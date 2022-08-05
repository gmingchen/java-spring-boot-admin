package com.slipper.service.modules.administrator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.exception.RunException;
import com.slipper.service.modules.administrator.dao.AdministratorRoleDao;
import com.slipper.service.modules.administrator.entity.AdministratorRoleEntity;
import com.slipper.service.modules.administrator.service.AdministratorRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理员-角色
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("administratorRoleService")
public class AdministratorRoleServiceImpl extends ServiceImpl<AdministratorRoleDao, AdministratorRoleEntity> implements AdministratorRoleService {

    @Transactional(rollbackFor = RunException.class)
    @Override
    public void createOrUpdateBatch(Long administratorId, List<Long> roleIds) {
        // 获取该管理员已经存在的角色
        LambdaQueryWrapper<AdministratorRoleEntity> queryWrapper = new LambdaQueryWrapper<AdministratorRoleEntity>()
                .eq(AdministratorRoleEntity::getAdministratorId, administratorId);
        List<AdministratorRoleEntity> administratorRoleEntities = this.list(queryWrapper);

        // 需要删除的ID
        List<Long> deleteIds = new ArrayList<>();
        // 不需要修改的角色ID
        List<Long> unmodifiedIds = new ArrayList<>();
        for (AdministratorRoleEntity administratorRoleEntity : administratorRoleEntities) {
            if (!roleIds.contains(administratorRoleEntity.getRoleId())) {
                deleteIds.add(administratorRoleEntity.getId());
            } else {
                unmodifiedIds.add(administratorRoleEntity.getRoleId());
            }
        }
        // 需要创建的角色ID
        List<Long> createIds = new ArrayList<>();
        for (Long roleId : roleIds) {
            if (!unmodifiedIds.contains(roleId)) {
                createIds.add(roleId);
            }
        }

        // 删除
        if (deleteIds.size() > 0) {
            LambdaQueryWrapper<AdministratorRoleEntity> deleteWrapper = new LambdaQueryWrapper<AdministratorRoleEntity>()
                    .in(AdministratorRoleEntity::getId, deleteIds);
            this.remove(deleteWrapper);
        }

        // 创建
        if (createIds.size() > 0) {
            baseMapper.createBatch(administratorId, createIds);
        }
    }
}
