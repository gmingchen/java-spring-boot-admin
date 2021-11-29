package com.slipper.service.modules.develop.menu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 菜单权限
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("menu")
public class MenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "id不能为空", groups = Update.class)
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	/**
	 * 父ID
	 */
	@NotNull(message = "上级菜单不能为空", groups = {Create.class, Update.class})
	@JsonProperty("parent_id")
	private Long parentId;
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
	 * 菜单URL
	 */
	private String url;
	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	private String permission;
	/**
	 * 类型   0：目录   1：菜单   2：按钮	
	 */
	private Integer type;
	/**
	 * 图标
	 */
	private String icon;
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
	 *
	 */
	@TableLogic
	private Integer deleted;
}
