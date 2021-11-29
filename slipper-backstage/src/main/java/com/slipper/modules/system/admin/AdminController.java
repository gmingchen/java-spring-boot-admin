package com.slipper.modules.system.admin;

import com.slipper.modules.AbstractController;
import com.slipper.service.annotation.OperationLog;
import com.slipper.service.modules.system.admin.entity.AdminEntity;
import com.slipper.service.modules.system.admin.service.AdminService;
import com.slipper.service.modules.system.admin.vo.EditAdminVo;
import com.slipper.service.modules.vo.StatusVo;
import com.slipper.common.utils.R;
import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 管理员
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/backstage/admin")
public class AdminController extends AbstractController {
    @Resource
    private AdminService adminService;

    /**
     * 当前登录管理员信息
     *
     * @api {GET} /backstage/admin/self/info selfInfo
     * @apiDescription 当前登录管理员信息
     * @apiVersion 1.0.0
     * @apiGroup Admin
     * @apiName selfInfo
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: {
     *         	   id: '', // ID
     *             username: '', // 管理员名
     *             nickname: '', // 昵称
     *             mobile: '', // 手机
     *             email: '', // 邮箱
     *             avatar: '', // 头像
     *             status: '', // 状态
     *             roles: [{
     *                 id: '', // 角色ID
     *                 name: '', // 角色名称
     *                 remark: '' // 角色备注
     *             }]
     *         }
     *     }
     * }
     */
    @GetMapping("/self/info")
    public R selfInfo(){
        return R.success(getAdmin());
    }

    /**
     * 编辑当前管理员信息
     *
     * @api {POST} /backstage/admin/self/update selfUpdate
     * @apiDescription 编辑
     * @apiVersion 1.0.0
     * @apiGroup Admin
     * @apiName selfUpdate
     * @apiParamExample 请求参数示例
     * {
     *     nickname: '', // 昵称
     *     mobile: '', // 手机
     *     email: '', // 邮箱
     *     avatar: '', // 头像
     *     old_password: '', // 原密码
     *     new_password: '' // 新密码
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: 0 // 0: 未修改密码 1: 修改了密码
     * }
     */
    @PostMapping("/self/update")
    public R selfUpdate(@RequestBody @Validated EditAdminVo editAdminVo) {
        editAdminVo.setId(getAdminId());
        return R.success(adminService.selfUpdate(editAdminVo));
    }

    /**
     * 管理员分页列表 TODO:只显示当前管理员创建管理员 超级管理员显示所有管理员
     *
     * @api {GET} /backstage/admin/page page
     * @apiDescription 系统管理员列表
     * @apiVersion 1.0.0
     * @apiGroup Admin
     * @apiName page
     * @apiParamExample 请求参数示例
     * {
     *     current: 1, // 当前页
     *     size: 10 // 页面大小
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: {
     *         current: 1, // 当前页
     *         size: 1, // 页面大小
     *         total: 1, // 总条数
     *         pages: 1, // 总页数
     *         list: [{
     *         	   id: '', // ID
     *             username: '', // 管理员名
     *             nickname: '', // 昵称
     *             mobile: '', // 手机
     *             email: '', // 邮箱
     *             avatar: '', // 头像
     *             status: '', // 状态
     *             roles: [{
     *                 id: '', // 角色ID
     *                 name: '', // 角色名称
     *                 remark: '' // 角色备注
     *             }]
     *         }]
     *     }
     * }
     */
    @GetMapping("/page")
    @RequiresPermissions("backstage:admin:page")
    public R page(@RequestParam Map<String, Object> params){
        params.put("adminId", getAdminId());
        return R.success(adminService.queryPage(params));
    }

    /**
     * 系统管理员信息
     *
     * @api {GET} /backstage/admin/info/{id} info
     * @apiDescription 系统管理员信息
     * @apiVersion 1.0.0
     * @apiGroup Admin
     * @apiName info
     * @apiParamExample 请求参数示例
     * {
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     *     data: {
     *          id: '', // ID
     *          username: '', // 管理员名
     *          nickname: '', // 昵称
     *          mobile: '', // 手机
     *          email: '', // 邮箱
     *          avatar: '', // 头像
     *          status: '', // 状态
     *          roles: [{
     *              id: '', // 角色ID
     *              name: '', // 角色名称
     *              remark: '' // 角色备注
     *          }]
     *     }
     * }
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("backstage:admin:info")
    public R info(@PathVariable("id") Long id) {
        return R.success(adminService.queryById(id));
    }

    /**
     * 新增
     *
     * @api {POST} /backstage/admin/create create
     * @apiDescription 新增
     * @apiVersion 1.0.0
     * @apiGroup Admin
     * @apiName create
     * @apiParamExample 请求参数示例
     * {
     *     username: '', // 管理员名
     *     password: '', // 密码
     *     nickname: '', // 昵称
     *     mobile: '', // 手机
     *     email: '', // 邮箱
     *     avatar: '', // 头像
     *     role_ids: [] // 角色ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @PostMapping("/create")
    @RequiresPermissions("backstage:admin:create")
    @OperationLog("新增管理员")
    public R create(@RequestBody @Validated(Create.class) AdminEntity adminEntity) {
        adminService.create(adminEntity, getAdmin());
        return R.success();
    }

    /**
     * 编辑
     *
     * @api {POST} /backstage/admin/update update
     * @apiDescription 编辑
     * @apiVersion 1.0.0
     * @apiGroup Admin
     * @apiName update
     * @apiParamExample 请求参数示例
     * {
     *     id: '', // ID
     *     username: '', // 管理员名
     *     password: '', // 密码
     *     nickname: '', // 昵称
     *     mobile: '', // 手机
     *     email: '', // 邮箱
     *     avatar: '' // 头像
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @PostMapping("/update")
    @RequiresPermissions("backstage:admin:update")
    @OperationLog("编辑管理员")
    public R update(@RequestBody @Validated(Update.class) AdminEntity adminEntity) {
        adminService.update(adminEntity, getAdmin());
        return R.success();
    }

    /**
     * 管理员批量删除
     *
     * @api {POST} /backstage/admin/delete delete
     * @apiDescription 批量删除
     * @apiVersion 1.0.0
     * @apiGroup Admin
     * @apiName delete
     * @apiParamExample 请求参数示例
     * {
     *      ids: [], // ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @PostMapping("/delete")
    @RequiresPermissions("backstage:admin:delete")
    @OperationLog("删除管理员")
    public R delete(@RequestBody Long[] ids) {
        adminService.removeByIds(Arrays.asList(ids));
        return R.success();
    }

    /**
     * 是否禁用
     *
     * @api {POST} /backstage/admin/status status
     * @apiDescription 是否禁用
     * @apiVersion 1.0.0
     * @apiGroup Admin
     * @apiName status
     * @apiParamExample 请求参数示例
     * {
     *     key: '', // ID
     *     value: '' // 0：是 1：否
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @RequestMapping("/status")
    @RequiresPermissions("backstage:admin:status")
    @OperationLog("禁用/启用管理员")
    public R status(@RequestBody @Validated StatusVo<Long, Integer> statusVo){
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setId(statusVo.getKey());
        adminEntity.setStatus(statusVo.getValue());
        adminEntity.setUpdater(getAdminId());
        adminEntity.setUpdatedAt(new Date());

        adminService.updateById(adminEntity);

        return R.success();
    }
}
