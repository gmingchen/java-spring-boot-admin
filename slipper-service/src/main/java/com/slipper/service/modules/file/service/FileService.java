package com.slipper.service.modules.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.service.modules.file.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2022-06-21 05:29:36
 */
public interface FileService extends IService<FileEntity> {

    /**
     * 文件创建存储
     * @param file
     * @param administratorId
     * @return
     */
    FileEntity create(MultipartFile file, Long administratorId);

}

