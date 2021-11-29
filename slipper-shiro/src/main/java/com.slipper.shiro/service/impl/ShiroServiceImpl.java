package com.slipper.shiro.service.impl;

import com.slipper.common.utils.Common;
import com.slipper.service.modules.develop.menu.dao.MenuDao;
import com.slipper.service.modules.system.token.dao.TokenDao;
import com.slipper.service.modules.system.token.entity.TokenEntity;
import com.slipper.service.modules.system.admin.dao.AdminDao;
import com.slipper.service.modules.system.admin.dto.AdminDto;
import com.slipper.shiro.service.ShiroService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * shiro
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("shiroService")
public class ShiroServiceImpl implements ShiroService {

    /*@Resource
    TokenService tokenService;
    @Resource
    AdminService adminService;
    @Resource
    MenuService menuService;*/
    @Resource
    TokenDao tokenDao;
    @Resource
    AdminDao adminDao;
    @Resource
    MenuDao menuDao;


    @Override
    public TokenEntity queryTokenByToken(String token) {
//        return tokenService.queryByToken(token);
        return tokenDao.queryByToken(token);
    }

    @Override
    public AdminDto queryAdminByAdminId(Long adminId) {
//        return adminService.queryById(adminId);
        return adminDao.queryById(adminId);
    }

    @Override
    public Set<String> queryPermissionByAdminId(Long adminId) {
//        return menuService.queryPermission(adminId);
        List<String> permissions;
        // 判断是否是超级管理员
        boolean isSuper = Common.isSuper(adminId);
        if (isSuper) { // 是超级管理员
            permissions = menuDao.queryAllPermission();
        } else { // 非超级管理员
            permissions = menuDao.queryPermissionByAdminId(adminId);
        }
        return getPermissionSet(permissions);
    }

    /**
     * 权限list 转 set
     * @param permissionList
     * @return
     */
    private Set<String> getPermissionSet(List<String> permissionList) {
        Set<String> permissions = new HashSet<>();
        for (String p : permissionList) {
            if (StringUtils.isBlank(p)) {
                continue;
            }
            permissions.addAll(Arrays.asList(p.trim().split(";")));
        }
        return permissions;
    }
}