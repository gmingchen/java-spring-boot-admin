package com.slipper.modules.file;

import com.slipper.common.utils.BeanUtils;
import com.slipper.common.utils.R;
import com.slipper.modules.AbstractController;
import com.slipper.service.modules.file.model.vo.FileSimplifyVO;
import com.slipper.service.modules.file.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 文件
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 2022-06-21 05:29:36
 */
@RestController
@RequestMapping("/backstage/file")
public class FileController extends AbstractController {
    @Resource
    private FileService fileService;

    /**
     * 文件上传
     *
     * @api {GET} /backstage/file/upload upload
     * @apiDescription 上传图片
     * @apiVersion 1.0.0
     * @apiGroup File
     * @apiName upload
     * @apiParamExample 请求参数示例
     * {
     *     file: (binary), // 文件流
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     *     data: {
     *         id: '', // ID
     *         original: '', // 原始名称
     *         actual: '', // 实际名称
     *         url: '', // 虚拟路径
     *     }
     * }
     */
    @PostMapping("/upload")
    public R upload(MultipartFile file) {
        FileSimplifyVO fileSimplifyVO = new FileSimplifyVO();
        BeanUtils.copyProperties(fileService.create(file, getAdministratorId()), fileSimplifyVO);
        return R.success(fileSimplifyVO);
    }

}
