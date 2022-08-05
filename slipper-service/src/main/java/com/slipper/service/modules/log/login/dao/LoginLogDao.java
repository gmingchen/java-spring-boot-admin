package com.slipper.service.modules.log.login.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.slipper.service.modules.log.login.entity.LoginLogEntity;
import com.slipper.service.modules.log.login.model.vo.LoginLogVO;
import com.slipper.service.modules.log.login.model.vo.LoginLogSimplifyVO;
import com.slipper.service.modules.log.login.model.vo.VisitsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface LoginLogDao extends BaseMapper<LoginLogEntity> {

    /**
     * 分页列表
     * @param page 分页参数
     * @param name 昵称 / 用户名
     * @param start 开始时间
     * @param end 结束时间
     * @param enterpriseId 企业ID
     * @return
     */
    IPage<LoginLogVO> queryPage(
            Page<LoginLogVO> page,
            @Param("name") String name,
            @Param("start") String start,
            @Param("end") String end,
            @Param("enterpriseId") Long enterpriseId
    );

    /**
     * 分页列表
     * @param page 分页参数
     * @param administratorId 管理员ID
     * @return
     */
    IPage<LoginLogSimplifyVO> queryPageByAdministrator(Page<LoginLogSimplifyVO> page, @Param("administratorId") Long administratorId);

    /**
     * 删除
     * @param enterpriseId
     */
    void delete(@Param("enterpriseId") Long enterpriseId);

    /**
     * 清空操作日志表
     */
    void truncate();

    /**
     * 查询最近登录量
     * @param day 天数
     * @return
     */
    List<VisitsVO> queryVisits(@Param("day") Integer day);

}
