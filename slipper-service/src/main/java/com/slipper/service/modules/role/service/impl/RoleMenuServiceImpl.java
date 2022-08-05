package com.slipper.service.modules.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.exception.RunException;
import com.slipper.service.modules.role.dao.RoleMenuDao;
import com.slipper.service.modules.role.entity.RoleMenuEntity;
import com.slipper.service.modules.role.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色-菜单
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenuEntity> implements RoleMenuService {

    @Transactional(rollbackFor = RunException.class)
    @Override
    public void createOrUpdateBatch(Long roleId, List<Long> enterpriseMenuIds) {
        // 获取该角色已经存在的企业菜单
        LambdaQueryWrapper<RoleMenuEntity> queryWrapper = new LambdaQueryWrapper<RoleMenuEntity>()
                .eq(RoleMenuEntity::getRoleId, roleId);
        List<RoleMenuEntity> roleMenuEntities = this.list(queryWrapper);

        // 需要删除的ID
        List<Long> deleteIds = new ArrayList<>();
        // 不需要修改的企业菜单ID
        List<Long> unmodifiedIds = new ArrayList<>();
        for (RoleMenuEntity roleMenuEntity : roleMenuEntities) {
            if (!enterpriseMenuIds.contains(roleMenuEntity.getEnterpriseMenuId())) {
                deleteIds.add(roleMenuEntity.getId());
            } else {
                unmodifiedIds.add(roleMenuEntity.getEnterpriseMenuId());
            }
        }
        // 需要创建的企业菜单ID
        List<Long> createIds = new ArrayList<>();
        for (Long enterpriseMenuId : enterpriseMenuIds) {
            if (!unmodifiedIds.contains(enterpriseMenuId)) {
                createIds.add(enterpriseMenuId);
            }
        }

        // 删除
        if (deleteIds.size() > 0) {
            LambdaQueryWrapper<RoleMenuEntity> deleteWrapper = new LambdaQueryWrapper<RoleMenuEntity>()
                    .in(RoleMenuEntity::getId, deleteIds);
            this.remove(deleteWrapper);
        }

        // 创建
        if (createIds.size() > 0) {
            baseMapper.createBatch(roleId, createIds);
        }
    }
}
