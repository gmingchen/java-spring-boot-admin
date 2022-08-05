package com.slipper.modules.enterpriseMenu;

import com.slipper.common.utils.R;
import com.slipper.modules.AbstractController;
import com.slipper.service.annotation.Log;
import com.slipper.service.model.KeyModel;
import com.slipper.service.modules.enterprise.model.form.EnterpriseMenuModifyForm;
import com.slipper.service.modules.enterprise.model.form.EnterpriseMenuUpdateForm;
import com.slipper.service.modules.enterprise.service.EnterpriseMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 企业菜单权限
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/backstage/enterprise/menu")
public class EnterpriseMenuController extends AbstractController {

    @Resource
    private EnterpriseMenuService enterpriseMenuService;

    /**
     * 获取当前管理员菜单、权限
     * TODO: 超级管理员返回当前企业下所有菜单、权限
     *
     * @api {GET} /backstage/enterprise/menu/self/info selfInfo
     * @apiDescription 获取当前管理员菜单、权限
     * @apiVersion 1.0.0
     * @apiGroup EnterpriseMenu
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
     *         	   id: '', // ID
     *             name_cn: '', // 中文名称
     *             name_en: '', // 英文名称
     *             path: '', // 路由 path 属性值 若为空则按照url路径处理
     *             name: '', // 路由 name 属性值 若为空则按照url路径处理
     *             url: '', // 路由 、url
     *             permission: '', // 授权(多个用逗号分隔)
     *             type: '', // 类型：0-目录 1-菜单 2-按钮 3-iframe 4-外链
     *             icon: '', // 图标
     *             show: '', // 是否显示：0-否 1-是
     *             tab: '', // 是否显示在tab页签：0-否 1-是
     *             multiple: '', // 是否支持tab页签多开：0-否 1-是
     *             keepalive: '', // 是否支持缓存：0-否 1-是
     *             sort: '', // 排序
     *             menu_id: '', // 菜单ID
     *             parent_id: '', // 父级ID
     *             enterprise_id: '', // 企业ID
     *             children: [{
     *                  id: '', // ID
     *                  name_cn: '', // 中文名称
     *                  name_en: '', // 英文名称
     *                  path: '', // 路由 path 属性值 若为空则按照url路径处理
     *                  name: '', // 路由 name 属性值 若为空则按照url路径处理
     *                  url: '', // 路由 、url
     *                  permission: '', // 授权(多个用逗号分隔)
     *                  type: '', // 类型：0-目录 1-菜单 2-按钮 3-iframe 4-外链
     *                  icon: '', // 图标
     *                  show: '', // 是否显示：0-否 1-是
     *                  tab: '', // 是否显示在tab页签：0-否 1-是
     *                  multiple: '', // 是否支持tab页签多开：0-否 1-是
     *                  keepalive: '', // 是否支持缓存：0-否 1-是
     *                  sort: '', // 排序
     *                  menu_id: '', // 菜单ID
     *                  parent_id: '', // 父级ID
     *                  enterprise_id: '', // 企业ID
     *                  children: []
     *             }]
     *         }
     *     }
     * }
     */
    @GetMapping("/self/info")
    public R selfInfo() {
        return R.success(enterpriseMenuService.queryPermissionAndMenuByAdministrator(getAdministrator()));
    }

    /**
     * 获取当前企业下所有菜单
     *
     * @api {GET} /backstage/enterprise/menu/list list
     * @apiDescription 获取当前企业下所有菜单
     * @apiVersion 1.0.0
     * @apiGroup EnterpriseMenu
     * @apiName list
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: {
     *          id: '', // ID
     *          name_cn: '', // 中文名称
     *          name_en: '', // 英文名称
     *          type: '', // 类型：0-目录 1-菜单 2-按钮 3-iframe 4-外链
     *          icon: '', // 图标
     *          sort: '', // 排序
     *          menu_id: '', // 菜单ID
     *          parent_id: '', // 父级ID
     *          enterprise_id: '', // 企业ID
     *          children: [{
     *               id: '', // ID
     *               name_cn: '', // 中文名称
     *               name_en: '', // 英文名称
     *               type: '', // 类型：0-目录 1-菜单 2-按钮 3-iframe 4-外链
     *               icon: '', // 图标
     *               sort: '', // 排序
     *               menu_id: '', // 菜单ID
     *               parent_id: '', // 父级ID
     *               enterprise_id: '', // 企业ID
     *               children: []
     *          }]
     *     }
     * }
     */
    @RequiresPermissions("enterpriseMenu:list")
    @GetMapping("/list")
    public R list() {
        return R.success(enterpriseMenuService.list(getEnterpriseId()));
    }

    /**
     * 修改企业菜单
     *
     * @api {GET} /backstage/enterprise/menu/update update
     * @apiDescription 修改企业菜单
     * @apiVersion 1.0.0
     * @apiGroup EnterpriseMenu
     * @apiName update
     * @apiParamExample 请求参数示例
     * {
     *     id: '', // ID
     *     name_cn: '', // 中文名称
     *     name_en: '', // 英文名称
     *     icon: '', // 图标
     *     sort: '', // 排序
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("编辑企业菜单")
    @RequiresPermissions("enterpriseMenu:update")
    @PostMapping("/update")
    public R update(@RequestBody @Validated EnterpriseMenuUpdateForm enterpriseMenuForm) {
        enterpriseMenuForm.setUpdater(getAdministratorId());
        enterpriseMenuService.update(enterpriseMenuForm);
        return R.success();
    }

    /**
     * 获取管理员在该企业下的菜单权限 非树形 只包括名称 id
     * 超级管理员 返回所有 否则 返回该用户角色下面的所有
     *
     * @api {GET} /backstage/enterprise/menu/select/list list
     * @apiDescription 获取当前管理员菜单
     * @apiVersion 1.0.0
     * @apiGroup EnterpriseMenu
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
     *          name_cn: '', // 中文名称
     *          name_en: '', // 英文名称
     *     }]
     * }
     */
    @RequiresPermissions("enterpriseMenu:select")
    @GetMapping("/select/list")
    public R selectList() {
        return R.success(enterpriseMenuService.queryListByAdministrator(getAdministrator()));
    }

    /* todo:--------------------------------------------------------------------------------------  */

    /**
     * 获取当前企业下所有菜单
     *
     * @api {GET} /backstage/enterprise/menu/global/list list
     * @apiDescription 获取当前企业下所有菜单
     * @apiVersion 1.0.0
     * @apiGroup EnterpriseMenu
     * @apiName list
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: {
     *          id: '', // ID
     *          name_cn: '', // 中文名称
     *          name_en: '', // 英文名称
     *          type: '', // 类型：0-目录 1-菜单 2-按钮 3-iframe 4-外链
     *          icon: '', // 图标
     *          sort: '', // 排序
     *          menu_id: '', // 菜单ID
     *          parent_id: '', // 父级ID
     *          enterprise_id: '', // 企业ID
     *          children: [{
     *               id: '', // ID
     *               name_cn: '', // 中文名称
     *               name_en: '', // 英文名称
     *               type: '', // 类型：0-目录 1-菜单 2-按钮 3-iframe 4-外链
     *               icon: '', // 图标
     *               sort: '', // 排序
     *               menu_id: '', // 菜单ID
     *               parent_id: '', // 父级ID
     *               enterprise_id: '', // 企业ID
     *               children: []
     *          }]
     *     }
     * }
     */
    @RequiresPermissions("global:enterpriseMenu:list")
    @GetMapping("/global/list/{id}")
    public R globalList(@PathVariable("id") Long id) {
        return R.success(enterpriseMenuService.list(id));
    }

    /**
     * 获取该企业有用的菜单ID数组
     *
     * @api {GET} /backstage/enterprise/menu/global/ids/{id} id
     * @apiDescription 获取该企业有用的菜单ID数组
     * @apiVersion 1.0.0
     * @apiGroup EnterpriseMenu
     * @apiName id
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: [] // 菜单ID数组
     * }
     */
    @RequiresPermissions("global:enterpriseMenu:ids")
    @GetMapping("/global/ids/{id}")
    public R globalIds(@PathVariable("id") Long id) {
        return R.success(enterpriseMenuService.queryMenuIdByEnterpriseId(id));
    }

    /**
     * 修改企业菜单权限
     *
     * @api {GET} /backstage/enterprise/menu/global/modify modify
     * @apiDescription 修改企业菜单权限
     * @apiVersion 1.0.0
     * @apiGroup EnterpriseMenu
     * @apiName modify
     * @apiParamExample 请求参数示例
     * {
     *     enterprise_id: '', // 企业ID
     *     menu_ids: [] // 菜单ID数组
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("总后台修改企业菜单权限")
    @RequiresPermissions("global:enterpriseMenu:modify")
    @PostMapping("/global/modify")
    public R globalModify(@RequestBody @Validated EnterpriseMenuModifyForm modifyForm) {
        enterpriseMenuService.modify(modifyForm, getEnterpriseId());
        return R.success();
    }

    /**
     * 修改企业菜单
     *
     * @api {GET} /backstage/enterprise/menu/global/update update
     * @apiDescription 修改企业菜单
     * @apiVersion 1.0.0
     * @apiGroup EnterpriseMenu
     * @apiName update
     * @apiParamExample 请求参数示例
     * {
     *     id: '', // ID
     *     name_cn: '', // 中文名称
     *     name_en: '', // 英文名称
     *     icon: '', // 图标
     *     sort: '', // 排序
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("总后台编辑企业菜单")
    @RequiresPermissions("global:enterpriseMenu:update")
    @PostMapping("/global/update")
    public R globalUpdate(@RequestBody @Validated EnterpriseMenuUpdateForm enterpriseMenuForm) {
        enterpriseMenuForm.setUpdater(getAdministratorId());
        enterpriseMenuService.update(enterpriseMenuForm);
        return R.success();
    }

    /**
     * 删除
     *
     * @api {POST} /backstage/enterprise/menu/global/delete delete
     * @apiDescription 删除
     * @apiVersion 1.0.0
     * @apiGroup EnterpriseMenu
     * @apiName delete
     * @apiParamExample 请求参数示例
     * {
     *     key: '' // ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("总后台删除企业菜单")
    @RequiresPermissions("global:enterpriseMenu:delete")
    @PostMapping("/global/delete")
    public R globalDelete(@RequestBody KeyModel<Long> key) {
        enterpriseMenuService.delete(key.getKey());
        return R.success();
    }

    /**
     * 获取该企业下的所有菜单权限 只包括名称 id
     *
     * @api {GET} /backstage/enterprise/menu/global/select/list/{id} list
     * @apiDescription 获取该企业下的所有菜单权限 只包括名称 id
     * @apiVersion 1.0.0
     * @apiGroup EnterpriseMenu
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
     *          name_cn: '', // 中文名称
     *          name_en: '', // 英文名称
     *     }]
     * }
     */
    @RequiresPermissions("global:enterpriseMenu:select")
    @GetMapping("/global/select/list/{id}")
    public R globalSelectList(@PathVariable("id") Long id) {
        return R.success(enterpriseMenuService.queryListByEnterpriseId(id));
    }

}
