package com.slipper.service.modules.administrator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.service.modules.administrator.entity.AdministratorRoleEntity;

import java.util.List;

/**
 * 管理员-角色
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface AdministratorRoleService extends IService<AdministratorRoleEntity> {

    /**
     * 批量添加 管理员 与 角色的关联
     * @param administratorId 管理员ID
     * @param roleIds 角色ID数组
     */
    void createOrUpdateBatch(Long administratorId, List<Long> roleIds);
}
