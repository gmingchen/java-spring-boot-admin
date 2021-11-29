package com.slipper.service.modules.system.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slipper.service.modules.system.admin.entity.AdminRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理员角色关联
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface AdminRoleDao extends BaseMapper<AdminRoleEntity> {
	
}
