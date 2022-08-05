package com.slipper.service.modules.enterprise.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.service.modules.administrator.model.dto.AdministratorRoleDTO;
import com.slipper.service.modules.enterprise.entity.EnterpriseMenuEntity;
import com.slipper.service.modules.enterprise.model.form.EnterpriseMenuModifyForm;
import com.slipper.service.modules.enterprise.model.form.EnterpriseMenuUpdateForm;
import com.slipper.service.modules.enterprise.model.vo.EnterpriseMenuSimplifyTreeVO;
import com.slipper.service.modules.enterprise.model.vo.EnterpriseMenuTreeVO;
import com.slipper.service.modules.enterprise.model.vo.EnterpriseMenuVO;

import java.util.List;

/**
 * 企业菜单权限
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface EnterpriseMenuService extends IService<EnterpriseMenuEntity> {

    /**
     * 获取用户拥有的权限、菜单
     * @param administratorRoleDto 管理员
     * @return
     */
    EnterpriseMenuVO queryPermissionAndMenuByAdministrator(AdministratorRoleDTO administratorRoleDto);

    /**
     * 列表
     * @param enterpriseId 企业ID
     * @return
     */
    List<EnterpriseMenuTreeVO> list(Long enterpriseId);

    /**
     * 编辑企业菜单
     * @param enterpriseMenuForm
     */
    void update(EnterpriseMenuUpdateForm enterpriseMenuForm);

    /**
     * 获取取管理员在该企业下的菜单权限 只包括名称 id
     * 超级管理员 返回所有 否则 返回该用户角色下面的所有
     * @param administratorRoleDto
     * @return
     */
    List<EnterpriseMenuSimplifyTreeVO> queryListByAdministrator(AdministratorRoleDTO administratorRoleDto);

    /* todo:--------------------------------------------------------------------------------------  */

    /**
     * 获取企业下的企业菜单ID
     * @param enterpriseId 企业ID
     * @return
     */
    List<Long> queryMenuIdByEnterpriseId(Long enterpriseId);

    /**
     * 修改企业菜单权限
     * @param modifyForm 企业id 菜单列表
     * @param administratorId 管理员ID
     */
    void modify(EnterpriseMenuModifyForm modifyForm, Long administratorId);

    /**
     * 删除企业菜单全新啊
     * @param enterpriseMenuId 企业菜单ID
     */
    void delete(Long enterpriseMenuId);

    /**
     * 获取该企业下的所有菜单权限 只包括名称 id
     * @param enterpriseId 企业ID
     * @return
     */
    List<EnterpriseMenuSimplifyTreeVO> queryListByEnterpriseId(Long enterpriseId);

}
