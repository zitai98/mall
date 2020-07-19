package com.zitai.mall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @data 2020/3/24 - 下午2:52
 * 描述：
 */
@Data
public class UserRegisterForm {

//    @NotBlank 用于String判断空格
//    @NotEmpty 用于集合
//    @NotNull
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;
}
