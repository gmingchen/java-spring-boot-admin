package com.slipper.service.modules.log.operation.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.utils.Query;
import com.slipper.common.utils.RPage;
import com.slipper.service.model.PageModel;
import com.slipper.service.modules.log.operation.dao.OperationLogDao;
import com.slipper.service.modules.log.operation.entity.OperationLogEntity;
import com.slipper.service.modules.log.operation.model.form.OperationLogPageDateForm;
import com.slipper.service.modules.log.operation.model.vo.OperationLogVO;
import com.slipper.service.modules.log.operation.model.vo.OperationLogSimplifyVO;
import com.slipper.service.modules.log.operation.service.OperationLogService;
import org.springframework.stereotype.Service;

/**
 * 操作日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("operationLogService")
public class OperationLogServiceImpl extends ServiceImpl<OperationLogDao, OperationLogEntity> implements OperationLogService {

    @Override
    public RPage<OperationLogVO> queryPage(OperationLogPageDateForm pageDateForm, Long enterpriseId) {
        Page<OperationLogVO> page = new Query<OperationLogVO>().getPage(pageDateForm.getCurrent(), pageDateForm.getSize());
        pageDateForm.setId(enterpriseId);
        return new RPage<>(
                baseMapper.queryPage(
                        page,
                        pageDateForm.getOperation(),
                        pageDateForm.getName(),
                        pageDateForm.getStart(),
                        pageDateForm.getEnd(),
                        pageDateForm.getId()
                )
        );
    }

    @Override
    public RPage<OperationLogSimplifyVO> queryPage(PageModel pageModel, Long administratorId) {
        Page<OperationLogSimplifyVO> page = new Query<OperationLogSimplifyVO>().getPage(pageModel.getCurrent(), pageModel.getSize());

        return new RPage<>(
                baseMapper.queryPageByAdministrator(page,administratorId )
        );
    }

    @Override
    public void delete(Long enterpriseId) {
        baseMapper.delete(enterpriseId);
    }

    @Override
    public void truncate() {
        baseMapper.truncate();
    }
}
