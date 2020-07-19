package com.zitai.mall.service;

import com.zitai.mall.vo.CategoryVo;
import com.zitai.mall.vo.ResponseVo;

import java.util.List;
import java.util.Set;

/**
 * @data 2020/3/25 - 下午3:27
 * 描述：
 */
public interface ICategoryService {

    /**
     * 查询所有类目
     * @return
     */
    ResponseVo<List<CategoryVo>> selectAll();

    /**
     * 查询所有子类目id
     */
    void findSubCategoryId(Integer id, Set<Integer> resultSet);
}
