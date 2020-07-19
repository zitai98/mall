package com.zitai.pay.config;

import com.lly835.bestpay.config.AliPayConfig;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @data 2020/3/20 - 下午3:39
 * 描述：
 */
@Component
public class BestPayConfig {

    @Autowired
    WxAccountConfig wxAccountConfig;
    @Autowired
    AlipayAccountConfig alipayAccountConfig;

    @Bean
    public BestPayService getBestPayService(WxPayConfig wxPayConfig){


        //支付宝配置
        AliPayConfig aliPayConfig = new AliPayConfig();
        aliPayConfig.setAppId(alipayAccountConfig.getAppId());
        aliPayConfig.setPrivateKey(alipayAccountConfig.getPrivateKey());
        aliPayConfig.setAliPayPublicKey(alipayAccountConfig.getPublicKey());
        aliPayConfig.setReturnUrl(alipayAccountConfig.getReturnUrl());
        aliPayConfig.setNotifyUrl(alipayAccountConfig.getNotifyUrl());
        //支付类, 所有方法都在这个类里
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayConfig(wxPayConfig);
        bestPayService.setAliPayConfig(aliPayConfig);

        return bestPayService;
    }

    @Bean
    public WxPayConfig getWxPayConfig(){
        //微信配置
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(wxAccountConfig.getAppId());        //公众号ID
        wxPayConfig.setMchId(wxAccountConfig.getMchId());        //商户ID
        wxPayConfig.setMchKey(wxAccountConfig.getMchKey());       //商户密钥
        wxPayConfig.setNotifyUrl(wxAccountConfig.getNotifyUrl());
        wxPayConfig.setReturnUrl(wxAccountConfig.getReturnUrl());
        return wxPayConfig;
    }
}
