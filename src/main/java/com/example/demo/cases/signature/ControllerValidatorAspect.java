package com.example.demo.cases.signature;

import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Order(2)
@Aspect
@Component
@Slf4j
public class ControllerValidatorAspect {

    //同一个请求多长时间内有效
    private static final Long EXPIRE_TIME = 60 * 1000 * 10L;
    //同一个nonce 请求多长时间内不允许重复请求
    private static final Long RESUBMIT_DURATION = 2000L;

    @Autowired
    private SignatureHeaders signatureHeaders;

    //要使整个类的方法都适用@within，只适用某个方法@annotation

    @Around("execution(* com.example.demo.cases.signature..*.*(..))" +
           // "&& @annotation(com.xxxx.wmhopenapi.util.signature.Signature) " +
            "&& (@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PatchMapping))"
    )
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //1、先检查配置文件中enable-all-interface-signature=true,true的话就开启所有接口签名校验
        Optional.ofNullable(signatureHeaders).orElseThrow(() -> new RuntimeException("配置文件异常！"));
        if(StringUtils.isNotBlank(signatureHeaders.getEnableAllInterfaceSignature())
                && EnableAllInterfaceSignaturesEnum.TRUE.getValue().equals(signatureHeaders.getEnableAllInterfaceSignature())){
            return validateSignatureSign(pjp);
        }
        //2、判断接口所在类的注解上是否有@InterfaceSignature，有就开启签名校验
        if(SignatureUtils.isAnnotated(pjp.getTarget().getClass(), InterfaceSignature.class)){
            return validateSignatureSign(pjp);
        }
        //3、判断接口上是否有@InterfaceSignature注解
        MethodSignature msig = null;
        if (!(pjp.getSignature() instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) pjp.getSignature();
        if(SignatureUtils.isAnnotated(pjp.getTarget().getClass().getMethod(msig.getName(), msig.getParameterTypes())
                , InterfaceSignature.class)){
            return validateSignatureSign(pjp);
        }
        return pjp.proceed();
    }

    /**
     * 接口签名校验
     * @param pjp
     */
    private Object validateSignatureSign(ProceedingJoinPoint pjp) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        InterfaceSignature interfaceSignature = method.getDeclaringClass().getAnnotation(InterfaceSignature.class);
        if(Objects.isNull(interfaceSignature)){
            interfaceSignature = method.getAnnotation(InterfaceSignature.class);
        }
        //获取header中的相关参数
        SignatureHeaders signatureHeaders = generateSignatureHeaders(interfaceSignature, request);
        //客户端签名
        String clientSignature = signatureHeaders.getSignature();
        //获取到header中的拼接结果
        String headersToSplice = SignatureUtils.toSplice(signatureHeaders);
        //服务端签名
        List<String> allSplice = generateAllSplice(method, pjp.getArgs(), headersToSplice);
        String serverSignature = SignatureUtils.signature(allSplice.toArray(new String[]{}), signatureHeaders.getAppsecret());
        if (!clientSignature.equals(serverSignature)) {
            log.error(String.format("签名不一致... clientSignature=%s, serverSignature=%s", clientSignature, serverSignature));
            throw new RuntimeException("接口签名不一致！");
        }
        //SignatureContext.setSignatureHeaders(signatureHeaders);
        log.info("签名验证通过, 相关信息: " + signatureHeaders);
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            throw e;
        }
    }

    /**
     * 根据request中header值生成SignatureHeaders实体
     */
    private SignatureHeaders generateSignatureHeaders(InterfaceSignature interfaceSignature, HttpServletRequest request) throws Exception {
        Map<String, Object> headerMap = Collections.list(request.getHeaderNames())
                .stream()
                .filter(headerName -> SignatureHeaders.HEADER_NAME_SET.contains(headerName))
                .collect(Collectors.toMap(headerName -> headerName.replaceAll(SignatureHeaders.SIGNATURE_HEADERS_PREFIX, StringUtils.EMPTY)
                        , headerName -> request.getHeader(headerName)));
        SignatureHeaders signatureHeaders = BeanUtils.mapToBean(headerMap, SignatureHeaders.class);
        //校验appid是否对应
        if(!this.signatureHeaders.getAppid().equals(signatureHeaders.getAppid())){
            throw new RuntimeException("appid不匹配！");
        }
        String appSecret = this.signatureHeaders.INNER_APP_MAP.get(signatureHeaders.getAppid());
        if (StringUtils.isBlank(appSecret)) {
            log.error("未找到appId对应的appSecret, appId=" + signatureHeaders.getAppid());
            throw new RuntimeException("appSecret异常！");
        }
        signatureHeaders.setAppsecret(appSecret);
        //校验接口签名是否超时
        Long now = System.currentTimeMillis();
        Long requestTimestamp = Long.parseLong(signatureHeaders.getTimestamp());
        if ((now - requestTimestamp) > EXPIRE_TIME) {
            String errMsg = "请求时间超过规定范围时间10分钟, signature=" + signatureHeaders.getSignature();
            log.error(errMsg);
            throw new RuntimeException(errMsg);
        }
        //判断是否打开重复请求配置
        if(StringUtils.isBlank(signatureHeaders.getResubmit())){
            if(interfaceSignature.resubmit()){
                validateWhetherRepeatedSubmit(signatureHeaders.getNonce());
            }
            return signatureHeaders;
        }
        if(ResubmitEnum.TRUE.getValue().equals(signatureHeaders.getResubmit())){
            validateWhetherRepeatedSubmit(signatureHeaders.getNonce());
        }
        return signatureHeaders;
    }

    /**
     * 校验是否重复提交
     */
    private void validateWhetherRepeatedSubmit(String nonce){
        //校验接口是否重复请求
        if (nonce.length() < 10) {
            String errMsg = "随机串nonce长度最少为10位, nonce=" + nonce;
            log.error(errMsg);
            throw new RuntimeException(errMsg);
        }
        /*String key = nonce + request.getServletPath() + JSON.toJSONString(request.getParameterMap());
        String existNonce = redisCacheService.getString();
        if (StringUtils.isBlank(existNonce)) {
            redisCacheService.setString(nonce, nonce);
            redisCacheService.expire(nonce, (int) TimeUnit.MILLISECONDS.toSeconds(RESUBMIT_DURATION));
        } else {
            String errMsg = "不允许重复请求, nonce=" + nonce;
            LOGGER.error(errMsg);
            throw new ServiceException("WMH5000", errMsg);
        }*/
    }

    /**
     * 生成header中的参数，mehtod中的参数的拼接
     */
    private List<String> generateAllSplice(Method method, Object[] args, String headersToSplice) {
        List<String> pathVariables = Lists.newArrayList(), requestParams = Lists.newArrayList();
        String beanParams = StringUtils.EMPTY;
        for (int i = 0; i < method.getParameterCount(); ++i) {
            MethodParameter mp = new MethodParameter(method, i);
            boolean findSignature = false;
            for (Annotation anno : mp.getParameterAnnotations()) {
                if (anno instanceof PathVariable) {
                    if (!Objects.isNull(args[i])) {
                        pathVariables.add(args[i].toString());
                    }
                    findSignature = true;
                } else if (anno instanceof RequestParam) {
                    RequestParam rp = (RequestParam) anno;
                    String name = mp.getParameterName();
                    if (StringUtils.isNotBlank(rp.name())) {
                        name = rp.name();
                    }
                    if (!Objects.isNull(args[i])) {
                        List<String> values = Lists.newArrayList();
                        if (args[i].getClass().isArray()) {
                            //数组
                            for (int j = 0; j < Array.getLength(args[i]); ++j) {
                                values.add(Array.get(args[i], j).toString());
                            }
                        } else if (ClassUtils.isAssignable(Collection.class, args[i].getClass())) {
                            //集合
                            for (Object o : (Collection<?>) args[i]) {
                                values.add(o.toString());
                            }
                        } else {
                            //单个值
                            values.add(args[i].toString());
                        }
                        values.sort(Comparator.naturalOrder());
                        requestParams.add(name + "=" + StringUtils.join(values));
                    }
                    findSignature = true;
                } else if (anno instanceof RequestBody || anno instanceof ModelAttribute) {
                    beanParams = SignatureUtils.toSplice(args[i]);
                    findSignature = true;
                }

                if (findSignature) {
                    break;
                }
            }
            if (!findSignature) {
                log.info(String.format("签名未识别的注解, method=%s, parameter=%s, annotations=%s", method.getName(), mp.getParameterName(), StringUtils.join(mp.getMethodAnnotations())));
            }
        }
        List<String> toSplices = Lists.newArrayList();
        toSplices.add(headersToSplice);
        toSplices.addAll(pathVariables);
        requestParams.sort(Comparator.naturalOrder());
        toSplices.addAll(requestParams);
        toSplices.add(beanParams);
        return toSplices;
    }

}