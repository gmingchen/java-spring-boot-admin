package com.slipper.service.modules.administrator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slipper.service.modules.administrator.entity.AdministratorRoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员-角色
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface AdministratorRoleDao extends BaseMapper<AdministratorRoleEntity> {

    /**
     * 批量创建
     * @param administratorId 管理员ID
     * @param roleIds 角色ID
     */
    void createBatch(@Param("administratorId") Long administratorId, @Param("roleIds") List<Long> roleIds);

}
