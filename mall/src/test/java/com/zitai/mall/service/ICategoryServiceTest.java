package com.zitai.mall.service;

import com.zitai.mall.MallApplicationTests;
import com.zitai.mall.enums.ResponseEnum;
import com.zitai.mall.pojo.Category;
import com.zitai.mall.vo.CategoryVo;
import com.zitai.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @data 2020/3/25 - 下午5:06
 * 描述：
 */
public class ICategoryServiceTest extends MallApplicationTests {

    @Autowired
    private ICategoryService categoryService;
    
    @Test
    public void selectAll() {
        ResponseVo<List<CategoryVo>> listResponseVo = categoryService.selectAll();
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(), listResponseVo.getStatus());
    }

    @Test
    public void findSubCategoryId(){
        Set<Integer> set = new HashSet<>();
        categoryService.findSubCategoryId(100001, set);
        System.out.println(set);
    }
}