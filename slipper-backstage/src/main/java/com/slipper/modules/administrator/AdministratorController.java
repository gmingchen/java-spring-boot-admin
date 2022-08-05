package com.slipper.modules.administrator;

import com.slipper.common.utils.Constant;
import com.slipper.common.utils.R;
import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import com.slipper.modules.AbstractController;
import com.slipper.service.annotation.Log;
import com.slipper.service.model.KeyValueModel;
import com.slipper.service.model.KeysModel;
import com.slipper.service.modules.administrator.entity.AdministratorEntity;
import com.slipper.service.modules.administrator.model.dto.AdministratorRoleDTO;
import com.slipper.service.modules.administrator.model.form.*;
import com.slipper.service.modules.administrator.service.AdministratorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 管理员
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/backstage/administrator")
public class AdministratorController extends AbstractController {

    @Resource
    private AdministratorService administratorService;

    /**
     * 获取当前管理员信息
     * TODO: 超级管理员返回当前企业下所有菜单、权限
     *
     * @api {GET} /backstage/administrator/self/info selfInfo
     * @apiDescription 获取当前管理员信息
     * @apiVersion 1.0.0
     * @apiGroup Administrator
     * @apiName selfInfo
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: {
     *          id: '', // ID
     *          username: '', // 用户名
     *          nickname: '', // 昵称
     *          avatar: '', // 头像
     *          mobile: '', // 手机
     *          email: '', // 邮箱
     *          sex: '', // 性别：0-女 1-男 2-未知
     *          status: '', // 状态：0-禁用 1-正常
     *          roles: [], // 角色列表
     *          supervisor: '', // 是否是超管：0-否 1-是
     *          enterprise_id: '' // 企业ID
     *     }
     * }
     */
    @GetMapping("/self/info")
    public R selfInfo() {
        return R.success(getAdministrator());
    }

    /**
     * 更新基础信息
     *
     * @api {POST} /backstage/administrator/update/basic basic
     * @apiDescription 更新基础信息
     * @apiVersion 1.0.0
     * @apiGroup Administrator
     * @apiName basic
     * @apiParamExample 请求参数示例
     * {
     *      username: '', // 用户名
     *      password: '', // 密码
     *      nickname: '', // 昵称
     *      avatar: '', // 头像
     *      mobile: '', // 手机
     *      email: '', // 邮箱
     *      sex: '', // 性别：0-女 1-男 2-未知
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("修改基础信息")
    @RequiresPermissions("administrator:basic")
    @PostMapping("/update/basic")
    public R updateBasic(@RequestBody @Validated AdministratorUpdateBasicForm updateBasicForm) {
        updateBasicForm.setId(getAdministratorId());
        administratorService.updateBasic(updateBasicForm);
        return R.success();
    }

    /**
     * 更新密码
     *
     * @api {POST} /backstage/administrator/update/password password
     * @apiDescription 更新密码
     * @apiVersion 1.0.0
     * @apiGroup Administrator
     * @apiName password
     * @apiParamExample 请求参数示例
     * {
     *      old_password: '', // 原密码
     *      new_password: '', // 新密码
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("修改密码")
    @RequiresPermissions("administrator:password")
    @PostMapping("/update/password")
    public R updatePassword(@RequestBody @Validated AdministratorUpdatePasswordForm updatePasswordForm) {
        updatePasswordForm.setId(getAdministratorId());
        administratorService.updatePassword(updatePasswordForm);
        return R.success();
    }

    /**
     * 分页
     *
     * @api {GET} /backstage/administrator/page page
     * @apiDescription 分页
     * @apiVersion 1.0.0
     * @apiGroup Administrator
     * @apiName page
     * @apiParamExample 请求参数示例
     * {
     *     name: '', // 昵称 / 账户
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
     *              username: '', // 用户名
     *              nickname: '', // 昵称
     *              avatar: '', // 头像
     *              mobile: '', // 手机
     *              email: '', // 邮箱
     *              sex: '', // 性别：0-女 1-男 2-未知
     *              status: '', // 状态：0-禁用 1-正常
     *              roles: [], // 角色列表
     *              supervisor: '', // 是否是超管：0-否 1-是
     *              enterprise_id: '' // 企业ID
     *              created_at: '' // 创建时间
     *              updated_at: '' // 更新时间
     *         }]
     *     }
     * }
     */
    @RequiresPermissions("administrator:page")
    @GetMapping("/page")
    public R page(AdministratorPageDateForm pageForm){
        return R.success(administratorService.queryPage(pageForm, getAdministrator()));
    }

    /**
     * 信息
     *
     * @api {GET} /backstage/administrator/info/{id} info
     * @apiDescription 信息
     * @apiVersion 1.0.0
     * @apiGroup Administrator
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
     *          username: '', // 用户名
     *          nickname: '', // 昵称
     *          avatar: '', // 头像
     *          mobile: '', // 手机
     *          email: '', // 邮箱
     *          sex: '', // 性别：0-女 1-男 2-未知
     *          status: '', // 状态：0-禁用 1-正常
     *          roles: [], // 角色列表
     *          supervisor: '' // 是否是超管：0-否 1-是
     *     }
     * }
     */
    @RequiresPermissions("administrator:info")
    @GetMapping("/info/{id}")
    public R info (@PathVariable("id") Long id) {
        return R.success(administratorService.queryInfo(id));
    }

    /**
     * 创建
     *
     * @api {POST} /backstage/administrator/create create
     * @apiDescription 创建
     * @apiVersion 1.0.0
     * @apiGroup Administrator
     * @apiName create
     * @apiParamExample 请求参数示例
     * {
     *      username: '', // 用户名
     *      password: '', // 密码
     *      nickname: '', // 昵称
     *      avatar: '', // 头像
     *      mobile: '', // 手机
     *      email: '', // 邮箱
     *      sex: '', // 性别：0-女 1-男 2-未知
     *      role_ids: [] // 角色ID列表
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("新增管理员")
    @RequiresPermissions("administrator:create")
    @PostMapping("/create")
    public R create(@RequestBody @Validated(Create.class) AdministratorCreateOrUpdateForm createOrUpdateForm) {
        if (createOrUpdateForm.getRoleIds().size() == 0) {
            return R.error(Constant.VERIFICATION_ERROR_CODE, "至少选择一个角色");
        }
        AdministratorRoleDTO administratorRoleDto = getAdministrator();
        createOrUpdateForm.setSupervisor(0);
        administratorService.create(createOrUpdateForm, administratorRoleDto.getEnterpriseId(), administratorRoleDto.getId());
        return R.success();
    }

    /**
     * 更新
     *
     * @api {POST} /backstage/administrator/update update
     * @apiDescription 创建
     * @apiVersion 1.0.0
     * @apiGroup Administrator
     * @apiName update
     * @apiParamExample 请求参数示例
     * {
     *      id: '', // ID
     *      username: '', // 用户名
     *      password: '', // 密码
     *      nickname: '', // 昵称
     *      avatar: '', // 头像
     *      mobile: '', // 手机
     *      email: '', // 邮箱
     *      sex: '', // 性别：0-女 1-男 2-未知
     *      role_ids: [] // 角色ID列表
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("编辑管理员")
    @RequiresPermissions("administrator:update")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(Update.class) AdministratorCreateOrUpdateForm createOrUpdateForm) {
        if (getAdministrator().getSupervisor() == 1) {
            return R.error(Constant.WARNING_CODE, "没有修改超管信息的权限！");
        }
        if (createOrUpdateForm.getRoleIds().size() == 0) {
            return R.error(Constant.VERIFICATION_ERROR_CODE, "至少选择一个角色！");
        }
        AdministratorRoleDTO administratorRoleDto = getAdministrator();
        createOrUpdateForm.setSupervisor(0);
        administratorService.update(createOrUpdateForm, administratorRoleDto.getId());
        return R.success();
    }

    /**
     * 删除
     *
     * @api {POST} /backstage/administrator/delete delete
     * @apiDescription 删除
     * @apiVersion 1.0.0
     * @apiGroup Administrator
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
    @Log("删除管理员")
    @RequiresPermissions("administrator:delete")
    @PostMapping("/delete")
    public R delete(@RequestBody @Validated KeysModel<Long> keysModel) {
        administratorService.delete(keysModel.getKeys());
        return R.success();
    }

    /**
     * 设置是否启用
     *
     * @api {POST} /backstage/administrator/status status
     * @apiDescription 设置是否启用
     * @apiVersion 1.0.0
     * @apiGroup Administrator
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
    @Log("设置管理员是否启用")
    @RequiresPermissions("administrator:status")
    @PostMapping("/status")
    public R setStatus(@RequestBody @Validated KeyValueModel<Long, Integer> keyValueModel) {
        if (keyValueModel.getKey().equals(getAdministratorId())) {
            return R.error(Constant.WARNING_CODE, "不能操作自己的帐号！");
        }
        AdministratorEntity administratorEntity = new AdministratorEntity();
        administratorEntity.setId(keyValueModel.getKey());
        administratorEntity.setStatus(keyValueModel.getValue());
        administratorEntity.setUpdater(getAdministratorId());
        administratorEntity.setUpdatedAt(new Date());
        administratorService.updateById(administratorEntity);
        return R.success();
    }

    /* todo:--------------------------------------------------------------------------------------  */

    /**
     * 分页
     *
     * @api {GET} /backstage/administrator/global/page page
     * @apiDescription 分页
     * @apiVersion 1.0.0
     * @apiGroup Administrator
     * @apiName page
     * @apiParamExample 请求参数示例
     * {
     *     name: '', // 昵称 / 账户
     *     start: '', // 开始日期
     *     end: '' // 结束日期
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
     *              username: '', // 用户名
     *              nickname: '', // 昵称
     *              avatar: '', // 头像
     *              mobile: '', // 手机
     *              email: '', // 邮箱
     *              sex: '', // 性别：0-女 1-男 2-未知
     *              status: '', // 状态：0-禁用 1-正常
     *              roles: [], // 角色列表
     *              supervisor: '', // 是否是超管：0-否 1-是
     *              enterprise_id: '' // 企业ID
     *              created_at: '' // 创建时间
     *              updated_at: '' // 更新时间
     *         }]
     *     }
     * }
     */
    @RequiresPermissions("global:administrator:page")
    @GetMapping("/global/page")
    public R globalPage(EnterpriseAdministratorPageDateForm pageForm){
        if (pageForm.getId() == null) {
            return R.error(Constant.VERIFICATION_ERROR_CODE, "企业ID不能为空");
        }
        return R.success(administratorService.queryPage(pageForm));
    }

    /**
     * 信息
     *
     * @api {GET} /backstage/administrator/global/info/{id} info
     * @apiDescription 信息
     * @apiVersion 1.0.0
     * @apiGroup Administrator
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
     *          username: '', // 用户名
     *          nickname: '', // 昵称
     *          avatar: '', // 头像
     *          mobile: '', // 手机
     *          email: '', // 邮箱
     *          sex: '', // 性别：0-女 1-男 2-未知
     *          status: '', // 状态：0-禁用 1-正常
     *          roles: [], // 角色列表
     *          supervisor: '' // 是否是超管：0-否 1-是
     *     }
     * }
     */
    @RequiresPermissions("global:administrator:info")
    @GetMapping("/global/info/{id}")
    public R globalInfo (@PathVariable("id") Long id) {
        return R.success(administratorService.queryInfo(id));
    }

    /**
     * 创建
     *
     * @api {POST} /backstage/administrator/global/create create
     * @apiDescription 创建
     * @apiVersion 1.0.0
     * @apiGroup Administrator
     * @apiName create
     * @apiParamExample 请求参数示例
     * {
     *      username: '', // 用户名
     *      password: '', // 密码
     *      nickname: '', // 昵称
     *      avatar: '', // 头像
     *      mobile: '', // 手机
     *      email: '', // 邮箱
     *      sex: '', // 性别：0-女 1-男 2-未知
     *      role_ids: [], // 角色ID列表
     *      enterprise_id: '' // 企业ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("总后台新增管理员")
    @RequiresPermissions("global:administrator:create")
    @PostMapping("/global/create")
    public R globalCreate(@RequestBody @Validated(Create.class) AdministratorCreateOrUpdateForm createOrUpdateForm) {
        if (createOrUpdateForm.getEnterpriseId() == null) {
            return R.error(Constant.VERIFICATION_ERROR_CODE, "企业ID不能为空");
        }
        AdministratorRoleDTO administratorRoleDto = getAdministrator();
        administratorService.create(
                createOrUpdateForm,
                createOrUpdateForm.getEnterpriseId(),
                administratorRoleDto.getId()
        );
        return R.success();
    }

    /**
     * 更新
     *
     * @api {POST} /backstage/administrator/global/update update
     * @apiDescription 创建
     * @apiVersion 1.0.0
     * @apiGroup Administrator
     * @apiName update
     * @apiParamExample 请求参数示例
     * {
     *      id: '', // ID
     *      username: '', // 用户名
     *      password: '', // 密码
     *      nickname: '', // 昵称
     *      avatar: '', // 头像
     *      mobile: '', // 手机
     *      email: '', // 邮箱
     *      sex: '', // 性别：0-女 1-男 2-未知
     *      role_ids: [] // 角色ID列表
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("总后台编辑管理员")
    @RequiresPermissions("global:administrator:update")
    @PostMapping("/global/update")
    public R globalUpdate(@RequestBody @Validated(Update.class) AdministratorCreateOrUpdateForm createOrUpdateForm) {
        AdministratorRoleDTO administratorRoleDto = getAdministrator();
        administratorService.update(createOrUpdateForm, administratorRoleDto.getId());
        return R.success();
    }

    /**
     * 删除
     *
     * @api {POST} /backstage/administrator/global/delete delete
     * @apiDescription 删除
     * @apiVersion 1.0.0
     * @apiGroup Administrator
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
    @Log("总后台删除管理员")
    @RequiresPermissions("global:administrator:delete")
    @PostMapping("/global/delete")
    public R globalDelete(@RequestBody @Validated KeysModel<Long> keysModel) {
        administratorService.delete(keysModel.getKeys());
        return R.success();
    }

    /**
     * 设置是否启用
     *
     * @api {POST} /backstage/administrator/global/status status
     * @apiDescription 设置是否启用
     * @apiVersion 1.0.0
     * @apiGroup Administrator
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
    @Log("总后台设置管理员是否启用")
    @RequiresPermissions("global:administrator:status")
    @PostMapping("/global/status")
    public R globalSetStatus(@RequestBody @Validated KeyValueModel<Long, Integer> keyValueModel) {
        if (keyValueModel.getKey().equals(getAdministratorId())) {
            return R.error(Constant.WARNING_CODE, "不能操作自己的帐号！");
        }
        AdministratorEntity administratorEntity = new AdministratorEntity();
        administratorEntity.setId(keyValueModel.getKey());
        administratorEntity.setStatus(keyValueModel.getValue());
        administratorEntity.setUpdater(getAdministratorId());
        administratorEntity.setUpdatedAt(new Date());
        administratorService.updateById(administratorEntity);
        return R.success();
    }

}
