package com.zitai.mall.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @data 2020/3/25 - 下午3:25
 * 描述：
 */
@Data
public class CategoryVo {
    private Integer id;

    private Integer parentId;

    private String name;

    private Integer sortOrder;

    private List<CategoryVo> subCategories;
}
