package com.slipper.service.modules.administrator.model.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 管理员 新增 编辑 参数
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class AdministratorCreateOrUpdateForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID不能为空", groups = Update.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = {Create.class, Update.class})
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = {Create.class})
    private String password;
    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空", groups = {Create.class, Update.class})
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
    /**
     * 角色ID
     */
    @JsonProperty("role_ids")
    private List<Long> roleIds;
    /**
     * 企业ID
     */
    @JsonProperty("enterprise_id")
    private Long enterpriseId;
    /**
     * 是否是超管：0-否 1-是
     */
    private Integer supervisor;
}
