package com.zitai.mall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @data 2020/3/29 - 下午2:51
 * 描述：
 */
@Data
public class CartVo {

    private List<CartProductVo> cartProductVoList;

    private boolean selectedAll;

    private BigDecimal cartTotalPrice;

    private Integer cartTotalQuantity;
}
