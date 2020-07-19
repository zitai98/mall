package com.zitai.mall.dao;

import com.zitai.mall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectByCategorySet(@Param("categorySet") Set<Integer> categorySet);

    List<Product> selectByProductIdSet(@Param("productIdSet") Set<Integer> productIdSet);
}