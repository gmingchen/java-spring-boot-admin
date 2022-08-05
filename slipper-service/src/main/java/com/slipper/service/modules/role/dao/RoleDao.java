package com.slipper.service.modules.role.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slipper.service.modules.role.entity.RoleEntity;
import com.slipper.service.modules.role.model.vo.RoleInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 信息
     * @param id
     * @return
     */
    RoleInfoVO queryInfo(@Param("id") Long id);
}
