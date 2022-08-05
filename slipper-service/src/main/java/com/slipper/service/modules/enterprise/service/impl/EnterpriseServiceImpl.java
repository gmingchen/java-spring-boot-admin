package com.slipper.service.modules.enterprise.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.utils.BeanUtils;
import com.slipper.common.utils.Query;
import com.slipper.common.utils.RPage;
import com.slipper.service.modules.enterprise.dao.EnterpriseDao;
import com.slipper.service.modules.enterprise.entity.EnterpriseEntity;
import com.slipper.service.modules.enterprise.model.form.EnterpriseCreateOrUpdateForm;
import com.slipper.service.modules.enterprise.model.form.EnterprisePageDateForm;
import com.slipper.service.modules.enterprise.model.form.EnterprisePageForm;
import com.slipper.service.modules.enterprise.model.vo.EnterpriseSimplifyVO;
import com.slipper.service.modules.enterprise.model.vo.EnterpriseVO;
import com.slipper.service.modules.enterprise.service.EnterpriseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 企业
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("enterpriseService")
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseDao, EnterpriseEntity> implements EnterpriseService {

    @Override
    public RPage<EnterpriseVO> queryPage(EnterprisePageDateForm pageForm) {
        Page<EnterpriseEntity> page = new Query<EnterpriseEntity>().getPage(pageForm.getCurrent(), pageForm.getSize());
        LambdaQueryWrapper<EnterpriseEntity> wrapper = new LambdaQueryWrapper<EnterpriseEntity>()
                .like(StringUtils.isNotBlank(pageForm.getName()), EnterpriseEntity::getName, pageForm.getName())
                .ge(StringUtils.isNotBlank(pageForm.getStart()), EnterpriseEntity::getCreatedAt, pageForm.getStart())
                .le(StringUtils.isNotBlank(pageForm.getEnd()), EnterpriseEntity::getCreatedAt, pageForm.getEnd())
                .orderByDesc(EnterpriseEntity::getCreatedAt);
        IPage<EnterpriseEntity> iPage = this.page(page, wrapper);
        List<EnterpriseVO> result = BeanUtils.copyProperties(iPage.getRecords(), EnterpriseVO.class);
        return new RPage<>(iPage, result);
    }

    @Override
    public void create(EnterpriseCreateOrUpdateForm createOrUpdateForm, Long administratorId) {
        EnterpriseEntity enterpriseEntity = new EnterpriseEntity();
        BeanUtils.copyProperties(createOrUpdateForm, enterpriseEntity);
        enterpriseEntity.setCreator(administratorId);
        enterpriseEntity.setCreatedAt(new Date());
        this.save(enterpriseEntity);
    }

    @Override
    public void update(EnterpriseCreateOrUpdateForm createOrUpdateForm, Long administratorId) {
        EnterpriseEntity enterpriseEntity = new EnterpriseEntity();
        BeanUtils.copyProperties(createOrUpdateForm, enterpriseEntity);
        enterpriseEntity.setUpdater(administratorId);
        enterpriseEntity.setUpdatedAt(new Date());
        this.updateById(enterpriseEntity);
    }

    @Override
    public void delete(List<Long> ids) {
        LambdaQueryWrapper<EnterpriseEntity> enterpriseWrapper = new LambdaQueryWrapper<EnterpriseEntity>()
                .in(EnterpriseEntity::getId, ids);
        this.remove(enterpriseWrapper);
    }

    @Override
    public RPage<EnterpriseSimplifyVO> queryPage(EnterprisePageForm PageForm) {
        Page<EnterpriseSimplifyVO> page = new Query<EnterpriseSimplifyVO>().getPage(PageForm.getCurrent(), PageForm.getSize());

        return new RPage<>(
                baseMapper.queryPage(page, PageForm.getName())
        );
    }
}
