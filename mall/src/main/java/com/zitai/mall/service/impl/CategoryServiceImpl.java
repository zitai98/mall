package com.zitai.mall.service.impl;

import com.zitai.mall.dao.CategoryMapper;
import com.zitai.mall.enums.ResponseEnum;
import com.zitai.mall.pojo.Category;
import com.zitai.mall.service.ICategoryService;
import com.zitai.mall.vo.CategoryVo;
import com.zitai.mall.vo.ResponseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.zitai.mall.consts.MallConst.ROOT_PARENT_ID;

/**
 * @data 2020/3/25 - 下午3:29
 * 描述：
 */
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 查询所有类目
     * @return
     */
    @Override
    public ResponseVo<List<CategoryVo>> selectAll() {
        List<Category> categories = categoryMapper.selectAll();

//        for (Category category : categories) {
//            if(category.getParentId().equals(ROOT_PARENT_ID)){
//                CategoryVo categoryVo = category2CategoryVo(category);
//                categoryVoList.add(categoryVo);
//            }
//        }

//        Lambda+Stream
        List<CategoryVo> categoryVoList = categories.stream()
                .filter(e -> e.getParentId().equals(ROOT_PARENT_ID))
                .map(e -> category2CategoryVo(e))
                .sorted(Comparator.comparing(CategoryVo::getSortOrder).reversed())
                .collect(Collectors.toList());
        //查找一级目录
        findSubCategory(categoryVoList, categories);
        return ResponseVo.success(categoryVoList);

    }

    /**
     * 查询所有子类目id
     *
     * @param id
     * @param resultSet
     */
    @Override
    public void findSubCategoryId(Integer id, Set<Integer> resultSet) {
        List<Category> categories = categoryMapper.selectAll();
        findSubCategoryId(id, resultSet,categories);
    }
    public void findSubCategoryId(Integer id, Set<Integer> resultSet, List<Category> categories){
        for (Category category : categories) {
            if(category.getParentId().equals(id)){
                resultSet.add(category.getId());
                findSubCategoryId(category.getId(),resultSet,categories);
            }
        }
    }

    private void findSubCategory(List<CategoryVo> categoryVoList, List<Category> categories){
        for (CategoryVo categoryVo : categoryVoList) {
            List<CategoryVo> subCategoryVoList = new ArrayList<>();
            for (Category category : categories) {
                if(categoryVo.getId().equals(category.getParentId())){
                    CategoryVo subCategoryVo = category2CategoryVo(category);
                    subCategoryVoList.add(subCategoryVo);
                }
            }
            subCategoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
            categoryVo.setSubCategories(subCategoryVoList);
            findSubCategory(subCategoryVoList, categories);
        }
    }

    private CategoryVo category2CategoryVo(Category category){
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }
}
