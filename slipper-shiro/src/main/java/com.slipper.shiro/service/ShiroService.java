package com.slipper.shiro.service;

import com.slipper.service.modules.system.token.entity.TokenEntity;
import com.slipper.service.modules.system.admin.dto.AdminDto;

import java.util.Set;

/**
 * 菜单
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
     * 获取用户信息
     * @param adminId
     * @return
     */
    AdminDto queryAdminByAdminId(Long adminId);

    /**
     * 获取用户权限
     * @param adminId
     * @return
     */
    Set<String> queryPermissionByAdminId(Long adminId);
}

