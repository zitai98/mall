package com.zitai.mall.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @data 2020/3/31 - 下午5:22
 * 描述：
 */
@Data
public class OrderCreateForm {

    @NotNull
    private Integer shippingId;
}
