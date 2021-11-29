package com.slipper.service.modules.system.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.common.utils.RPage;
import com.slipper.service.modules.system.role.dto.RoleDto;
import com.slipper.service.modules.system.role.dto.RoleSelectDto;
import com.slipper.service.modules.system.role.entity.RoleEntity;
import com.slipper.service.modules.system.admin.dto.AdminDto;

import java.util.List;
import java.util.Map;

/**
 * 角色
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface RoleService extends IService<RoleEntity> {

    /**
     * 分页 TODO: 超级管理员显示所有 非 显示当前管理员创建的
     * @param params 分页参数
     * @return
     */
    RPage<RoleDto> queryPage(Map<String, Object> params);

    /**
     * ID查询信息
     * @param id key
     * @return
     */
    RoleDto queryById(Long id, Long adminId);

    /**
     * 新增
     * @param roleEntity 角色实体
     * @param adminDto 管理员实体
     */
    void create(RoleEntity roleEntity, AdminDto adminDto);

    /**
     * 编辑
     * @param roleEntity 角色实体
     * @param adminDto 管理员实体
     */
    void update(RoleEntity roleEntity, AdminDto adminDto);

    /**
     * 查询角色下拉 TODO: 超级管理查询未删除的 当前管理员查询自己创建的
     * @param adminId
     * @return
     */
    List<RoleSelectDto> querySelect(Long adminId);

}

