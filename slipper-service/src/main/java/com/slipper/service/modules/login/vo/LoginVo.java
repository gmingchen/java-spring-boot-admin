package com.slipper.service.modules.login.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginVo {
    @NotBlank(message = "帐号不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "uuid不能为空")
    private String uuid;
    @NotBlank(message = "code不能为空")
    private String code;
}
