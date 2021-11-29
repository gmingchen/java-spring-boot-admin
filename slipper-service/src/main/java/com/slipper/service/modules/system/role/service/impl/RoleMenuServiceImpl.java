package com.slipper.service.modules.system.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.service.modules.system.role.service.RoleMenuService;
import com.slipper.service.modules.system.role.dao.RoleMenuDao;
import com.slipper.service.modules.system.role.entity.RoleMenuEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色菜单关联
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenuEntity> implements RoleMenuService {

    @Override
    public List<Long> queryMenuIdByRoleId(List<Long> roleIds) {
        return baseMapper.queryMenuIdByRoleId(roleIds);
    }

    @Override
    public void createOrUpdate(Long roleId, List<Long> menuIds) {
        QueryWrapper<RoleMenuEntity> wrapper = new QueryWrapper<RoleMenuEntity>()
                .eq("role_id", roleId);
        this.remove(wrapper);

        if (menuIds.size() == 0) {
            return;
        }

        List<RoleMenuEntity> roleMenuEntities = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenuEntity roleMenu = new RoleMenuEntity();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuEntities.add(roleMenu);
        }

        this.saveBatch(roleMenuEntities);
    }
}