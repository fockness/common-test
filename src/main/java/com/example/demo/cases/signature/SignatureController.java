package com.example.demo.cases.signature;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SignatureController {

    @RequestMapping("/exampleSignature")
    @ResponseBody
    public String exampleSignature(@RequestBody Object o) {
        //生成接口签名
        //初始化请求头信息
        SignatureHeaders signatureHeaders = new SignatureHeaders();
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
        System.out.println("请求数据: " + pathParams);
        return StringUtils.EMPTY;
    }
}
