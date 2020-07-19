package com.zitai.pay.service;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayResponse;
import com.zitai.pay.pojo.PayInfo;

import java.math.BigDecimal;

/**
 * @data 2020/3/19 - 下午10:32
 * 描述：
 */
public interface IPayService {
    /*创建、发起支付*/
    public PayResponse create(String orderId, BigDecimal amount, BestPayTypeEnum bestPayTypeEnum) throws Exception;

    /*异步通知处理*/
    String asyncNotify(String notifyData);

    /*通过订单号查询订单详情*/
    PayInfo queryByOrderID(String orderId);
}
