package com.slipper.service.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.exception.RunException;
import com.slipper.common.utils.Constant;
import com.slipper.service.modules.administrator.model.dto.AdministratorRoleDTO;
import com.slipper.service.modules.enterprise.dao.EnterpriseMenuDao;
import com.slipper.service.modules.enterprise.entity.EnterpriseMenuEntity;
import com.slipper.service.modules.enterprise.model.dto.EnterpriseMenuTreeDTO;
import com.slipper.service.modules.enterprise.model.form.EnterpriseMenuModifyForm;
import com.slipper.service.modules.enterprise.model.form.EnterpriseMenuUpdateForm;
import com.slipper.service.modules.enterprise.model.vo.EnterpriseMenuSimplifyTreeVO;
import com.slipper.service.modules.enterprise.model.vo.EnterpriseMenuTreeVO;
import com.slipper.service.modules.enterprise.model.vo.EnterpriseMenuVO;
import com.slipper.service.modules.enterprise.service.EnterpriseMenuService;
import com.slipper.service.modules.menu.entity.MenuEntity;
import com.slipper.service.modules.menu.service.MenuService;
import com.slipper.service.modules.role.model.dto.RoleSimplifyDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 企业菜单权限
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("enterpriseMenuService")
public class EnterpriseMenuServiceImpl extends ServiceImpl<EnterpriseMenuDao, EnterpriseMenuEntity> implements EnterpriseMenuService {

    @Resource
    private MenuService menuService;

    @Override
    public EnterpriseMenuVO queryPermissionAndMenuByAdministrator(AdministratorRoleDTO administratorRoleDto) {
        EnterpriseMenuVO enterpriseMenuVO = new EnterpriseMenuVO();
        enterpriseMenuVO.setPermissions(new HashSet<>());
        enterpriseMenuVO.setMenus(new ArrayList<>());

        List<EnterpriseMenuTreeDTO> list;
        if (administratorRoleDto.getSupervisor() == 1) {
            list = baseMapper.queryCompleteEnterpriseMenuByEnterpriseId(administratorRoleDto.getEnterpriseId());
        } else {
            list = baseMapper.queryCompleteEnterpriseMenuByAdministratorId(administratorRoleDto.getId());
        }
        // 筛选企业下根节点菜单 并 筛选出所有的权限
        List<EnterpriseMenuTreeDTO> rootList = list.stream().filter(item -> {
            if (StringUtils.isNotBlank(item.getPermission())) {
                enterpriseMenuVO.getPermissions().addAll(Arrays.asList(item.getPermission().split(";")));
            }
            return item.getParentId() == 0;
        }).collect(Collectors.toList());
        // 筛选子节点菜单
        List<EnterpriseMenuTreeDTO> finalList = list;
        rootList.forEach(item -> enterpriseMenuVO.getMenus().add(findChildren(item, finalList)));

        return enterpriseMenuVO;
    }

    @Override
    public List<EnterpriseMenuTreeVO> list(Long enterpriseId) {
        List<EnterpriseMenuTreeVO> list = baseMapper.queryEnterpriseMenuByEnterpriseId(enterpriseId);
        // 筛选企业下根节点菜单 并 筛选出所有的权限
        List<EnterpriseMenuTreeVO> rootList = list.stream().filter(item -> {
            return item.getParentId() == 0;
        }).collect(Collectors.toList());
        // 筛选子节点菜单
        List<EnterpriseMenuTreeVO> result = new ArrayList<>();
        rootList.forEach(item -> result.add(findChildren(item, list)));

        return result;
    }

    @Override
    public void update(EnterpriseMenuUpdateForm enterpriseMenuForm) {
        EnterpriseMenuEntity enterpriseMenuEntity = new EnterpriseMenuEntity();
        BeanUtils.copyProperties(enterpriseMenuForm, enterpriseMenuEntity);
        enterpriseMenuEntity.setUpdatedAt(new Date());
        this.updateById(enterpriseMenuEntity);
    }

    @Override
    public List<EnterpriseMenuSimplifyTreeVO> queryListByAdministrator(AdministratorRoleDTO administratorRoleDto) {
        List<EnterpriseMenuSimplifyTreeVO> list;
        if (administratorRoleDto.getSupervisor() == 1) {
            list = baseMapper.queryListByEnterpriseId(administratorRoleDto.getEnterpriseId());
        } else {
            List<Long> roleIds = new ArrayList<>();
            for (RoleSimplifyDTO role: administratorRoleDto.getRoles()) {
                roleIds.add(role.getId());
            }
            list = baseMapper.queryListByRoleId(roleIds);
        }

        // 筛选企业下根节点菜单 并 筛选出所有的权限
        List<EnterpriseMenuSimplifyTreeVO> rootList = list.stream().filter(item -> {
            return item.getParentId() == 0;
        }).collect(Collectors.toList());
        // 筛选子节点菜单
        List<EnterpriseMenuSimplifyTreeVO> result = new ArrayList<>();
        List<EnterpriseMenuSimplifyTreeVO> finalList = list;
        rootList.forEach(item -> result.add(findChildren(item, finalList)));
        return result;
    }

    @Override
    public List<Long> queryMenuIdByEnterpriseId(Long enterpriseId) {
        return baseMapper.queryMenuIdByEnterpriseId(enterpriseId);
    }

    @Override
    public void modify(EnterpriseMenuModifyForm modifyForm, Long administratorId) {
        // 获取已经存在的企业菜单
        LambdaQueryWrapper<EnterpriseMenuEntity> queryWrapper = new LambdaQueryWrapper<EnterpriseMenuEntity>()
                .eq(EnterpriseMenuEntity::getEnterpriseId, modifyForm.getEnterpriseId());
        List<EnterpriseMenuEntity> enterpriseMenuEntities = this.list(queryWrapper);

        // 需要删除的ID
        List<Long> deleteIds = new ArrayList<>();
        // 不需要修改的菜单ID
        List<Long> unmodifiedIds = new ArrayList<>();
        for (EnterpriseMenuEntity enterpriseMenuEntity : enterpriseMenuEntities) {
            if (!modifyForm.getMenuIds().contains(enterpriseMenuEntity.getMenuId())) {
                deleteIds.add(enterpriseMenuEntity.getId());
            } else {
                unmodifiedIds.add(enterpriseMenuEntity.getMenuId());
            }
        }
        // 需要创建的企业菜单ID
        List<Long> createIds = new ArrayList<>();
        for (Long menuId : modifyForm.getMenuIds()) {
            if (!unmodifiedIds.contains(menuId)) {
                createIds.add(menuId);
            }
        }
        // 删除
        if (deleteIds.size() > 0) {
            LambdaQueryWrapper<EnterpriseMenuEntity> deleteWrapper = new LambdaQueryWrapper<EnterpriseMenuEntity>()
                    .in(EnterpriseMenuEntity::getId, deleteIds);
            this.remove(deleteWrapper);
        }
        // 创建
        if (createIds.size() > 0) {
            baseMapper.createBatch(modifyForm.getEnterpriseId(), createIds, administratorId, new Date());
        }
    }

    @Override
    public void delete(Long enterpriseMenuId) {
        EnterpriseMenuEntity enterpriseMenuEntity = this.getById(enterpriseMenuId);
        if (enterpriseMenuEntity == null) {
            throw new RunException(Constant.WARNING_CODE, "该企业菜单不存在！");
        }

        LambdaQueryWrapper<MenuEntity> menuWrapper = new LambdaQueryWrapper<MenuEntity>()
                .eq(MenuEntity::getParentId, enterpriseMenuEntity.getMenuId());
        List<MenuEntity> list = menuService.list(menuWrapper);
        if (list.size() > 0) {
            List<Long> menuIds = new ArrayList<>();
            for (MenuEntity menu: list) {
                menuIds.add(menu.getId());
            }
            LambdaQueryWrapper<EnterpriseMenuEntity> enterpriseMenuWrapper = new LambdaQueryWrapper<EnterpriseMenuEntity>()
                    .eq(EnterpriseMenuEntity::getEnterpriseId, enterpriseMenuEntity.getEnterpriseId())
                    .in(EnterpriseMenuEntity::getMenuId, menuIds);
            Long count = this.count(enterpriseMenuWrapper);
            if (count > 0) {
                throw new RunException(Constant.WARNING_CODE, "请先删除子菜单权限！");
            }
        }

        this.removeById(enterpriseMenuId);
    }

    @Override
    public List<EnterpriseMenuSimplifyTreeVO> queryListByEnterpriseId(Long enterpriseId) {
        List<EnterpriseMenuSimplifyTreeVO> list = baseMapper.queryListByEnterpriseId(enterpriseId);
        // 筛选企业下根节点菜单 并 筛选出所有的权限
        List<EnterpriseMenuSimplifyTreeVO> rootList = list.stream().filter(item -> {
            return item.getParentId() == 0;
        }).collect(Collectors.toList());
        // 筛选子节点菜单
        List<EnterpriseMenuSimplifyTreeVO> result = new ArrayList<>();
        List<EnterpriseMenuSimplifyTreeVO> finalList = list;
        rootList.forEach(item -> result.add(findChildren(item, finalList)));
        return result;
    }

    /**
     * 筛选子节点菜单
     * @param enterpriseMenuTreeDto
     * @param list
     * @return
     */
    private EnterpriseMenuTreeDTO findChildren(EnterpriseMenuTreeDTO enterpriseMenuTreeDto, List<EnterpriseMenuTreeDTO> list) {
        list.stream().filter(item -> enterpriseMenuTreeDto.getMenuId().equals(item.getParentId()))
                .collect(Collectors.toList())
                .forEach(item -> {
                    if (enterpriseMenuTreeDto.getChildren() == null) {
                        enterpriseMenuTreeDto.setChildren(new ArrayList<>());
                    }
                    enterpriseMenuTreeDto.getChildren().add(findChildren(item, list));
                });
        return enterpriseMenuTreeDto;
    }

    /**
     * 筛选子节点菜单
     * @param enterpriseMenuTreeVO
     * @param list
     * @return
     */
    private EnterpriseMenuTreeVO findChildren(EnterpriseMenuTreeVO enterpriseMenuTreeVO, List<EnterpriseMenuTreeVO> list) {
        list.stream().filter(item -> enterpriseMenuTreeVO.getMenuId().equals(item.getParentId()))
                .collect(Collectors.toList())
                .forEach(item -> {
                    if (enterpriseMenuTreeVO.getChildren() == null) {
                        enterpriseMenuTreeVO.setChildren(new ArrayList<>());
                    }
                    enterpriseMenuTreeVO.getChildren().add(findChildren(item, list));
                });
        return enterpriseMenuTreeVO;
    }

    /**
     * 筛选子节点菜单
     * @param enterpriseMenuSimplifyTreeVO
     * @param list
     * @return
     */
    private EnterpriseMenuSimplifyTreeVO findChildren(EnterpriseMenuSimplifyTreeVO enterpriseMenuSimplifyTreeVO, List<EnterpriseMenuSimplifyTreeVO> list) {
        list.stream().filter(item -> enterpriseMenuSimplifyTreeVO.getMenuId().equals(item.getParentId()))
                .collect(Collectors.toList())
                .forEach(item -> {
                    if (enterpriseMenuSimplifyTreeVO.getChildren() == null) {
                        enterpriseMenuSimplifyTreeVO.setChildren(new ArrayList<>());
                    }
                    enterpriseMenuSimplifyTreeVO.getChildren().add(findChildren(item, list));
                });
        return enterpriseMenuSimplifyTreeVO;
    }
}
