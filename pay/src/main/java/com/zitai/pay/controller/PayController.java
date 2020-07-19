package com.zitai.pay.controller;

import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayResponse;
import com.zitai.pay.pojo.PayInfo;
import com.zitai.pay.service.impl.PayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @data 2020/3/19 - 下午11:26
 * 描述：
 */
@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayServiceImpl payServiceImpl;

    @Autowired
    private WxPayConfig wxPayConfig;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("amount") BigDecimal amount,
                               @RequestParam("payType") BestPayTypeEnum payType
    ) throws Exception {
        PayResponse response = payServiceImpl.create(orderId, amount, payType);
        Map<String,String> map = new HashMap<>();
        if(payType==BestPayTypeEnum.WXPAY_NATIVE){
            map.put("code_url",response.getCodeUrl());
            map.put("orderId", orderId);
            map.put("returnUrl", wxPayConfig.getReturnUrl());
            return new ModelAndView("createForWxNATIVE", map);
        }
        else if(payType==BestPayTypeEnum.ALIPAY_PC){
            map.put("body", response.getBody());
            return new ModelAndView("createForALPC", map);
        }
        return new ModelAndView();
    }

    @PostMapping("/notify")
    @ResponseBody
    public String asyncNotify(@RequestBody String notifyData){
//        log.info("notifyData{}",notifyData);
        return payServiceImpl.asyncNotify(notifyData);


    }

    @GetMapping("/queryByOrderId")
    @ResponseBody
    public PayInfo queryByOrderID(@RequestParam("orderId") String orderId){
        return payServiceImpl.queryByOrderID(orderId);
    }

}
