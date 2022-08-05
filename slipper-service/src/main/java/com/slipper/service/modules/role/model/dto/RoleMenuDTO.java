package com.slipper.service.modules.role.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色 菜单ID 信息
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class RoleMenuDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否显示：0-否 1-是
     * 用于不给用户操作的角色 系统角色
     */
    private Integer show;
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
     * 菜单ID
     */
    @JsonProperty("menu_ids")
    private List<Long> menuIds;
}
