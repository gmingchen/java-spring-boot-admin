package com.slipper.service.modules.system.token.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slipper.service.modules.system.token.entity.TokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TokenDao extends BaseMapper<TokenEntity> {

    TokenEntity queryByToken (@Param("token") String token);

    TokenEntity queryByAdminId (@Param("adminId") Long adminId);

}
