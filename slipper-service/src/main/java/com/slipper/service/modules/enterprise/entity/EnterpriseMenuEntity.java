package com.slipper.service.modules.enterprise.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 企业菜单权限
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
@TableName("enterprise__menu")
public class EnterpriseMenuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID不能为空", groups = Update.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 中文名称
     */
    @NotBlank(message = "中文名称不能为空", groups = {Create.class, Update.class})
    @JsonProperty("name_cn")
    private String nameCn;
    /**
     * 英文名称
     */
    @JsonProperty("name_en")
    private String nameEn;
    /**
     * 图标
     */
    private String icon;
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
     * 企业ID
     */
    @JsonProperty("enterprise_id")
    private Long enterpriseId;
    /**
     * 创建者
     */
    private Long creator;
    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    private Date createdAt;
    /**
     * 更新者
     */
    private Long updater;
    /**
     * 更新时间
     */
    @JsonProperty("updated_at")
    private Date updatedAt;
    /**
     * 是否删除：0-否 1-是
     */
    @TableLogic
    private Integer deleted;
}
