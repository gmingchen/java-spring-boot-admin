package com.slipper.service.modules.system.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.slipper.service.modules.system.admin.dto.AdminDto;
import com.slipper.service.modules.system.admin.entity.AdminEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 管理员
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface AdminDao extends BaseMapper<AdminEntity> {

    IPage<AdminDto> queryAllPage(Page<AdminDto> page, @Param("username") String username, @Param("nickname") String nickname);

    IPage<AdminDto> queryByCreatorPage(Page<AdminDto> page, @Param("creator") Long creator, @Param("username") String username, @Param("nickname") String nickname);

    AdminDto queryById (@Param("id") Long id);

    AdminDto queryByUsername (@Param("username") String username);
	
}
