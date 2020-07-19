package com.zitai.mall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @data 2020/3/29 - 下午4:20
 * 描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Integer productId;

    private Integer quantity;

    private Boolean productSelected;
}
