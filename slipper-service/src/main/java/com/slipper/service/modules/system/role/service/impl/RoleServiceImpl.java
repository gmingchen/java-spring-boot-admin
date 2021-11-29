package com.slipper.service.modules.system.role.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.utils.Constant;
import com.slipper.service.modules.system.role.service.RoleMenuService;
import com.slipper.common.exception.RunException;
import com.slipper.common.utils.Common;
import com.slipper.common.utils.Query;
import com.slipper.common.utils.RPage;
import com.slipper.service.modules.system.role.dao.RoleDao;
import com.slipper.service.modules.system.role.dao.RoleMenuDao;
import com.slipper.service.modules.system.role.dto.RoleDto;
import com.slipper.service.modules.system.role.dto.RoleSelectDto;
import com.slipper.service.modules.system.role.entity.RoleEntity;
import com.slipper.service.modules.system.role.service.RoleService;
import com.slipper.service.modules.system.admin.dto.AdminDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private RoleMenuDao roleMenuDao;

    @Override
    public RPage<RoleDto> queryPage(Map<String, Object> params) {
        Page<RoleDto> page = new Query<RoleDto>().getPage(params);
        Long adminId = (Long)params.get("adminId");
        String name = (String)params.get("name");
        RPage<RoleDto> rPage;
        boolean isSuper = Common.isSuper(adminId);
        if (isSuper) {
            rPage = new RPage<>(baseMapper.queryPage(page, null, name, null));
        } else {
            rPage = new RPage<>(baseMapper.queryPage(page, adminId, name, 1));
        }
        return rPage;
    }

    @Override
    public RoleDto queryById(Long id, Long adminId) {
        RoleDto roleDto = baseMapper.queryById(id);
        return roleDto;
    }

    @Override
    @Transactional
    public void create(RoleEntity roleEntity, AdminDto adminDto) {
        validatedPermission(roleEntity, adminDto);

        roleEntity.setCreator(adminDto.getId());
        roleEntity.setCreatedAt(new Date());
        this.save(roleEntity);

        roleMenuService.createOrUpdate(roleEntity.getId(), roleEntity.getMenuIds());
    }

    @Override
    @Transactional
    public void update(RoleEntity roleEntity, AdminDto adminDto) {
        validatedPermission(roleEntity, adminDto);

        roleEntity.setUpdater(adminDto.getId());
        roleEntity.setUpdatedAt(new Date());
        this.updateById(roleEntity);

        roleMenuService.createOrUpdate(roleEntity.getId(), roleEntity.getMenuIds());
    }

    @Override
    public List<RoleSelectDto> querySelect(Long adminId) {
        Integer isDisplay;
        Long creator;
        boolean isSuper = Common.isSuper(adminId);
        if (isSuper) {
            isDisplay = null;
            creator = null;
        } else {
            isDisplay = 1;
            creator = adminId;
        }
        return baseMapper.querySelect(isDisplay, creator);
    }

    /**
     * 检查是否越权
     * @param roleEntity
     * @param adminDto
     */
    private void validatedPermission(RoleEntity roleEntity, AdminDto adminDto){
        // 判断是否是超级管理员
        if (Common.isSuper(adminDto.getId())) {
            return;
        }
        // 获取用户角色拥有的菜单ID
        List<Long> roleIds = new ArrayList<>();
        for(RoleDto role : adminDto.getRoles()) {
            roleIds.add(role.getId());
        }
        List<Long> menuIds = roleMenuService.queryMenuIdByRoleId(roleIds);
        // 判断是否越权
        if (!menuIds.containsAll(roleEntity.getMenuIds())) {
            throw new RunException(Constant.WARNING_CODE, "该角色权限已超出你的权限!");
        }
    }

}