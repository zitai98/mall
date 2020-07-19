package com.zitai.mall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @data 2020/3/24 - 下午3:58
 * 描述：
 */
@Data
public class UserLoginForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
