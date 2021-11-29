package com.slipper.service.modules.develop.menu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.service.modules.system.admin.dto.AdminDto;
import com.slipper.service.modules.system.admin.service.AdminService;
import com.slipper.service.modules.system.role.dto.RoleDto;
import com.slipper.service.modules.system.role.service.RoleMenuService;
import com.slipper.common.utils.Common;
import com.slipper.common.utils.Constant;
import com.slipper.service.modules.develop.menu.dao.MenuDao;
import com.slipper.service.modules.develop.menu.entity.MenuEntity;
import com.slipper.service.modules.develop.menu.dto.MenuDto;
import com.slipper.service.modules.develop.menu.dto.MenuSelectDto;
import com.slipper.service.modules.develop.menu.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 菜单权限
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements MenuService {

    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private AdminService adminService;

    @Override
    public Set<String> queryPermission(Long adminId) {
        List<String> permissions;
        // 判断是否是超级管理员
        boolean isSuper = Common.isSuper(adminId);
        if (isSuper) { // 是超级管理员
            permissions = baseMapper.queryAllPermission();
        } else { // 非超级管理员
            permissions = baseMapper.queryPermissionByAdminId(adminId);
        }
        return getPermissionSet(permissions);
    }

    @Override
    public List<MenuDto> queryMenu(Long adminId) {
        List<Long> menuIds;
        // 判断是否是超级管理员
        boolean isSuper = Common.isSuper(adminId);
        if (isSuper) { // 是超级管理员
            // 获取所有的菜单权限ID
            menuIds = baseMapper.queryAllMenuId();
        } else { // 非超级管理员
            // 获取当前用户所有的角色ID
            menuIds = getAdminRoleId(adminId);
        }
        // 获取根目录菜单权限
        List<MenuDto> menus = baseMapper.queryMenuByParentId(0L, menuIds);
        return queryMenuTree(menus, menuIds);
    }

    @Override
    public List<MenuDto> queryAllMenuByParentId(Long parentId) {
        if (parentId == null) {
            parentId = 0L;
        }
        return baseMapper.queryAllMenuByParentId(parentId);
    }

    @Override
    public List<MenuSelectDto> queryNoButtonMenu() {
        List<Integer> types = new ArrayList<>();
        types.add(Constant.MenuType.CATALOG.getValue());
        types.add(Constant.MenuType.MENU.getValue());
        return baseMapper.queryAllMenu(types, null, null);
    }

    @Override
    public List<MenuSelectDto> queryAllMenuByRole(Long adminId) {
        List<Long> menuIds;
        // 判断是否是超级管理员
        boolean isSuper = Common.isSuper(adminId);
        if (isSuper) {
            menuIds = null;
        } else {
            // 获取当前用户所有的角色ID
            menuIds = getAdminRoleId(adminId);
        }
        return baseMapper.queryAllMenu(null, menuIds, null);
    }

    @Override
    public MenuDto queryById(Long id) {
        return baseMapper.queryById(id);
    }

    @Override
    public void create(MenuEntity menuEntity) {
        menuEntity.setCreatedAt(new Date());
        this.save(menuEntity);
    }

    @Override
    public void update(MenuEntity menuEntity) {
        menuEntity.setUpdatedAt(new Date());
        this.updateById(menuEntity);
    }

    /**
     * 递归获取权限菜单
     * @param menus 父级菜单
     * @param menuIds 拥有的菜单ID
     * @return
     */
    private List<MenuDto> queryMenuTree(List<MenuDto> menus, List<Long> menuIds) {
        List<MenuDto> list = new ArrayList<>();
        if (menuIds == null || menuIds.size() == 0) {
            return list;
        }
        for (MenuDto menu : menus) {
            if (!menuIds.contains(menu.getId())) {
                break;
            }
            if (menu.getType() != Constant.MenuType.BUTTON.getValue()) {
                menu.setChildren(baseMapper.queryMenuByParentId(menu.getId(), menuIds));
            }
            list.add(menu);
        }
        return list;
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

    /**
     * 获取用户角色ID
     * @param adminId 管理员ID
     * @return
     */
    private List<Long> getAdminRoleId(Long adminId) {
        AdminDto adminDto = adminService.queryById(adminId);
        List<Long> roleIds = new ArrayList<>();
        for (RoleDto role : adminDto.getRoles()) {
            roleIds.add(role.getId());
        }
        // 通过角色ID获取拥有的菜单权限ID
        if (roleIds.size() == 0) {
            return null;
        } else {
            return roleMenuService.queryMenuIdByRoleId(roleIds);
        }
    }
}