package com.slipper.service.modules.administrator.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.slipper.service.modules.administrator.entity.AdministratorEntity;
import com.slipper.service.modules.administrator.model.dto.AdministratorRoleDTO;
import com.slipper.service.modules.administrator.model.vo.AdministratorOnlineVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface AdministratorDao extends BaseMapper<AdministratorEntity> {
    /**
     * 通过ID查询管理员
     * @param id 管理员ID
     * @return
     */
    AdministratorRoleDTO queryById(@Param("id") Long id);

    /**
     * 管理员分页列表 查询该企业下管理员所创建的管理员 超级管理员查询该企业下所有的管理员
     * @param page 分页参数
     * @param enterpriseId 企业ID
     * @param creator 创建者ID
     * @param supervisor 是否是超管：0-否 1-是
     * @param name 昵称 / 帐号
     * @param start 创建开始时间
     * @param end 创建结束时间
     * @return
     */
    IPage<AdministratorRoleDTO> queryPage(Page<AdministratorRoleDTO> page,
                                          @Param("enterpriseId") Long enterpriseId,
                                          @Param("creator") Long creator,
                                          @Param("supervisor") Integer supervisor,
                                          @Param("name") String name,
                                          @Param("start") String start,
                                          @Param("end") String end);

    /**
     * 分页列表 根据ID获取管理员
     * @param page 分页参数
     * @param ids 用户ID
     * @param name 昵称 / 帐号
     * @return
     */
    IPage<AdministratorOnlineVO> queryPageByIds(Page<AdministratorOnlineVO> page,
                                                @Param("ids") List<Long> ids,
                                                @Param("name") String name);
}
