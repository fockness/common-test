package com.example.demo.cases.signature;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.util.Map;
import java.util.Set;

/**
 *
 * 签名实体
 */
@ConfigurationProperties(prefix = "wowjoy.validate")
@Signature
@Data
@Component
public class SignatureHeaders {
    public static final String SIGNATURE_HEADERS_PREFIX = "wowjoy-validate-";
    public static final Set<String> HEADER_NAME_SET = Sets.newHashSet();
    public static final Map<String, String> INNER_APP_MAP = Maps.newHashMap();
    private static final String HEADER_APPID = SIGNATURE_HEADERS_PREFIX + "appid";
    private static final String HEADER_TIMESTAMP = SIGNATURE_HEADERS_PREFIX + "timestamp";
    private static final String HEADER_NONCE = SIGNATURE_HEADERS_PREFIX + "nonce";
    private static final String HEADER_SIGNATURE = SIGNATURE_HEADERS_PREFIX + "signature";
    static {
        HEADER_NAME_SET.add(HEADER_APPID);
        HEADER_NAME_SET.add(HEADER_TIMESTAMP);
        HEADER_NAME_SET.add(HEADER_NONCE);
        HEADER_NAME_SET.add(HEADER_SIGNATURE);
    }

    /**
     * 线下分配的值     * 客户端和服务端各自保存appId对应的appSecret
     */
    @NotBlank(message = "Header中缺少" + HEADER_APPID)
    @SignatureField
    private String appid;
    /**
     * 线下分配的值     * 客户端和服务端各自保存，与appId对应
     */
    @SignatureField
    private String appsecret;
    /**
     * 时间戳，单位: ms
     */
    @NotBlank(message = "Header中缺少" + HEADER_TIMESTAMP)
    @SignatureField
    private String timestamp;
    /**
     * 流水号【防止重复提交】; (备注：针对查询接口，流水号只用于日志落地，便于后期日志核查； 针对办理类接口需校验流水号在有效期内的唯一性，以避免重复请求)
     */
    @NotBlank(message = "Header中缺少" + HEADER_NONCE)
    @SignatureField
    private String nonce;
    /**
     * 签名
     */
    @NotBlank(message = "Header中缺少" + HEADER_SIGNATURE)
    private String signature;

    /**
     * 是否开启所有接口都要签名校验开关
     * true是，false否
     */
    @NotBlank(message = "")
    private String enableAllInterfaceSignature;

    /**
     * 允许重复请求
     * true是，false否
     */
    @NotBlank(message = "")
    private String resubmit;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("appid", appid)
                .add("appsecret", appsecret)
                .add("timestamp", timestamp)
                .add("nonce", nonce)
                .add("signature", signature)
                .toString();
    }
    {
        INNER_APP_MAP.put(appid, appsecret);
    }
}