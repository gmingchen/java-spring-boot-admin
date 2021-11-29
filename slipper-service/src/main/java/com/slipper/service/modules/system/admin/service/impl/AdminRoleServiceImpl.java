package com.slipper.service.modules.system.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.service.modules.system.admin.dao.AdminRoleDao;
import com.slipper.service.modules.system.admin.entity.AdminRoleEntity;
import com.slipper.service.modules.system.admin.service.AdminRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("adminRoleService")
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleDao, AdminRoleEntity> implements AdminRoleService {

    @Override
    public void createOrUpdate(Long adminId, List<Long> roleIds) {
        QueryWrapper<AdminRoleEntity> wrapper = new QueryWrapper<AdminRoleEntity>()
                .eq("admin_id", adminId);
        this.remove(wrapper);

        if (roleIds.size() == 0) {
            return;
        }

        List<AdminRoleEntity> adminRoleEntities = new ArrayList<>();
        for (Long roleId : roleIds) {
            AdminRoleEntity adminRole = new AdminRoleEntity();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId);
            adminRoleEntities.add(adminRole);
        }

        this.saveBatch(adminRoleEntities);
    }
}