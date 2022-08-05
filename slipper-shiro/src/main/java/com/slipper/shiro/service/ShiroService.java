package com.slipper.shiro.service;

import com.slipper.service.modules.administrator.model.dto.AdministratorRoleDTO;
import com.slipper.service.modules.token.entity.TokenEntity;

import java.util.Set;

/**
 * shiro
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface ShiroService {

    /**
     * 获取token
     * @param token
     * @return
     */
    TokenEntity queryTokenByToken(String token);

    /**
     * 获取管理员信息
     * @param id 管理员ID
     * @return
     */
    AdministratorRoleDTO queryAdministratorById(Long id);

    /**
     * 获取管理员权限
     * @param administratorRoleDto 管理员
     * @return
     */
    Set<String> queryPermissionByAdministrator(AdministratorRoleDTO administratorRoleDto);
}

