package com.slipper.service.modules.develop.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.service.modules.develop.menu.entity.MenuEntity;
import com.slipper.service.modules.develop.menu.dto.MenuDto;
import com.slipper.service.modules.develop.menu.dto.MenuSelectDto;

import java.util.List;
import java.util.Set;

/**
 * 菜单权限
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface MenuService extends IService<MenuEntity> {

    /**
     * 获取权限 TODO:超级管理员获取所有权限
     * @param adminId 管理员ID
     * @return
     */
    Set<String> queryPermission(Long adminId);

    /**
     * 获取菜单 TODO:超级管理员获取所有菜单
     * @param adminId 管理员ID
     * @return
     */
    List<MenuDto> queryMenu(Long adminId);

    /**
     * 获取未删除的菜单
     * @param parentId 父级ID
     * @return
     */
    List<MenuDto> queryAllMenuByParentId(Long parentId);

    /**
     * 获取所有未删除菜单 TODO:不包括按钮 非树形
     * @return
     */
    List<MenuSelectDto> queryNoButtonMenu();

    /**
     * 根据角色获取 菜单 权限
     * @return
     */
    List<MenuSelectDto> queryAllMenuByRole(Long adminId);

    /**
     * 信息
     * @param id
     * @return
     */
    MenuDto queryById(Long id);

    /**
     * 新增
     * @param menuEntity 菜单实体
     */
    void create(MenuEntity menuEntity);

    /**
     * 编辑
     * @param menuEntity 菜单实体
     */
    void update(MenuEntity menuEntity);
}

