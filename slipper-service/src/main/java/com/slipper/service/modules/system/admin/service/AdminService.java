package com.slipper.service.modules.system.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.common.utils.RPage;
import com.slipper.service.modules.system.admin.dto.AdminDto;
import com.slipper.service.modules.system.admin.entity.AdminEntity;
import com.slipper.service.modules.system.admin.vo.EditAdminVo;

import java.util.Map;

/**
 * 管理员
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface AdminService extends IService<AdminEntity> {

    /**
     * 查询系统管理员
     * @param params 分页参数
     * @return
     */
    RPage<AdminDto> queryPage(Map<String, Object> params);

    /**
     * 管理员ID查询管理员信息
     * @param id 管理员ID
     * @return
     */
    AdminDto queryById (Long id);

    /**
     * 管理员名查询管理员信息
     * @param username 管理员名
     * @return
     */
    AdminDto queryByUsername (String username);

    /**
     * 新增
     *
     * @param adminEntity
     * @param adminDto
     */
    void create(AdminEntity adminEntity, AdminDto adminDto);

    /**
     * 编辑
     * @param adminEntity 管理员实体
     * @param adminDto 管理员Dto
     */
    void update(AdminEntity adminEntity, AdminDto adminDto);

    /**
     * 编辑信息
     * @param editAdminVo 修改信息Vo
     * @return int
     */
    int selfUpdate(EditAdminVo editAdminVo);

}

