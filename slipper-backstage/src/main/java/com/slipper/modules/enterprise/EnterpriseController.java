package com.slipper.modules.enterprise;

import com.slipper.common.utils.BeanUtils;
import com.slipper.common.utils.R;
import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import com.slipper.modules.AbstractController;
import com.slipper.service.annotation.Log;
import com.slipper.service.model.KeyValueModel;
import com.slipper.service.model.KeysModel;
import com.slipper.service.modules.enterprise.entity.EnterpriseEntity;
import com.slipper.service.modules.enterprise.model.form.EnterpriseCreateOrUpdateForm;
import com.slipper.service.modules.enterprise.model.form.EnterprisePageDateForm;
import com.slipper.service.modules.enterprise.model.form.EnterprisePageForm;
import com.slipper.service.modules.enterprise.model.vo.EnterpriseVO;
import com.slipper.service.modules.enterprise.service.EnterpriseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 企业
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/backstage/enterprise")
public class EnterpriseController extends AbstractController {

    @Resource
    private EnterpriseService enterpriseService;

    /**
     * 获取当前管理员所在企业
     * TODO: 超级管理员返回当前企业下所有菜单、权限
     *
     * @api {GET} /backstage/enterprise/self/info selfInfo
     * @apiDescription 获取当前管理员所在企业
     * @apiVersion 1.0.0
     * @apiGroup Enterprise
     * @apiName selfInfo
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: {
     *         permissions:[], // 权限列表
     *         menus: [{
     *              id: '', // ID
     *              name: '', // 名称
     *              logo: '' // logo
     *         }
     *     }
     * }
     */
    @GetMapping("/self/info")
    public R selfInfo() {
        return R.success(enterpriseService.getById(getEnterpriseId()));
    }

    /**
     * 分页
     *
     * @api {GET} /backstage/enterprise/page page
     * @apiDescription 分页
     * @apiVersion 1.0.0
     * @apiGroup Enterprise
     * @apiName page
     * @apiParamExample 请求参数示例
     * {
     *     name: '', // 名称
     *     start: '', // 开始日期
     *     end: '' // 结束日期
     *     current: '', // 当前页
     *     size: '' // 页面大小
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: {
     *          current: 1, // 当前页
     *          size: 1, // 页面大小
     *          total: 1, // 总条数
     *          pages: 1, // 总页数
     *          list: [{
     *              id: '', // ID
     *         	    name: '', // 名称
     *         	    logo: '', // logo
     *              describe: '', // 备注
     *              status: '', // 状态：0-禁用 1-正常
     *              created_at: '', // 创建时间
     *              updated_at: '' // 更新时间
     *         }]
     *     }
     * }
     */
    @RequiresPermissions("enterprise:page")
    @GetMapping("/page")
    public R page(EnterprisePageDateForm pageForm) {
        return R.success(enterpriseService.queryPage(pageForm));
    }

    /**
     * 信息
     *
     * @api {GET} /backstage/role/enterprise/{id} info
     * @apiDescription 信息
     * @apiVersion 1.0.0
     * @apiGroup Enterprise
     * @apiName info
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: {
     *          id: '', // ID
     *          name: '', // 名称
     *          logo: '', // Logo
     *          describe: '', // 备注
     *          status: '', // 状态：0-禁用 1-正常
     *     }
     * }
     */
    @RequiresPermissions("enterprise:info")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        EnterpriseVO enterpriseVO = new EnterpriseVO();
        BeanUtils.copyProperties(enterpriseService.getById(id), enterpriseVO);
        return R.success(enterpriseVO);
    }

    /**
     * 创建
     *
     * @api {POST} /backstage/enterprise/create create
     * @apiDescription 创建
     * @apiVersion 1.0.0
     * @apiGroup Enterprise
     * @apiName create
     * @apiParamExample 请求参数示例
     * {
     *      name: '', // 名称
     *      logo: '' // Logo
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("新增企业")
    @RequiresPermissions("enterprise:create")
    @PostMapping("/create")
    public R create(@RequestBody @Validated(Create.class) EnterpriseCreateOrUpdateForm createOrUpdateForm) {
        enterpriseService.create(createOrUpdateForm, getAdministratorId());
        return R.success();
    }

    /**
     * 更新
     *
     * @api {POST} /backstage/enterprise/update update
     * @apiDescription 创建
     * @apiVersion 1.0.0
     * @apiGroup Enterprise
     * @apiName update
     * @apiParamExample 请求参数示例
     * {
     *      id: '', // Id
     *      name: '', // 名称
     *      logo: '' // Logo
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("更新企业")
    @RequiresPermissions("enterprise:update")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(Update.class) EnterpriseCreateOrUpdateForm createOrUpdateForm) {
        enterpriseService.update(createOrUpdateForm, getAdministratorId());
        return R.success();
    }

    /**
     * 删除
     *
     * @api {POST} /backstage/enterprise/delete delete
     * @apiDescription 删除
     * @apiVersion 1.0.0
     * @apiGroup Enterprise
     * @apiName delete
     * @apiParamExample 请求参数示例
     * {
     *     keys: [] // ID 数组
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("删除企业")
    @RequiresPermissions("enterprise:delete")
    @PostMapping("/delete")
    public R delete(@RequestBody @Validated KeysModel<Long> keysModel) {
        enterpriseService.delete(keysModel.getKeys());
        return R.success();
    }

    /**
     * 设置是否启用
     *
     * @api {POST} /backstage/enterprise/status status
     * @apiDescription 设置是否启用
     * @apiVersion 1.0.0
     * @apiGroup Enterprise
     * @apiName status
     * @apiParamExample 请求参数示例
     * {
     *      key: '', // 主键 - ID
     *      value: '', // 值 - 需要设置的值
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("设置企业是否启用")
    @RequiresPermissions("enterprise:status")
    @PostMapping("/status")
    public R setStatus(@RequestBody @Validated KeyValueModel<Long, Integer> keyValueModel) {
        EnterpriseEntity enterpriseEntity = new EnterpriseEntity();
        enterpriseEntity.setId(keyValueModel.getKey());
        enterpriseEntity.setStatus(keyValueModel.getValue());
        enterpriseEntity.setUpdater(getAdministratorId());
        enterpriseEntity.setUpdatedAt(new Date());
        enterpriseService.updateById(enterpriseEntity);
        return R.success();
    }

    /* todo:--------------------------------------------------------------------------------------  */

    /**
     * 分页
     *
     * @api {GET} /backstage/enterprise/global/page page
     * @apiDescription 分页
     * @apiVersion 1.0.0
     * @apiGroup Enterprise
     * @apiName page
     * @apiParamExample 请求参数示例
     * {
     *     name: '', // 名称
     *     current: '', // 当前页
     *     size: '' // 页面大小
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: {
     *          current: 1, // 当前页
     *          size: 1, // 页面大小
     *          total: 1, // 总条数
     *          pages: 1, // 总页数
     *          list: [{
     *              id: '', // ID
     *         	    name: '', // 名称
     *         }]
     *     }
     * }
     */
    @RequiresPermissions("global:enterprise:page")
    @GetMapping("/global/page")
    public R globalPage(EnterprisePageForm pageForm) {
        return R.success(enterpriseService.queryPage(pageForm));
    }

}
