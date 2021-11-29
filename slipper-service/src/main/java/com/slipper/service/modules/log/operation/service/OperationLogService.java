package com.slipper.service.modules.log.operation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.common.utils.RPage;
import com.slipper.service.modules.log.operation.entity.OperationLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface OperationLogService extends IService<OperationLogEntity> {

    /**
     * 分页列表
     * @param params 分页参数
     * @return RPage
     */
    RPage<OperationLogEntity> queryPage(Map<String, Object> params);

    /**
     * 日志清理
     */
    void truncate ();

}
