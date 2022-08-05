package com.slipper.service.modules.enterprise.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slipper.service.modules.enterprise.entity.EnterpriseMenuEntity;
import com.slipper.service.modules.enterprise.model.dto.EnterpriseMenuTreeDTO;
import com.slipper.service.modules.enterprise.model.vo.EnterpriseMenuSimplifyTreeVO;
import com.slipper.service.modules.enterprise.model.vo.EnterpriseMenuTreeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 企业菜单权限
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface EnterpriseMenuDao extends BaseMapper<EnterpriseMenuEntity> {

    /**
     * 获取企业拥有的权限
     * @param id 企业ID
     * @return
     */
    List<String> queryPermissionByEnterpriseId(@Param("id") Long id);

    /**
     * 获取企业拥有的菜单权限
     * @param id 企业ID
     * @return
     */
    List<EnterpriseMenuTreeDTO> queryCompleteEnterpriseMenuByEnterpriseId(@Param("id") Long id);

    /**
     * 获取管理員拥有的权限
     * @param id 管理員ID
     * @return
     */
    List<String> queryPermissionByAdministratorId(@Param("id") Long id);

    /**
     * 获取管理員拥有的菜单权限
     * @param id 管理員ID
     * @return
     */
    List<EnterpriseMenuTreeDTO> queryCompleteEnterpriseMenuByAdministratorId(@Param("id") Long id);

    /**
     * 获取企业拥有的菜单权限
     * @param id 企业ID
     * @return
     */
    List<EnterpriseMenuTreeVO> queryEnterpriseMenuByEnterpriseId(@Param("id") Long id);

    /**
     * 获取企业下的菜单权限 只包括名称 id
     * @param enterpriseId 企业ID
     * @return
     */
    List<EnterpriseMenuSimplifyTreeVO> queryListByEnterpriseId(Long enterpriseId);

    /**
     * 获取角色下面的菜单权限 只包括名称 id
     * @param roleIds 角色ID数组
     * @return
     */
    List<EnterpriseMenuSimplifyTreeVO> queryListByRoleId(List<Long> roleIds);

    /**
     * 获取企业下的企业菜单ID
     * @param enterpriseId 企业ID
     * @return
     */
    List<Long> queryMenuIdByEnterpriseId(@Param("enterpriseId") Long enterpriseId);

    /**
     * 批量创建
     * @param enterpriseId 企业ID
     * @param menuIds 菜单ID
     * @param administratorId 管理员ID
     * @param createdAt 创建时间
     */
    void createBatch(
            @Param("enterpriseId") Long enterpriseId,
            @Param("menuIds") List<Long> menuIds,
            @Param("administratorId") Long administratorId,
            @Param("createdAt") Date createdAt
    );
}
