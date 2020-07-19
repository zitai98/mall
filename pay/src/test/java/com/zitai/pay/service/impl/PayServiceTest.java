package com.zitai.pay.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.zitai.pay.PayApplicationTests;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @data 2020/3/19 - 下午10:56
 * 描述：
 */
public class PayServiceTest extends PayApplicationTests {

    @Autowired
    private PayServiceImpl payServiceImpl;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void create() throws Exception {
        //BigDecimal.valueOf(0.01)
        //new BigDecimal(0.01) 不能使用这种形式，因为构造函数的参数是一个字符串
        payServiceImpl.create("5612673681273", BigDecimal.valueOf(0.01), BestPayTypeEnum.WXPAY_NATIVE);
    }

    @Test
    public void sendMQMsg(){
        amqpTemplate.convertAndSend("payNotify", "hello");
    }
}