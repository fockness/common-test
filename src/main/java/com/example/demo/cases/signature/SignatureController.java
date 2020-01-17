package com.example.demo.cases.signature;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *  方案一：
 * 需要进行接口签名校验的controller加上@InterfaceSignature，在类或者方法上加都可以
 * 配置文件中需要有一个开关去设定是否开启所有接口都要签名校验，开启就不需要@InterfaceSignature，没开启就需要该注解来指定
 * 方案二：
 * 需要进行接口签名校验的对象模型，加上注解@Signature以及在字段上加@SignatureField指定哪些字段需要校验
 *
 */
@RestController
@InterfaceSignature
@Slf4j
public class SignatureController {

    @RequestMapping("/exampleSignature")
    @ResponseBody
    public String exampleSignature(@RequestBody Object o) {
        //生成接口签名
        //初始化请求头信息
        /*SignatureHeaders signatureHeaders = new SignatureHeaders();
        signatureHeaders.setAppid("111");
        signatureHeaders.setAppsecret("222");
        signatureHeaders.setNonce(SignatureUtils.generateNonce());
        signatureHeaders.setTimestamp(String.valueOf(System.currentTimeMillis()));
        List<String> pathParams = new ArrayList<>();
        //初始化path中的数据
        pathParams.add(SignatureUtils.encode("18237172801", signatureHeaders.getAppsecret()));
        //调用签名工具生成签名
        signatureHeaders.setSignature(SignatureUtils.signature(signatureHeaders, pathParams, null, null));
        System.out.println("签名数据: " + signatureHeaders);
        System.out.println("请求数据: " + pathParams);*/
        log.info("signature info===================");
        return StringUtils.EMPTY;
    }
}
