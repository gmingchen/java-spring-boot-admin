package com.slipper.service.modules.enterprise.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 企业菜单权限 树形结构
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class EnterpriseMenuTreeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 中文名称
     */
    @JsonProperty("name_cn")
    private String nameCn;
    /**
     * 英文名称
     */
    @JsonProperty("name_en")
    private String nameEn;
    /**
     * 路由 path 属性值 若为空则按照url路径处理
     */
    private String path;
    /**
     * 路由 name 属性值 若为空则按照url路径处理
     */
    private String name;
    /**
     * 菜单路由 或 URL
     */
    private String url;
    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String permission;
    /**
     * 类型：0-目录 1-菜单 2-按钮 3-iframe 4-外链
     */
    private Integer type;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否在菜单显示：0-否 1-是
     */
    private Integer show;
    /**
     * 是否显示在tab页签：0-否 1-是
     */
    private Integer tab;
    /**
     * 是否支持tab页签多开：0-否 1-是
     */
    private Integer multiple;
    /**
     * 是否支持缓存：0-否 1-是
     */
    private Integer keepalive;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 菜单ID
     */
    @JsonProperty("menu_id")
    private Long menuId;
    /**
     * 父ID
     */
    @JsonProperty("parent_id")
    private Long parentId;
    /**
     * 企业ID
     */
    @JsonProperty("enterprise_id")
    private Long enterpriseId;
    /**
     * 子集
     */
    private List<EnterpriseMenuTreeDTO> children;
}
