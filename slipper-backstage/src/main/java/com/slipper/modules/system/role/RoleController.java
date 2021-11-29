package com.slipper.modules.system.role;

import com.slipper.service.annotation.OperationLog;
import com.slipper.service.modules.system.role.service.RoleService;
import com.slipper.common.utils.R;
import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import com.slipper.modules.AbstractController;
import com.slipper.service.modules.system.role.entity.RoleEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * 角色
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
     * 角色分页列表 TODO:只显示当前用户创建角色 超级管理员显示所有角色
     *
     * @api {GET} /backstage/role/page page
     * @apiDescription 角色列表
     * @apiVersion 1.0.0
     * @apiGroup Role
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
     *             name: '', // 角色名
     *             remark: '', // 备注
     *             created_at: '' // 创建时间
     *         }]
     *     }
     * }
     */
    @GetMapping("/page")
    @RequiresPermissions("backstage:role:page")
    public R page(@RequestParam Map<String, Object> params){
        params.put("adminId", getAdminId());
        return R.success(roleService.queryPage(params));
    }

    /**
     * 角色信息
     *
     * @api {GET} /backstage/role/info/{id} info
     * @apiDescription 角色信息
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
     *          name: '', // 角色名
     *          remark: '', // 备注
     *          created_at: '' // 创建时间
     *     }
     * }
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("backstage:role:info")
    public R info(@PathVariable("id") Long id) {
        return R.success(roleService.queryById(id, getAdminId()));
    }

    /**
     * 角色新增
     *
     * @api {POST} /backstage/role/create create
     * @apiDescription 新增
     * @apiVersion 1.0.0
     * @apiGroup Role
     * @apiName create
     * @apiParamExample 请求参数示例
     * {
     *      name: '', // 角色名称
     *      remark: '', // 备注
     *      menu_ids: [] // 菜单权限ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @PostMapping("/create")
    @RequiresPermissions("backstage:role:create")
    @OperationLog("新增角色")
    public R create(@RequestBody @Validated(Create.class) RoleEntity RoleEntity) {
        roleService.create(RoleEntity, getAdmin());
        return R.success();
    }

    /**
     * 角色编辑
     *
     * @api {POST} /backstage/role/update update
     * @apiDescription 编辑
     * @apiVersion 1.0.0
     * @apiGroup Role
     * @apiName update
     * @apiParamExample 请求参数示例
     * {
     *      id: '', // ID
     *      name: '', // 角色名称
     *      remark: '', // 备注
     *      menu_ids: [] // 菜单权限ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @PostMapping("/update")
    @RequiresPermissions("backstage:role:update")
    @OperationLog("编辑角色")
    public R update(@RequestBody @Validated(Update.class) RoleEntity RoleEntity) {
        roleService.update(RoleEntity, getAdmin());
        return R.success();
    }

    /**
     * 角色批量删除
     *
     * @api {POST} /backstage/role/delete delete
     * @apiDescription 批量删除
     * @apiVersion 1.0.0
     * @apiGroup Role
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
    @RequiresPermissions("backstage:role:delete")
    @OperationLog("删除角色")
    public R delete(@RequestBody Long[] ids) {
        roleService.removeByIds(Arrays.asList(ids));
        return R.success();
    }

    /**
     * 角色下拉 TODO: 超级管理查询未删除的 当前用户查询自己创建的
     *
     * @api {POST} /backstage/role/select select
     * @apiDescription 角色下拉
     * @apiVersion 1.0.0
     * @apiGroup Role
     * @apiName select
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: [{
     *          id: '', // ID
     *          name: '', // 角色名称
     *          remark: '', // 备注
     *     }]
     * }
     */
    @GetMapping("/select")
    @RequiresPermissions("backstage:role:select")
    public R select() {
        return R.success(roleService.querySelect(getAdminId()));
    }
}
