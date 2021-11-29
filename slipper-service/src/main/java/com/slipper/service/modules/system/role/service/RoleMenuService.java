package com.slipper.service.modules.system.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.service.modules.system.role.entity.RoleMenuEntity;

import java.util.List;

/**
 * 角色菜单关联
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface RoleMenuService extends IService<RoleMenuEntity> {

    /**
     * 根据角色ID获取用户菜单ID
     * @param roleIds
     * @return
     */
    List<Long> queryMenuIdByRoleId(List<Long> roleIds);

    /**
     * 角色菜单关联新增、编辑
     * @param roleId
     * @param menuIds
     */
    void createOrUpdate(Long roleId, List<Long> menuIds);

}

