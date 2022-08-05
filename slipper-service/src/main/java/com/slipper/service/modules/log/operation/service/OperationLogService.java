package com.slipper.service.modules.log.operation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.common.utils.RPage;
import com.slipper.service.model.PageModel;
import com.slipper.service.modules.log.operation.entity.OperationLogEntity;
import com.slipper.service.modules.log.operation.model.form.OperationLogPageDateForm;
import com.slipper.service.modules.log.operation.model.vo.OperationLogVO;
import com.slipper.service.modules.log.operation.model.vo.OperationLogSimplifyVO;

/**
 * 操作日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface OperationLogService extends IService<OperationLogEntity> {

    /**
     * 分页列表
     * @param pageDateForm 分页参数
     * @param enterpriseId 企业ID
     * @return
     */
    RPage<OperationLogVO> queryPage(OperationLogPageDateForm pageDateForm, Long enterpriseId);

    /**
     * 分页列表
     * @param pageModel 分页参数
     * @param administratorId 管理员ID
     * @return
     */
    RPage<OperationLogSimplifyVO> queryPage(PageModel pageModel, Long administratorId);

    /**
     * 删除企业日志
     * @param enterpriseId
     */
    void delete(Long enterpriseId);

    /**
     * 清空操作日志表
     */
    void truncate();

}
