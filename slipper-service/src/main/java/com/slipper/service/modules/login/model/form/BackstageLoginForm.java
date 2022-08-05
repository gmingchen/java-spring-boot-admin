package com.slipper.service.modules.login.model.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 后台登录
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class BackstageLoginForm implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @NotBlank(message = "帐号不能为空")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * UUID
     */
    @NotBlank(message = "uuid不能为空")
    private String uuid;
    /**
     * 验证码
     */
    @NotBlank(message = "code不能为空")
    private String code;
}
