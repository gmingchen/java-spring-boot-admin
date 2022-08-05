package com.slipper.service.modules.enterprise.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.slipper.service.modules.enterprise.entity.EnterpriseEntity;
import com.slipper.service.modules.enterprise.model.vo.EnterpriseSimplifyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 企业
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface EnterpriseDao extends BaseMapper<EnterpriseEntity> {
    /**
     * 企业分页列表 精简信息
     * @param page
     * @param name
     * @return
     */
    IPage<EnterpriseSimplifyVO> queryPage(Page<EnterpriseSimplifyVO> page, @Param("name") String name);
}
