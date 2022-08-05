package com.slipper.service.modules.log.login.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.utils.Query;
import com.slipper.common.utils.RPage;
import com.slipper.service.model.PageModel;
import com.slipper.service.modules.log.login.dao.LoginLogDao;
import com.slipper.service.modules.log.login.entity.LoginLogEntity;
import com.slipper.service.modules.log.login.model.form.LoginLogPageDateForm;
import com.slipper.service.modules.log.login.model.vo.LoginLogVO;
import com.slipper.service.modules.log.login.model.vo.LoginLogSimplifyVO;
import com.slipper.service.modules.log.login.model.vo.VisitsVO;
import com.slipper.service.modules.log.login.service.LoginLogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 登录日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("loginLogService")
public class LoginLogServiceImpl extends ServiceImpl<LoginLogDao, LoginLogEntity> implements LoginLogService {

    @Override
    public RPage<LoginLogVO> queryPage(LoginLogPageDateForm pageDateForm, Long enterpriseId) {
        Page<LoginLogVO> page = new Query<LoginLogVO>().getPage(pageDateForm.getCurrent(), pageDateForm.getSize());
        pageDateForm.setId(enterpriseId);
        return new RPage<>(
                baseMapper.queryPage(
                        page,
                        pageDateForm.getName(),
                        pageDateForm.getStart(),
                        pageDateForm.getEnd(),
                        pageDateForm.getId()
                )
        );
    }

    @Override
    public RPage<LoginLogSimplifyVO> queryPage(PageModel pageModel, Long administratorId) {
        Page<LoginLogSimplifyVO> page = new Query<LoginLogSimplifyVO>().getPage(pageModel.getCurrent(), pageModel.getSize());

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

    @Override
    public List<VisitsVO> queryVisits(Integer day) {
        return baseMapper.queryVisits(day);
    }
}
