package com.slipper.service.modules.administrator.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.slipper.service.modules.role.model.dto.RoleSimplifyDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 管理员 角色 信息
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class AdministratorRoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
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
    private String email;
    /**
     * 性别：0-女 1-男 2-未知
     */
    private Integer sex;
    /**
     * 状态：0-禁用 1-正常
     */
    private Integer status;
    /**
     * 是否是超管：0-否 1-是
     */
    private Integer supervisor;
    /**
     * 企业ID
     */
    @JsonProperty("enterprise_id")
    private Long enterpriseId;
    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    private Date createdAt;
    /**
     * 更新时间
     */
    @JsonProperty("updated_at")
    private Date updatedAt;
    /**
     * 角色列表
     */
    private List<RoleSimplifyDTO> roles;
}
