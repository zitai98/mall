package com.zitai.mall.service;

import com.github.pagehelper.PageInfo;
import com.zitai.mall.MallApplicationTests;
import com.zitai.mall.enums.ResponseEnum;
import com.zitai.mall.service.impl.ProductServicelmp;
import com.zitai.mall.vo.ProductDetailVo;
import com.zitai.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @data 2020/3/25 - 下午8:34
 * 描述：
 */
public class IProductServiceTest extends MallApplicationTests {

    @Autowired
    ProductServicelmp productServicelmp;
    @Test
    public void list() {
//        productServicelmp.list(100012,1,1);
        ResponseVo<PageInfo> list = productServicelmp.list(null, 1, 2);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), list.getStatus());
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), list.getStatus());
    }

    @Test
    public void detail(){
        ResponseVo<ProductDetailVo> detail = productServicelmp.detail(26);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), detail.getStatus());
    }
}