package com.slipper.service.modules.system.role.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色Dto
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class RoleDto implements Serializable {
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
	 * 创建者
	 */
	private Long creator;
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
