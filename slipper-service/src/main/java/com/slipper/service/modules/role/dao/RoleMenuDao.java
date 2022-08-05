package com.slipper.service.modules.role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slipper.service.modules.role.entity.RoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色-菜单
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface RoleMenuDao extends BaseMapper<RoleMenuEntity> {

    /**
     * 批量创建
     * @param roleId 角色ID
     * @param enterpriseMenuIds 企业菜单ID
     */
    void createBatch(@Param("roleId") Long roleId, @Param("enterpriseMenuIds") List<Long> enterpriseMenuIds);
}
