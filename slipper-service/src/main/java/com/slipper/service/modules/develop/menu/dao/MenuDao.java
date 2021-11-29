package com.slipper.service.modules.develop.menu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slipper.service.modules.develop.menu.dto.MenuDto;
import com.slipper.service.modules.develop.menu.dto.MenuSelectDto;
import com.slipper.service.modules.develop.menu.entity.MenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单权限
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface MenuDao extends BaseMapper<MenuEntity> {

    /**
     * 获取所有的权限
     * @return
     */
    List<String> queryAllPermission();

    /**
     * 获取用户拥有的权限
     * @param adminId 管理員ID
     * @return
     */
    List<String> queryPermissionByAdminId(@Param("adminId") Long adminId);

    /**
     * 根据父级ID获取菜单
     * @param parentId
     * @return
     */
    List<MenuDto> queryMenuByParentId(@Param("parentId") Long parentId, @Param("menuIds") List<Long> menuIds);

    /**
     * 获取所有菜单ID
     * @return
     */
    List<Long> queryAllMenuId();

    /**
     * 获取未删除的菜单
     * @param parentId 父级ID
     * @return
     */
    List<MenuDto> queryAllMenuByParentId(@Param("parentId") Long parentId);

    /**
     * 获取所有有效菜单 非树形
     * @param types 需要查询的类型
     * @param menuIds 菜单ID
     * @param isDisplay 是否显示
     * @return
     */
    List<MenuSelectDto> queryAllMenu(@Param("types") List<Integer> types,
                                     @Param("menuIds") List<Long> menuIds,
                                     @Param("isDisplay") Integer isDisplay);

    /**
     * 信息
     * @param id
     * @return
     */
    MenuDto queryById(@Param("id") Long id);

}
