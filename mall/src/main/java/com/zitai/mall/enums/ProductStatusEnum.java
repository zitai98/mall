package com.zitai.mall.enums;

import lombok.Getter;

/**
 * @data 2020/3/28 - 下午10:21
 * 描述：商品状态.1-在售 2-下架 3-删除
 */
@Getter
public enum ProductStatusEnum {

    ON_SALE(1),

    OFF_SALE(2),

    DELETE(3),

    ;
    Integer code;

    ProductStatusEnum(Integer code){
        this.code = code;
    }
}
