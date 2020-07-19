package com.zitai.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @data 2020/3/23 - 下午4:55
 * 描述：
 */
@Component
@Data
@ConfigurationProperties(prefix = "wx")
public class WxAccountConfig {

    private String appId;

    private String mchId;

    private String mchKey;

    private String notifyUrl;

    private String returnUrl;
}
