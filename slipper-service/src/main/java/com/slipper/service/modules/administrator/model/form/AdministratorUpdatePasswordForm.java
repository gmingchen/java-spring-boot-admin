package com.slipper.service.modules.administrator.model.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 管理员 编辑 密码 参数
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class AdministratorUpdatePasswordForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不能为空")
    @JsonProperty("old_password")
    private String oldPassword;
    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    @JsonProperty("new_password")
    private String newPassword;
}
