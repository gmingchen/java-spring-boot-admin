package com.slipper.service.modules.system.role.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色 select Dto
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class RoleSelectDto implements Serializable {
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
}
