package com.slipper.service.modules.enterprise.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.common.utils.RPage;
import com.slipper.service.modules.enterprise.entity.EnterpriseEntity;
import com.slipper.service.modules.enterprise.model.form.EnterpriseCreateOrUpdateForm;
import com.slipper.service.modules.enterprise.model.form.EnterprisePageDateForm;
import com.slipper.service.modules.enterprise.model.form.EnterprisePageForm;
import com.slipper.service.modules.enterprise.model.vo.EnterpriseSimplifyVO;
import com.slipper.service.modules.enterprise.model.vo.EnterpriseVO;

import java.util.List;

/**
 * 企业
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface EnterpriseService extends IService<EnterpriseEntity> {

    /**
     * 企业分页数据
     * @param PageForm 分页参数
     * @return
     */
    RPage<EnterpriseVO> queryPage(EnterprisePageDateForm PageForm);

    /**
     * 创建
     * @param createOrUpdateForm 创建参数
     * @param administratorId 创建用户ID
     */
    void create(EnterpriseCreateOrUpdateForm createOrUpdateForm, Long administratorId);

    /**
     * 更新
     * @param createOrUpdateForm 更新参数
     * @param administratorId 更新用户ID
     */
    void update(EnterpriseCreateOrUpdateForm createOrUpdateForm, Long administratorId);

    /**
     * 删除
     * @param ids ID列表
     */
    void delete(List<Long> ids);

    /* todo:--------------------------------------------------------------------------------------  */

    /**
     * 企业分页列表 精简信息
     * @param PageForm 分页参数
     * @return
     */
    RPage<EnterpriseSimplifyVO> queryPage(EnterprisePageForm PageForm);

}
