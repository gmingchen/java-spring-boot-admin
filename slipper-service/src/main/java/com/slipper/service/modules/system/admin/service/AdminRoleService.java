package com.slipper.service.modules.system.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.service.modules.system.admin.entity.AdminRoleEntity;

import java.util.List;

/**
 * 管理员
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2021-03-13 17:57:24
 */
public interface AdminRoleService extends IService<AdminRoleEntity> {

    /**
     * 管理员角色关联新增、编辑
     * @param adminId
     * @param roleIds
     */
    public void createOrUpdate(Long adminId, List<Long> roleIds);


}

