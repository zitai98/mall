package com.zitai.mall.controller;

import com.zitai.mall.service.ICategoryService;
import com.zitai.mall.vo.CategoryVo;
import com.zitai.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @data 2020/3/25 - 下午3:46
 * 描述：
 */
@RestController
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/categories")
    public ResponseVo<List<CategoryVo>> selectAll(){
        return categoryService.selectAll();
    }

}
