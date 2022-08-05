package com.slipper.service.modules.administrator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.common.utils.RPage;
import com.slipper.service.modules.administrator.entity.AdministratorEntity;
import com.slipper.service.modules.administrator.model.dto.AdministratorRoleDTO;
import com.slipper.service.modules.administrator.model.form.*;
import com.slipper.service.modules.administrator.model.vo.AdministratorOnlineVO;

import java.util.List;

/**
 * 管理员
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface AdministratorService extends IService<AdministratorEntity> {

    /**
     * 分页列表
     * @param pageForm 分页参数
     * @param administratorRoleDto 当前管理员
     * @return
     */
    RPage<AdministratorRoleDTO> queryPage(AdministratorPageDateForm pageForm, AdministratorRoleDTO administratorRoleDto);

    /**
     * 信息
     * @param id 管理员ID
     * @return
     */
    AdministratorRoleDTO queryInfo(Long id);

    /**
     * 更新基础信息
     * @param updateBasicForm 参数
     */
    void updateBasic(AdministratorUpdateBasicForm updateBasicForm);

    /**
     * 更新密码
     * @param updatePasswordForm
     */
    void updatePassword(AdministratorUpdatePasswordForm updatePasswordForm);

    /**
     * 创建
     * @param createOrUpdateForm 创建参数
     * @param enterpriseId 企业ID
     * @param administratorId 创建者ID
     */
    void create(AdministratorCreateOrUpdateForm createOrUpdateForm, Long enterpriseId, Long administratorId);

    /**
     * 更新
     * @param createOrUpdateForm 编辑参数
     * @param administratorId 更新者ID
     */
    void update(AdministratorCreateOrUpdateForm createOrUpdateForm, Long administratorId);

    /**
     * 删除
     * @param ids ID列表
     */
    void delete(List<Long> ids);

    /* todo:--------------------------------------------------------------------------------------  */

    /**
     * 分页列表 企业ID获取企业下所有的管理员
     * @param pageForm 分页参数
     * @return
     */
    RPage<AdministratorRoleDTO> queryPage(EnterpriseAdministratorPageDateForm pageForm);

    /**
     * 分页列表 根据ID获取管理员
     * @param pageForm
     * @param ids
     * @return
     */
    RPage<AdministratorOnlineVO> queryPage(AdministratorOnlinePageForm pageForm, List<Long> ids);

}
