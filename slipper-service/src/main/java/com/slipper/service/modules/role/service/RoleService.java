package com.slipper.service.modules.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.common.utils.RPage;
import com.slipper.service.modules.administrator.model.dto.AdministratorRoleDTO;
import com.slipper.service.modules.role.entity.RoleEntity;
import com.slipper.service.modules.role.model.form.EnterpriseRolePageForm;
import com.slipper.service.modules.role.model.form.RoleCreateOrUpdateForm;
import com.slipper.service.modules.role.model.form.RolePageForm;
import com.slipper.service.modules.role.model.vo.RoleInfoVO;
import com.slipper.service.modules.role.model.vo.RoleVO;

import java.util.List;

/**
 * 角色
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface RoleService extends IService<RoleEntity> {

    /**
     * 分页 - 企业下所有角色 或者 企业下当前管理员创建的角色
     * @param pageForm 分页参数
     * @param administratorRoleDto 当前管理员
     * @return
     */
    RPage<RoleVO> queryPage(RolePageForm pageForm, AdministratorRoleDTO administratorRoleDto);

    /**
     * 详情
     * @param id
     * @return
     */
    RoleInfoVO queryInfo(Long id);

    /**
     * 创建
     * @param createOrUpdateForm 创建参数
     * @param enterpriseId 企业ID
     * @param administratorId 管理员ID
     * @return
     */
    void create(RoleCreateOrUpdateForm createOrUpdateForm, Long enterpriseId, Long administratorId);

    /**
     * 更新
     * @param createOrUpdateForm 更新参数
     * @param administratorId 管理员ID
     * @return
     */
    void update(RoleCreateOrUpdateForm createOrUpdateForm, Long administratorId);

    /**
     * 删除
     * @param ids ID列表
     */
    void delete(List<Long> ids);

    /**
     * 获取管理员创建的角色 超级管理员返回企业下所有的角色
     * @param administratorRoleDto
     * @return
     */
    List<RoleVO> queryListByAdministrator(AdministratorRoleDTO administratorRoleDto);

    /* todo:--------------------------------------------------------------------------------------  */

    /**
     * 分页 - 企业ID查询所有角色
     * @param pageForm 分页参数
     * @return
     */
    RPage<RoleVO> queryPage(EnterpriseRolePageForm pageForm);

    /**
     * 获取企业下所有的角色
     * @param enterpriseId 企业ID
     * @return
     */
    List<RoleVO> queryListByEnterpriseId(Long enterpriseId);

}
