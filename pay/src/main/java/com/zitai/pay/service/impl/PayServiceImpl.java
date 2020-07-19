package com.zitai.pay.service.impl;

import com.google.gson.Gson;
import com.lly835.bestpay.config.AliPayConfig;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.enums.BestPayPlatformEnum;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.enums.OrderStatusEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.zitai.pay.config.BestPayConfig;
import com.zitai.pay.dao.PayInfoMapper;
import com.zitai.pay.enums.PayPlatformEnum;
import com.zitai.pay.pojo.PayInfo;
import com.zitai.pay.service.IPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @data 2020/3/19 - 下午10:33
 * 描述：
 */
@Slf4j
@Service
public class PayServiceImpl implements IPayService {

    private final static String QUEUE_PAY_NOTIFY = "payNotify";

    @Autowired
    private BestPayService bestPayService;
    @Autowired
    private PayInfoMapper payInfoMapper;
    @Autowired
    private AmqpTemplate amqpTemplate;


    @Override
    public PayResponse create(String orderId, BigDecimal amount, BestPayTypeEnum bestPayTypeEnum) throws Exception {
        if(bestPayTypeEnum != BestPayTypeEnum.WXPAY_NATIVE && bestPayTypeEnum != BestPayTypeEnum.ALIPAY_PC){
            throw new Exception("暂时不支持该付款方式");
        }
        //数据库存储订单信息
        PayInfo payInfo = new PayInfo(Long.parseLong(orderId),
                PayPlatformEnum.getByBestPayTypeEnum(bestPayTypeEnum).getCode(),
                OrderStatusEnum.NOTPAY.name(),
                amount);
        payInfoMapper.insertSelective(payInfo);

        //发起支付
        PayRequest request = new PayRequest();
        request.setOrderName("8293766-最好的支付sdk");
        request.setOrderId(orderId);
        request.setOrderAmount(amount.doubleValue());
        request.setPayTypeEnum(bestPayTypeEnum);

        PayResponse response = bestPayService.pay(request);
        log.info("response={}", response);

        return response;
    }

    //异步回调
    @Override
    public String asyncNotify(String notifyData) {
        //1.签名检验
        PayResponse response = bestPayService.asyncNotify(notifyData);
        log.info("异步通知 payResponse={}",response);

        //2.金额校验（从数据库查订单）
        //比较严重（正常情况不会发生）发出警告：钉钉、短信
        PayInfo payInfo = payInfoMapper.selectByOrderNo(Long.parseLong(response.getOrderId()));
        if(payInfo == null){
            throw new RuntimeException("通过orderno查询的结果为null");
        }
        //如果支付状态不是“已支付”
        if(!payInfo.getPayPlatform().equals(OrderStatusEnum.SUCCESS)){
            if(payInfo.getPayAmount().compareTo(BigDecimal.valueOf(response.getOrderAmount())) != 0){
                //金额告警
                throw new RuntimeException("异步通知中的金额和数据库里的不一致，orderId"+payInfo.getOrderNo());
            }
            //3.修改订单支付状态
            payInfo.setPlatformStatus(OrderStatusEnum.SUCCESS.name());//设置订单状态
            payInfo.setPlatformNumber(response.getOutTradeNo());//设置流水ID号
            payInfoMapper.updateByPrimaryKeySelective(payInfo);

        }

        //TODO pay发送MQ消息，mall接收MQ消息
        amqpTemplate.convertAndSend("QUEUE_PAY_NOTIFY", new Gson().toJson(payInfo));

        //4.回复微信校验成功
        if(response.getPayPlatformEnum() == BestPayPlatformEnum.WX){
            return "<xml>\n" +
                    "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                    "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                    "</xml>";
        }
        else if (response.getPayPlatformEnum() == BestPayPlatformEnum.ALIPAY){
            return "success";
        }
        return "";
    }

    @Override
    public PayInfo queryByOrderID(String orderId) {
        return payInfoMapper.selectByOrderNo(Long.parseLong(orderId));
    }
}
