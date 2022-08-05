package com.slipper.service.modules.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.exception.RunException;
import com.slipper.common.utils.BeanUtils;
import com.slipper.common.utils.Query;
import com.slipper.common.utils.RPage;
import com.slipper.service.modules.administrator.model.dto.AdministratorRoleDTO;
import com.slipper.service.modules.role.dao.RoleDao;
import com.slipper.service.modules.role.entity.RoleEntity;
import com.slipper.service.modules.role.entity.RoleMenuEntity;
import com.slipper.service.modules.role.model.form.EnterpriseRolePageForm;
import com.slipper.service.modules.role.model.form.RoleCreateOrUpdateForm;
import com.slipper.service.modules.role.model.form.RolePageForm;
import com.slipper.service.modules.role.model.vo.RoleInfoVO;
import com.slipper.service.modules.role.model.vo.RoleVO;
import com.slipper.service.modules.role.service.RoleMenuService;
import com.slipper.service.modules.role.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleEntity> implements RoleService {

    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public RPage<RoleVO> queryPage(RolePageForm pageForm, AdministratorRoleDTO administratorRoleDto) {
        Page<RoleEntity> page = new Query<RoleEntity>().getPage(pageForm.getCurrent(), pageForm.getSize());
        LambdaQueryWrapper<RoleEntity> wrapper = new LambdaQueryWrapper<RoleEntity>()
                .eq(administratorRoleDto.getEnterpriseId() != null, RoleEntity::getEnterpriseId, administratorRoleDto.getEnterpriseId())
                .eq(administratorRoleDto.getSupervisor() != 1, RoleEntity::getCreator, administratorRoleDto.getId())
                .like(StringUtils.isNotBlank(pageForm.getName()), RoleEntity::getName, pageForm.getName())
                .orderByDesc(RoleEntity::getShow, RoleEntity::getCreatedAt);
        IPage<RoleEntity> iPage = this.page(page, wrapper);
        List<RoleVO> result = BeanUtils.copyProperties(iPage.getRecords(), RoleVO.class);
        return new RPage<>(iPage, result);
    }

    @Override
    public RoleInfoVO queryInfo(Long id) {
        return baseMapper.queryInfo(id);
    }

    @Transactional(rollbackFor = RunException.class)
    @Override
    public void create(RoleCreateOrUpdateForm createOrUpdateForm, Long enterpriseId, Long administratorId) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(createOrUpdateForm, roleEntity);
        roleEntity.setId(null);
        roleEntity.setEnterpriseId(enterpriseId);
        roleEntity.setCreator(administratorId);
        roleEntity.setCreatedAt(new Date());
        this.save(roleEntity);

        roleMenuService.createOrUpdateBatch(roleEntity.getId(), createOrUpdateForm.getEnterpriseMenuIds());
    }

    @Transactional(rollbackFor = RunException.class)
    @Override
    public void update(RoleCreateOrUpdateForm createOrUpdateForm, Long administratorId) {
        RoleEntity roleEntity = new RoleEntity();
        BeanUtils.copyProperties(createOrUpdateForm, roleEntity);
        roleEntity.setEnterpriseId(null);
        roleEntity.setUpdater(administratorId);
        roleEntity.setUpdatedAt(new Date());
        this.updateById(roleEntity);

        roleMenuService.createOrUpdateBatch(roleEntity.getId(), createOrUpdateForm.getEnterpriseMenuIds());
    }

    @Transactional(rollbackFor = RunException.class)
    @Override
    public void delete(List<Long> ids) {
        LambdaQueryWrapper<RoleEntity> roleWrapper = new LambdaQueryWrapper<RoleEntity>()
                .in(RoleEntity::getId, ids);
        this.remove(roleWrapper);

        LambdaQueryWrapper<RoleMenuEntity> roleMenuWrapper = new LambdaQueryWrapper<RoleMenuEntity>()
                .in(RoleMenuEntity::getRoleId, ids);
        roleMenuService.remove(roleMenuWrapper);
    }

    @Override
    public List<RoleVO> queryListByAdministrator(AdministratorRoleDTO administratorRoleDto) {
        LambdaQueryWrapper<RoleEntity> wrapper = new LambdaQueryWrapper<>();

        if (administratorRoleDto.getSupervisor() == 1) {
            wrapper.eq(RoleEntity::getEnterpriseId, administratorRoleDto.getEnterpriseId());
        } else {
            wrapper.eq(RoleEntity::getCreator, administratorRoleDto.getId());
        }
        wrapper.orderByDesc(RoleEntity::getShow, RoleEntity::getCreatedAt);

        return BeanUtils.copyProperties(this.list(wrapper), RoleVO.class);
    }

    @Override
    public RPage<RoleVO> queryPage(EnterpriseRolePageForm pageForm) {
        Page<RoleEntity> page = new Query<RoleEntity>().getPage(pageForm.getCurrent(), pageForm.getSize());
        LambdaQueryWrapper<RoleEntity> wrapper = new LambdaQueryWrapper<RoleEntity>()
                .eq(RoleEntity::getEnterpriseId, pageForm.getId())
                .like(StringUtils.isNotBlank(pageForm.getName()), RoleEntity::getName, pageForm.getName())
                .orderByDesc(RoleEntity::getShow, RoleEntity::getCreatedAt);
        IPage<RoleEntity> iPage = this.page(page, wrapper);
        List<RoleVO> result = BeanUtils.copyProperties(iPage.getRecords(), RoleVO.class);
        return new RPage<>(iPage, result);
    }

    @Override
    public List<RoleVO> queryListByEnterpriseId(Long enterpriseId) {
        LambdaQueryWrapper<RoleEntity> wrapper = new LambdaQueryWrapper<RoleEntity>()
                .eq(RoleEntity::getEnterpriseId, enterpriseId)
                .orderByDesc(RoleEntity::getShow, RoleEntity::getCreatedAt);
        return BeanUtils.copyProperties(this.list(wrapper), RoleVO.class);
    }

}
