package com.slipper.service.modules.file.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slipper.service.modules.file.entity.FileEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件
 * 
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2022-06-21 05:29:36
 */
@Mapper
public interface FileDao extends BaseMapper<FileEntity> {
	
}
