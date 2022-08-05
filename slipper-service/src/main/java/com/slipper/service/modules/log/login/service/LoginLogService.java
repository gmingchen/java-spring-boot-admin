package com.slipper.service.modules.log.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.common.utils.RPage;
import com.slipper.service.model.PageModel;
import com.slipper.service.modules.log.login.entity.LoginLogEntity;
import com.slipper.service.modules.log.login.model.form.LoginLogPageDateForm;
import com.slipper.service.modules.log.login.model.vo.LoginLogVO;
import com.slipper.service.modules.log.login.model.vo.LoginLogSimplifyVO;
import com.slipper.service.modules.log.login.model.vo.VisitsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 登录日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface LoginLogService extends IService<LoginLogEntity> {

    /**
     * 分页列表
     * @param pageDateForm 分页参数
     * @param enterpriseId 企业ID
     * @return
     */
    RPage<LoginLogVO> queryPage(LoginLogPageDateForm pageDateForm, Long enterpriseId);

    /**
     * 分页列表
     * @param pageModel 分页参数
     * @param administratorId 管理员ID
     * @return
     */
    RPage<LoginLogSimplifyVO> queryPage(PageModel pageModel, Long administratorId);

    /**
     * 删除企业日志
     * @param enterpriseId
     */
    void delete(Long enterpriseId);

    /**
     * 清空操作日志表
     */
    void truncate();

    /**
     * 查询最近登录量
     * @param day 天数
     * @return
     */
    List<VisitsVO> queryVisits(Integer day);

}
