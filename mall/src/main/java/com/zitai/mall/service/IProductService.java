package com.zitai.mall.service;

import com.github.pagehelper.PageInfo;
import com.zitai.mall.pojo.Product;
import com.zitai.mall.vo.ProductDetailVo;
import com.zitai.mall.vo.ProductVo;
import com.zitai.mall.vo.ResponseVo;

import java.util.List;

/**
 * @data 2020/3/25 - 下午8:17
 * 描述：
 */
public interface IProductService {
    ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize);

    ResponseVo<ProductDetailVo> detail(Integer productId);

}
