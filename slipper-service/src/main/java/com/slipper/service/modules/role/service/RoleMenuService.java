package com.slipper.service.modules.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.service.modules.role.entity.RoleMenuEntity;

import java.util.List;

/**
 * 角色-菜单
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface RoleMenuService extends IService<RoleMenuEntity> {

    /**
     * 批量添加 角色 与 企业菜单的关联
     * @param roleId 角色ID
     * @param enterpriseMenuIds 企业菜单ID数组
     */
    void createOrUpdateBatch(Long roleId, List<Long> enterpriseMenuIds);

}
