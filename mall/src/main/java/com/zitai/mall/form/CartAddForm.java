package com.zitai.mall.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @data 2020/3/29 - 下午2:59
 * 描述：添加商品
 */
@Data
public class CartAddForm {

    @NotNull
    private Integer productId;

    private Boolean selected = true;
}
