package com.zitai.mall.enums;

import lombok.Getter;

/**
 * @data 2020/3/30 - 下午9:00
 * 描述：
 */
@Getter
public enum PaymentTypeEnum {

    PAY_ONLINE(1),
    ;


    Integer code;
    PaymentTypeEnum(Integer code) {
        this.code = code;
    }
}
