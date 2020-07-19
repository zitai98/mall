package com.zitai.mall.service;

import com.github.pagehelper.PageInfo;
import com.zitai.mall.MallApplicationTests;
import com.zitai.mall.enums.ResponseEnum;
import com.zitai.mall.form.ShippingForm;
import com.zitai.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @data 2020/3/30 - 上午10:14
 * 描述：
 */
@Slf4j
public class IShippingServiceTest extends MallApplicationTests {

    @Autowired
    private IShippingService shippingService;

    private Integer uid = 1;
    Integer shippingId = 8;

    ShippingForm form;

    @Before
    public void before(){
        ShippingForm form = new ShippingForm();
        form.setReceiverName("ziizit");
        form.setReceiverAddress("慕课网");
        form.setReceiverCity("广州");
        form.setReceiverMobile("188123456789");
        form.setReceiverPhone("0100202312");
        form.setReceiverProvince("广东");
        form.setReceiverDistrict("从化区");
        form.setReceiverZip("000000");
        this.form =form;
    }


    @Test
    public void add() {
        ResponseVo<Map<String, Integer>> responseVo = shippingService.add(uid, form);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }

    @Test
    public void delete() {
        ResponseVo responseVo = shippingService.delete(uid, shippingId);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }


    @Test
    public void update() {
        form.setReceiverCity("清远");
        ResponseVo responseVo = shippingService.update(uid, shippingId, form);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }

    @Test
    public void list() {
        ResponseVo<PageInfo> responseVo = shippingService.list(uid, 1, 10);
        log.info("result={}", responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }
}