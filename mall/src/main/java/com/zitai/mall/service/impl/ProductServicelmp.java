package com.zitai.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zitai.mall.dao.ProductMapper;
import com.zitai.mall.enums.ResponseEnum;
import com.zitai.mall.pojo.Product;
import com.zitai.mall.service.ICategoryService;
import com.zitai.mall.service.IProductService;
import com.zitai.mall.vo.ProductDetailVo;
import com.zitai.mall.vo.ProductVo;
import com.zitai.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.zitai.mall.enums.ProductStatusEnum.DELETE;
import static com.zitai.mall.enums.ProductStatusEnum.OFF_SALE;
import static com.zitai.mall.enums.ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE;

/**
 * @data 2020/3/25 - 下午8:22
 * 描述：
 */
@Service
@Slf4j
public class ProductServicelmp implements IProductService {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResponseVo<PageInfo> list(Integer categoryId, Integer pageNum, Integer pageSize) {
        Set<Integer> categorySet = new HashSet<>();
        if(categoryId!=null){
            //获取子类目ID
            categoryService.findSubCategoryId(categoryId, categorySet);
            //添加父类ID
            categorySet.add(categoryId);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList= productMapper.selectByCategorySet(categorySet);
        List<ProductVo> productVoList = productList.stream()
                .map(e -> {
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(e, productVo);
                    return productVo;
                }).collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo(productList);
        pageInfo.setList(productVoList);

        return ResponseVo.success(pageInfo);
    }

    @Override
    public ResponseVo<ProductDetailVo> detail(Integer productId) {
        Product product = productMapper.selectByPrimaryKey(productId);

        if(product.getStatus().equals(OFF_SALE.getCode()) || product.getStatus().equals(DELETE.getCode())){
            return ResponseVo.error(PRODUCT_OFF_SALE_OR_DELETE);
        }
        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product, productDetailVo);
        return ResponseVo.success(productDetailVo);
    }
}
