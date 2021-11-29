package com.slipper.service.modules.system.admin.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改用户信息Vo
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class EditAdminVo {

    private Long id;
    /**
     * 昵称
     */
    @NotBlank(message = "用户名不能为空")
    private String nickname;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private Long avatar;
    /**
     * 原密码
     */
    @JsonProperty("old_password")
    private String oldPassword;
    /**
     * 新密码
     */
    @JsonProperty("new_password")
    private String newPassword;

}
