package com.slipper.service.modules.develop.menu.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
public class MenuDto {
    @TableId
    private Long id;
    /**
     * 父ID
     */
    @JsonProperty("parent_id")
    private Long parentId;
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
     * 菜单URL
     */
    private String url;
    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    private Integer type;
    /**
     * 图标
     */
    private String icon;
    /**
     * 授权
     */
    private String permission;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否显示
     */
    @JsonProperty("is_display")
    private Integer isDisplay;
    /**
     * 是否缓存
     */
    @JsonProperty("is_alive")
    private Integer isAlive;
    /**
     * 是否显示在标签栏
     */
    @JsonProperty("is_tab")
    private Integer isTab;
    /**
     * 是否支持标签栏多开
     */
    @JsonProperty("is_multiple")
    private Integer isMultiple;
    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    private Date createdAt;
    /**
     * 树形
     */
    private List<MenuDto> children;

    /**
     * 重写get方法 返回为空 返回 空数组
     * @return
     */
    public List<MenuDto> getChildren() {
        return children != null ? children : Collections.emptyList();
    }
}
