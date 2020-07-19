package com.zitai.mall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @data 2020/3/30 - 上午9:58
 * 描述：
 */
@Data
public class ShippingForm {
    @NotBlank
    private String receiverName;

    @NotBlank
    private String receiverPhone;

    @NotBlank
    private String receiverMobile;

    @NotBlank
    private String receiverProvince;

    @NotBlank
    private String receiverCity;

    @NotBlank
    private String receiverDistrict;

    @NotBlank
    private String receiverAddress;

    @NotBlank
    private String receiverZip;
}
