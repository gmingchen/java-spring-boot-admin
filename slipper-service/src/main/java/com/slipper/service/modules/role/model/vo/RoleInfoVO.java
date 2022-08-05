package com.slipper.service.modules.role.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色详情 视图数据
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class RoleInfoVO implements Serializable {
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
     * 企业菜单ID
     */
    @JsonProperty("enterprise_menu_ids")
    public List<Long> enterpriseMenuIds;
}
