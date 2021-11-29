package com.slipper.service.modules.log.operation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.utils.Query;
import com.slipper.common.utils.RPage;
import com.slipper.service.modules.log.operation.dao.OperationLogDao;
import com.slipper.service.modules.log.operation.entity.OperationLogEntity;
import com.slipper.service.modules.log.operation.service.OperationLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("operationLogService")
public class OperationLogServiceImpl extends ServiceImpl<OperationLogDao, OperationLogEntity> implements OperationLogService {

    @Override
    public RPage<OperationLogEntity> queryPage(Map<String, Object> params) {
        String username = (String)params.get("username");
        String operation = (String)params.get("operation");
        String ip = (String)params.get("ip");
        String start = (String)params.get("start");
        String end = (String)params.get("end");
        Page<OperationLogEntity> page = new Query<OperationLogEntity>().getPage(params);
        QueryWrapper<OperationLogEntity> wrapper = new QueryWrapper<OperationLogEntity>()
                .like(StringUtils.isNotBlank(username), "username", username)
                .like(StringUtils.isNotBlank(operation), "operation", operation)
                .like(StringUtils.isNotBlank(ip), "ip", ip)
                .ge(StringUtils.isNotBlank(start), "created_at", start)
                .le(StringUtils.isNotBlank(end), "created_at", end)
                .orderByDesc("created_at");

        return new RPage<>(this.page(page, wrapper));
    }

    @Override
    public void truncate() {
        baseMapper.truncate();
    }
}
