package com.slipper.service.modules.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.exception.RunException;
import com.slipper.common.utils.Constant;
import com.slipper.service.modules.file.dao.FileDao;
import com.slipper.service.modules.file.entity.FileEntity;
import com.slipper.service.modules.file.service.FileService;
import com.slipper.service.modules.file.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

/**
 * 文件
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2022-06-21 05:29:36
 */
@Service("fileService")
public class FileServiceImpl extends ServiceImpl<FileDao, FileEntity> implements FileService {
    /**
     * 文件存储路径
     */
    private static final String PATH = "/home/static/file";
    /**
     * 访问域名
     */
    private static final String DOMAIN = "http://resource.gumingchen.icu";
    /**
     * 前缀
     */
    private static final String PREFIX = "file";

    @Override
    public FileEntity create(MultipartFile file, Long administratorId) {

        FileEntity fileEntity = new FileEntity();
        fileEntity.setCreator(administratorId);

        setFileEntity(fileEntity, file, PATH, DOMAIN, PREFIX, 1);
        Boolean result = FileUtils.save(file, fileEntity.getPath());

        if (!result) {
            throw new RunException(Constant.WARNING_CODE, "上传失败");
        }

        this.save(fileEntity);
        return fileEntity;
    }

    /**
     * 获取文件对象
     * @param fileEntity 文件对象
     * @param file 文件
     * @param path 路径
     * @param url 虚拟路径
     * @param prefix 前缀
     * @param type 类型
     * @return
     */
    private void setFileEntity(FileEntity fileEntity, MultipartFile file, String path, String url, String prefix, Integer type) {
        // 设置文件名
        fileEntity.setOriginal(file.getOriginalFilename());
        // 获取扩展名
        fileEntity.setExtension(fileEntity
                .getOriginal()
                .substring(fileEntity
                        .getOriginal()
                        .lastIndexOf(".")));
        // 设置文件实际名称
        fileEntity.setActual(UUID.randomUUID().toString()
                + fileEntity.getExtension());
        // 文件大小
        fileEntity.setSize(file.getSize());
        // 设置文件物理路径
        if (StringUtils.isNotBlank(path)) {
            fileEntity.setPath(path + "/" + fileEntity.getActual());
        }
        // 设置前缀
        fileEntity.setPrefix(prefix);
        // 设置存储类型
        fileEntity.setType(type);
        // 设置文件虚拟路径
        fileEntity.setUrl(url + (StringUtils.isNotBlank(prefix) ? ("/" + prefix) : "") + "/" + fileEntity.getActual());
        // 设置创建时间
        fileEntity.setCreatedAt(new Date());
    }

}
