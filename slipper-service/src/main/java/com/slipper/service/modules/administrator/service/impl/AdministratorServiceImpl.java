package com.slipper.service.modules.administrator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.exception.RunException;
import com.slipper.common.utils.Constant;
import com.slipper.common.utils.Query;
import com.slipper.common.utils.RPage;
import com.slipper.service.modules.administrator.dao.AdministratorDao;
import com.slipper.service.modules.administrator.entity.AdministratorEntity;
import com.slipper.service.modules.administrator.entity.AdministratorRoleEntity;
import com.slipper.service.modules.administrator.model.dto.AdministratorRoleDTO;
import com.slipper.service.modules.administrator.model.form.*;
import com.slipper.service.modules.administrator.model.vo.AdministratorOnlineVO;
import com.slipper.service.modules.administrator.service.AdministratorRoleService;
import com.slipper.service.modules.administrator.service.AdministratorService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 管理员
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("administratorService")
public class AdministratorServiceImpl extends ServiceImpl<AdministratorDao, AdministratorEntity> implements AdministratorService {

    @Resource
    private AdministratorRoleService administratorRoleService;

    @Override
    public RPage<AdministratorRoleDTO> queryPage(AdministratorPageDateForm pageForm, AdministratorRoleDTO administratorRoleDto) {
        Page<AdministratorRoleDTO> page = new Query<AdministratorRoleDTO>().getPage(pageForm.getCurrent(), pageForm.getSize());
        IPage<AdministratorRoleDTO> result;
        if (administratorRoleDto.getSupervisor() == 1) {
            result = baseMapper.queryPage(
                    page,
                    administratorRoleDto.getEnterpriseId(),
                    null,
                    0,
                    pageForm.getName(),
                    pageForm.getStart(),
                    pageForm.getEnd()
            );
        } else {
            result = baseMapper.queryPage(
                    page,
                    administratorRoleDto.getEnterpriseId(),
                    administratorRoleDto.getId(),
                    0,
                    pageForm.getName(),
                    pageForm.getStart(),
                    pageForm.getEnd()
            );
        }
        return new RPage<>(result);
    }

    @Override
    public AdministratorRoleDTO queryInfo(Long id) {
        return baseMapper.queryById(id);
    }

    @Override
    public void updateBasic(AdministratorUpdateBasicForm updateBasicForm) {
        AdministratorEntity administratorEntity = new AdministratorEntity();
        BeanUtils.copyProperties(updateBasicForm, administratorEntity);
        this.updateById(administratorEntity);
    }

    @Override
    public void updatePassword(AdministratorUpdatePasswordForm updatePasswordForm) {
        AdministratorEntity administratorEntity = this.getById(updatePasswordForm.getId());

        if (!administratorEntity.getPassword().equals(new Sha256Hash(updatePasswordForm.getOldPassword(), administratorEntity.getSalt()).toHex())) {
            throw new RunException(Constant.WARNING_CODE, "原密码不一致！");
        }

        String salt = RandomStringUtils.randomAlphanumeric(20);
        String password = new Sha256Hash(updatePasswordForm.getNewPassword(), salt).toHex();

        AdministratorEntity administrator = new AdministratorEntity();
        administrator.setId(updatePasswordForm.getId());
        administrator.setSalt(salt);
        administrator.setPassword(password);

        this.updateById(administrator);
    }

    @Transactional(rollbackFor = RunException.class)
    @Override
    public void create(AdministratorCreateOrUpdateForm createOrUpdateForm, Long enterpriseId, Long administratorId) {
        LambdaQueryWrapper<AdministratorEntity> wrapper = new LambdaQueryWrapper<AdministratorEntity>()
                .eq(AdministratorEntity::getUsername, createOrUpdateForm.getUsername());
        Long count = this.count(wrapper);
        if (count > 0) {
            throw new RunException(Constant.WARNING_CODE, "该账户已存在！");
        }

        AdministratorEntity administratorEntity = new AdministratorEntity();
        BeanUtils.copyProperties(createOrUpdateForm, administratorEntity);
        administratorEntity.setId(null);
        administratorEntity.setEnterpriseId(enterpriseId);
        administratorEntity.setCreator(administratorId);
        administratorEntity.setCreatedAt(new Date());
        // 密码加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        String password = new Sha256Hash(administratorEntity.getPassword(), salt).toHex();
        administratorEntity.setSalt(salt);
        administratorEntity.setPassword(password);

        this.save(administratorEntity);

        administratorRoleService.createOrUpdateBatch(administratorEntity.getId(), createOrUpdateForm.getRoleIds());
    }

    @Transactional(rollbackFor = RunException.class)
    @Override
    public void update(AdministratorCreateOrUpdateForm createOrUpdateForm, Long administratorId) {
        LambdaQueryWrapper<AdministratorEntity> wrapper = new LambdaQueryWrapper<AdministratorEntity>()
                .eq(AdministratorEntity::getUsername, createOrUpdateForm.getUsername())
                .ne(AdministratorEntity::getId, createOrUpdateForm.getId());
        Long count = this.count(wrapper);
        if (count > 0) {
            throw new RunException(Constant.WARNING_CODE, "该账户已存在！");
        }

        AdministratorEntity administratorEntity = new AdministratorEntity();
        BeanUtils.copyProperties(createOrUpdateForm, administratorEntity);
        administratorEntity.setEnterpriseId(null);
        administratorEntity.setUpdater(administratorId);
        administratorEntity.setUpdatedAt(new Date());

        if (StringUtils.isNotBlank(createOrUpdateForm.getPassword())) {
            // 密码加密
            String salt = RandomStringUtils.randomAlphanumeric(20);
            String password = new Sha256Hash(administratorEntity.getPassword(), salt).toHex();
            administratorEntity.setSalt(salt);
            administratorEntity.setPassword(password);
        }

        this.updateById(administratorEntity);

        administratorRoleService.createOrUpdateBatch(administratorEntity.getId(), createOrUpdateForm.getRoleIds());
    }

    @Transactional(rollbackFor = RunException.class)
    @Override
    public void delete(List<Long> ids) {
        LambdaQueryWrapper<AdministratorEntity> administratorWrapper = new LambdaQueryWrapper<AdministratorEntity>()
                .in(AdministratorEntity::getId, ids);
        this.remove(administratorWrapper);

        LambdaQueryWrapper<AdministratorRoleEntity> administratorRoleWrapper = new LambdaQueryWrapper<AdministratorRoleEntity>()
                .in(AdministratorRoleEntity::getRoleId, ids);
        administratorRoleService.remove(administratorRoleWrapper);
    }

    @Override
    public RPage<AdministratorRoleDTO> queryPage(EnterpriseAdministratorPageDateForm pageForm) {
        Page<AdministratorRoleDTO> page = new Query<AdministratorRoleDTO>().getPage(pageForm.getCurrent(), pageForm.getSize());
        return new RPage<>(
                baseMapper.queryPage(
                        page,
                        pageForm.getId(),
                        null,
                        null,
                        pageForm.getName(),
                        pageForm.getStart(),
                        pageForm.getEnd()
                )
        );
    }

    @Override
    public RPage<AdministratorOnlineVO> queryPage(AdministratorOnlinePageForm pageForm, List<Long> ids) {
        Page<AdministratorOnlineVO> page = new Query<AdministratorOnlineVO>().getPage(pageForm.getCurrent(), pageForm.getSize());
        IPage<AdministratorOnlineVO> list;
        if (ids.size() > 0) {
            list = baseMapper.queryPageByIds(page, ids, pageForm.getName());
        } else {
            list = new IPage<AdministratorOnlineVO>() {
                @Override
                public List<OrderItem> orders() {
                    return null;
                }

                @Override
                public List<AdministratorOnlineVO> getRecords() {
                    return new ArrayList<>();
                }

                @Override
                public IPage<AdministratorOnlineVO> setRecords(List<AdministratorOnlineVO> records) {
                    return null;
                }

                @Override
                public long getTotal() {
                    return 0;
                }

                @Override
                public IPage<AdministratorOnlineVO> setTotal(long total) {
                    return null;
                }

                @Override
                public long getSize() {
                    return 0;
                }

                @Override
                public IPage<AdministratorOnlineVO> setSize(long size) {
                    return null;
                }

                @Override
                public long getCurrent() {
                    return 0;
                }

                @Override
                public IPage<AdministratorOnlineVO> setCurrent(long current) {
                    return null;
                }
            };
        }
        return new RPage<>(list);
    }
}
