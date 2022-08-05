package com.slipper.service.modules.administrator.model.form;

import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;


/**
 * 管理员 编辑 基础信息 参数
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class AdministratorUpdateBasicForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确", groups = {Create.class, Update.class})
    private String email;
    /**
     * 性别：0-女 1-男 2-未知
     */
    private Integer sex;
}
