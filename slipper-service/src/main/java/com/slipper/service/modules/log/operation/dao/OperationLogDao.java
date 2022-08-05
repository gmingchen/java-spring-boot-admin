package com.slipper.service.modules.log.operation.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.slipper.service.modules.log.operation.entity.OperationLogEntity;
import com.slipper.service.modules.log.operation.model.vo.OperationLogVO;
import com.slipper.service.modules.log.operation.model.vo.OperationLogSimplifyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 操作日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface OperationLogDao extends BaseMapper<OperationLogEntity> {

    /**
     * 分页列表
     * @param page 分页参数
     * @param operation 操作
     * @param name 昵称 / 用户名
     * @param start 开始时间
     * @param end 结束时间
     * @param enterpriseId 企业ID
     * @return
     */
    IPage<OperationLogVO> queryPage(
            Page<OperationLogVO> page,
            @Param("operation") String operation,
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
    IPage<OperationLogSimplifyVO> queryPageByAdministrator(Page<OperationLogSimplifyVO> page, @Param("administratorId") Long administratorId);

    /**
     * 删除
     * @param enterpriseId
     */
    void delete(@Param("enterpriseId") Long enterpriseId);

    /**
     * 清空操作日志表
     */
    void truncate();

}
