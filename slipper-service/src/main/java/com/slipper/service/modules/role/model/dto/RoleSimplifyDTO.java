package com.slipper.service.modules.role.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色精简信息
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class RoleSimplifyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 名称
     */
    private String name;
}
