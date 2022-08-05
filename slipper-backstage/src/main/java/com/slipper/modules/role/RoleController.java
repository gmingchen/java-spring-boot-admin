package com.slipper.modules.role;

import com.slipper.common.utils.Constant;
import com.slipper.common.utils.R;
import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import com.slipper.modules.AbstractController;
import com.slipper.service.annotation.Log;
import com.slipper.service.model.KeyValueModel;
import com.slipper.service.model.KeysModel;
import com.slipper.service.modules.administrator.model.dto.AdministratorRoleDTO;
import com.slipper.service.modules.role.entity.RoleEntity;
import com.slipper.service.modules.role.model.form.EnterpriseRolePageForm;
import com.slipper.service.modules.role.model.form.RoleCreateOrUpdateForm;
import com.slipper.service.modules.role.model.form.RolePageForm;
import com.slipper.service.modules.role.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 企业角色
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/backstage/role")
public class RoleController extends AbstractController {

    @Resource
    private RoleService roleService;

    /**
     * 分页
     *
     * @api {GET} /backstage/role/page page
     * @apiDescription 分页
     * @apiVersion 1.0.0
     * @apiGroup Role
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
     *         	    remark: '', // 备注
     *         	    show: '', // 是否显示：0-否 1-是  用于不给用户操作的角色
     *         	    enterprise_id: '', // 企业ID
     *              created_at: '', // 创建时间
     *              updated_at: '' // 更新时间
     *         }]
     *     }
     * }
     */
    @RequiresPermissions("role:page")
    @GetMapping("/page")
    public R page(RolePageForm pageForm) {
        return R.success(roleService.queryPage(pageForm, getAdministrator()));
    }

    /**
     * 信息
     *
     * @api {GET} /backstage/role/info/{id} info
     * @apiDescription 信息
     * @apiVersion 1.0.0
     * @apiGroup Role
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
     *          remark: '', // 备注
     *          show: '', // 是否显示：0-否 1-是
     *          enterprise_menu_ids: [] // 企业菜单ID数组
     *     }
     * }
     */
    @RequiresPermissions("role:info")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        return R.success(roleService.queryInfo(id));
    }

    /**
     * 创建
     *
     * @api {POST} /backstage/role/create create
     * @apiDescription 创建
     * @apiVersion 1.0.0
     * @apiGroup Role
     * @apiName create
     * @apiParamExample 请求参数示例
     * {
     *      name: '', // 名称
     *      remark: '', // 备注
     *      enterprise_menu_ids: [] // 企业菜单ID数组
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("新增角色")
    @RequiresPermissions("role:create")
    @PostMapping("/create")
    public R create(@RequestBody @Validated(Create.class) RoleCreateOrUpdateForm createOrUpdateForm) {
        AdministratorRoleDTO administratorRoleDto = getAdministrator();
        roleService.create(createOrUpdateForm, administratorRoleDto.getEnterpriseId(), administratorRoleDto.getId());
        return R.success();
    }

    /**
     * 更新
     *
     * @api {POST} /backstage/role/update update
     * @apiDescription 更新
     * @apiVersion 1.0.0
     * @apiGroup Role
     * @apiName update
     * @apiParamExample 请求参数示例
     * {
     *      id: '', // ID
     *      name: '', // 名称
     *      remark: '', // 备注
     *      enterprise_menu_ids: [] // 企业菜单ID数组
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("编辑角色")
    @RequiresPermissions("role:update")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(Update.class) RoleCreateOrUpdateForm createOrUpdateForm) {
        roleService.update(createOrUpdateForm, getAdministratorId());
        return R.success();
    }

    /**
     * 删除
     *
     * @api {POST} /backstage/role/delete delete
     * @apiDescription 删除
     * @apiVersion 1.0.0
     * @apiGroup Role
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
    @Log("删除角色")
    @RequiresPermissions("role:delete")
    @PostMapping("/delete")
    public R delete(@RequestBody @Validated KeysModel<Long> keysModel) {
        roleService.delete(keysModel.getKeys());
        return R.success();
    }

    /**
     * 设置是否显示
     *
     * @api {POST} /backstage/role/show show
     * @apiDescription 设置是否显示
     * @apiVersion 1.0.0
     * @apiGroup Role
     * @apiName show
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
    @Log("设置角色是否显示")
    @RequiresPermissions("role:show")
    @PostMapping("/show")
    public R setShow(@RequestBody @Validated KeyValueModel<Long, Integer> keyValueModel) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(keyValueModel.getKey());
        roleEntity.setShow(keyValueModel.getValue());
        roleEntity.setUpdater(getAdministratorId());
        roleEntity.setUpdatedAt(new Date());
        roleService.updateById(roleEntity);
        return R.success();
    }

    /**
     * 获取管理员创建的角色 超级管理员获取企业下所有的角色
     *
     * @api {GET} /backstage/role/select/list list
     * @apiDescription 获取管理员创建的角色 超级管理员获取企业下所有的角色
     * @apiVersion 1.0.0
     * @apiGroup Role
     * @apiName list
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: [{
     *          id: '', // ID
     *          name: '', // 名称
     *     }]
     * }
     */
    @RequiresPermissions("role:select")
    @GetMapping("/select/list")
    public R selectList() {
        return R.success(roleService.queryListByAdministrator(getAdministrator()));
    }

    /* todo:--------------------------------------------------------------------------------------  */

    /**
     * 分页
     *
     * @api {GET} /backstage/role/global/page page
     * @apiDescription 分页
     * @apiVersion 1.0.0
     * @apiGroup Role
     * @apiName page
     * @apiParamExample 请求参数示例
     * {
     *     name: '', // 名称
     *     current: '', // 当前页
     *     size: '', // 页面大小
     *     id: '', // 企业ID
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
     *         	    remark: '', // 备注
     *         	    show: '', // 是否显示：0-否 1-是  用于不给用户操作的角色
     *         	    enterprise_id: '', // 企业ID
     *              created_at: '', // 创建时间
     *              updated_at: '' // 更新时间
     *         }]
     *     }
     * }
     */
    @RequiresPermissions("global:role:page")
    @GetMapping("/global/page")
    public R globalPage(EnterpriseRolePageForm pageForm) {
        if (pageForm.getId() == null) {
            return R.error(Constant.VERIFICATION_ERROR_CODE, "企业ID不能为空");
        }
        return R.success(roleService.queryPage(pageForm));
    }

    /**
     * 信息
     *
     * @api {GET} /backstage/role/global/info/{id} info
     * @apiDescription 信息
     * @apiVersion 1.0.0
     * @apiGroup Role
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
     *          remark: '', // 备注
     *          show: '', // 是否显示：0-否 1-是
     *          enterprise_menu_ids: [] // 企业菜单ID数组
     *     }
     * }
     */
    @RequiresPermissions("global:role:info")
    @GetMapping("/global/info/{id}")
    public R globalInfo(@PathVariable("id") Long id) {
        return R.success(roleService.queryInfo(id));
    }

    /**
     * 创建
     *
     * @api {POST} /backstage/role/global/create create
     * @apiDescription 创建
     * @apiVersion 1.0.0
     * @apiGroup Role
     * @apiName create
     * @apiParamExample 请求参数示例
     * {
     *      name: '', // 名称
     *      remark: '', // 备注
     *      enterprise_menu_ids: [], // 企业菜单ID数组
     *      enterprise_id: '', // 企业ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("总后台新增角色")
    @RequiresPermissions("global:role:create")
    @PostMapping("/global/create")
    public R globalCreate(@RequestBody @Validated(Create.class) RoleCreateOrUpdateForm createOrUpdateForm) {
        if (createOrUpdateForm.getEnterpriseId() == null) {
            return R.error(Constant.VERIFICATION_ERROR_CODE, "企业ID不能为空");
        }
        AdministratorRoleDTO administratorRoleDto = getAdministrator();
        roleService.create(createOrUpdateForm, createOrUpdateForm.getEnterpriseId(), administratorRoleDto.getId());
        return R.success();
    }

    /**
     * 更新
     *
     * @api {POST} /backstage/role/global/update update
     * @apiDescription 更新
     * @apiVersion 1.0.0
     * @apiGroup Role
     * @apiName update
     * @apiParamExample 请求参数示例
     * {
     *      id: '', // ID
     *      name: '', // 名称
     *      remark: '', // 备注
     *      enterprise_menu_ids: [] // 企业菜单ID数组
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("总后台编辑角色")
    @RequiresPermissions("global:role:update")
    @PostMapping("/global/update")
    public R globalUpdate(@RequestBody @Validated(Update.class) RoleCreateOrUpdateForm createOrUpdateForm) {
        roleService.update(createOrUpdateForm, getAdministratorId());
        return R.success();
    }

    /**
     * 删除
     *
     * @api {POST} /backstage/role/global/delete delete
     * @apiDescription 删除
     * @apiVersion 1.0.0
     * @apiGroup Role
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
    @Log("总后台删除角色")
    @RequiresPermissions("global:role:delete")
    @PostMapping("/global/delete")
    public R globalDelete(@RequestBody @Validated KeysModel<Long> keysModel) {
        roleService.delete(keysModel.getKeys());
        return R.success();
    }

    /**
     * 设置是否显示
     *
     * @api {POST} /backstage/role/global/show show
     * @apiDescription 设置是否显示
     * @apiVersion 1.0.0
     * @apiGroup Role
     * @apiName show
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
    @Log("总后台设置角色是否显示")
    @RequiresPermissions("global:role:show")
    @PostMapping("/global/show")
    public R globalSetShow(@RequestBody @Validated KeyValueModel<Long, Integer> keyValueModel) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(keyValueModel.getKey());
        roleEntity.setShow(keyValueModel.getValue());
        roleEntity.setUpdater(getAdministratorId());
        roleEntity.setUpdatedAt(new Date());
        roleService.updateById(roleEntity);
        return R.success();
    }

    /**
     * 获取企业下所有的角色
     *
     * @api {GET} /backstage/role/global/select/list/{id} list
     * @apiDescription 获取企业下所有的角色
     * @apiVersion 1.0.0
     * @apiGroup Role
     * @apiName list
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: [{
     *          id: '', // ID
     *          name: '', // 名称
     *     }]
     * }
     */
    @RequiresPermissions("global:role:select")
    @GetMapping("/global/select/list/{id}")
    public R globalSelectList(@PathVariable("id") Long id) {
        return R.success(roleService.queryListByEnterpriseId(id));
    }

}
