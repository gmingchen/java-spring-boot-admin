package com.slipper.service.modules.system.role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.slipper.service.modules.system.role.dto.RoleDto;
import com.slipper.service.modules.system.role.dto.RoleSelectDto;
import com.slipper.service.modules.system.role.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface RoleDao extends BaseMapper<RoleEntity> {

    /**
     * 分页
     * @param page 分页对象
     * @param adminId 创建者
     * @param isDisplay 是否显示
     * @return
     */
    IPage<RoleDto> queryPage(Page<RoleDto> page,
                             @Param("adminId") Long adminId,
                             @Param("name") String name,
                             @Param("isDisplay") Integer isDisplay);

    /**
     * ID查询信息
     * @param id key
     * @return
     */
    RoleDto queryById(@Param("id") Long id);

    /**
     * 查询下拉列表
     * @param isDisplay 是否显示
     * @param adminId 创建者
     * @return
     */
    List<RoleSelectDto> querySelect(@Param("isDisplay") Integer isDisplay, @Param("adminId") Long adminId);

}
