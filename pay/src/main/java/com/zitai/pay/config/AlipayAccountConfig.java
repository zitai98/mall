package com.zitai.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @data 2020/3/23 - 下午5:00
 * 描述：
 */
@Component
@Data
@ConfigurationProperties(prefix = "ali")
public class AlipayAccountConfig {
    private String appId;

    private String privateKey;

    private String publicKey;

    private String notifyUrl;

    private String returnUrl;
}
