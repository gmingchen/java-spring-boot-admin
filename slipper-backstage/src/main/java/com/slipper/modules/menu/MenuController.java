package com.slipper.modules.menu;

import com.slipper.common.utils.BeanUtils;
import com.slipper.common.utils.R;
import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import com.slipper.modules.AbstractController;
import com.slipper.service.annotation.Log;
import com.slipper.service.model.KeyModel;
import com.slipper.service.modules.menu.model.form.MenuCreateOrUpdateForm;
import com.slipper.service.modules.menu.model.form.MenuUpdateParentAndSortForm;
import com.slipper.service.modules.menu.model.vo.MenuVO;
import com.slipper.service.modules.menu.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 菜单
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@RestController
@RequestMapping("/backstage/menu")
public class MenuController extends AbstractController {

    @Resource
    private MenuService menuService;

    /**
     * 获取所有菜单权限
     *
     * @api {GET} /backstage/menu/list
     * @apiDescription 获取所有菜单权限
     * @apiVersion 1.0.0
     * @apiGroup Menu
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
     *          path: '', // 路由 path 属性值 若为空则按照url路径处理
     *          name: '', // 路由 name 属性值 若为空则按照url路径处理
     *          url: '', // 菜单路由 或 URL
     *          permission: '', // 授权(多个用逗号分隔，如：user:list,user:create)
     *          type: '', // 类型：0-目录 1-菜单 2-按钮 3-iframe 4-外链
     *          icon: '', // 图标
     *          show: '', // 是否在菜单显示：0-否 1-是
     *          tab: '', // 是否支持tab页签多开：0-否 1-是
     *          multiple: '', // 是否支持tab页签多开：0-否 1-是
     *          keepalive: '', // 是否支持缓存：0-否 1-是
     *          sort: '', // 排序
     *          parent_id: '', // 父ID
     *          created_at: '', // 创建时间
     *          updated_at: '', // updated_at
     *          children: [] // 子集
     *     }]
     * }
     */
    @RequiresPermissions("menu:list")
    @GetMapping("/list")
    public R list() {
        return R.success(menuService.queryList());
    }

    /**
     * 获取所有菜单权限
     *
     * @api {GET} /backstage/menu/select/list
     * @apiDescription 获取所有菜单权限
     * @apiVersion 1.0.0
     * @apiGroup Menu
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
     *          children: [] // 子集
     *     }]
     * }
     */
    @RequiresPermissions("menu:select")
    @GetMapping("/select/list")
    public R selectList() {
        return R.success(menuService.querySelectList());
    }

    /**
     * 信息
     *
     * @api {GET} /backstage/menu/info/{id} info
     * @apiDescription 获取菜单信息
     * @apiVersion 1.0.0
     * @apiGroup Menu
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
     *          name_cn: '', // 中文名称
     *          name_en: '', // 英文名称
     *          path: '', // 路由 path 属性值 若为空则按照url路径处理
     *          name: '', // 路由 name 属性值 若为空则按照url路径处理
     *          url: '', // 菜单路由 或 URL
     *          permission: '', // 授权(多个用逗号分隔，如：user:list,user:create)
     *          type: '', // 类型：0-目录 1-菜单 2-按钮 3-iframe 4-外链
     *          icon: '', // 图标
     *          show: '', // 是否在菜单显示：0-否 1-是
     *          tab: '', // 是否支持tab页签多开：0-否 1-是
     *          multiple: '', // 是否支持tab页签多开：0-否 1-是
     *          keepalive: '', // 是否支持缓存：0-否 1-是
     *          sort: '', // 排序
     *          parent_id: '' // 父ID
     *     }
     * }
     */
    @RequiresPermissions("menu:info")
    @GetMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        MenuVO menuVO = new MenuVO();
        BeanUtils.copyProperties(menuService.getById(id), menuVO);
        return R.success(menuVO);
    }

    /**
     * 创建
     *
     * @api {POST} /backstage/menu/create create
     * @apiDescription 创建
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName create
     * @apiParamExample 请求参数示例
     * {
     *      name_cn: '', // 中文名称
     *      name_en: '', // 英文名称
     *      path: '', // 路由 path 属性值 若为空则按照url路径处理
     *      name: '', // 路由 name 属性值 若为空则按照url路径处理
     *      url: '', // 菜单路由 或 URL
     *      permission: '', // 授权(多个用逗号分隔，如：user:list,user:create)
     *      type: '', // 类型：0-目录 1-菜单 2-按钮 3-iframe 4-外链
     *      icon: '', // 图标
     *      show: '', // 是否在菜单显示：0-否 1-是
     *      tab: '', // 是否支持tab页签多开：0-否 1-是
     *      multiple: '', // 是否支持tab页签多开：0-否 1-是
     *      keepalive: '', // 是否支持缓存：0-否 1-是
     *      sort: '', // 排序
     *      parent_id: '' // 父ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: '' // ID
     * }
     */
    @Log("新增菜单")
    @RequiresPermissions("menu:create")
    @PostMapping("/create")
    public R create(@RequestBody @Validated(Create.class) MenuCreateOrUpdateForm createOrUpdateForm) {
        return R.success(menuService.create(createOrUpdateForm, getAdministratorId()));
    }

    /**
     * 更新
     *
     * @api {POST} /backstage/menu/update update
     * @apiDescription 更新
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName update
     * @apiParamExample 请求参数示例
     * {
     *      id: '', // ID
     *      name_cn: '', // 中文名称
     *      name_en: '', // 英文名称
     *      path: '', // 路由 path 属性值 若为空则按照url路径处理
     *      name: '', // 路由 name 属性值 若为空则按照url路径处理
     *      url: '', // 菜单路由 或 URL
     *      permission: '', // 授权(多个用逗号分隔，如：user:list,user:create)
     *      type: '', // 类型：0-目录 1-菜单 2-按钮 3-iframe 4-外链
     *      icon: '', // 图标
     *      show: '', // 是否在菜单显示：0-否 1-是
     *      tab: '', // 是否支持tab页签多开：0-否 1-是
     *      multiple: '', // 是否支持tab页签多开：0-否 1-是
     *      keepalive: '', // 是否支持缓存：0-否 1-是
     *      sort: '', // 排序
     *      parent_id: '' // 父ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("编辑菜单")
    @RequiresPermissions("menu:update")
    @PostMapping("/update")
    public R update(@RequestBody @Validated(Update.class) MenuCreateOrUpdateForm createOrUpdateForm) {
        menuService.update(createOrUpdateForm, getAdministratorId());
        return R.success();
    }

    /**
     * 删除
     *
     * @api {POST} /backstage/menu/delete delete
     * @apiDescription 删除
     * @apiVersion 1.0.0
     * @apiGroup Menu
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
    @Log("删除菜单")
    @RequiresPermissions("menu:delete")
    @PostMapping("/delete")
    public R delete(@RequestBody KeyModel<Long> key) {
        menuService.delete(key.getKey());
        return R.success();
    }

    /**
     * 更新 菜单 父级ID 和 同级菜单排序 // todo:用于前端拖拽
     *
     * @api {POST} /backstage/menu/drag drag
     * @apiDescription 更新
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName drag
     * @apiParamExample 请求参数示例
     * {
     *      id: '', // ID
     *      parent_id: '', // 父ID
     *      sort_ids: [], // 排序ID数组
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @Log("拖拽菜单修改父级ID和同级菜单排序")
    @RequiresPermissions("menu:drag")
    @PostMapping("/drag")
    public R updateParentAndSort(@RequestBody @Validated MenuUpdateParentAndSortForm updateParentAndSortForm) {
        menuService.updateParentAndSort(updateParentAndSortForm, getAdministratorId());
        return R.success();
    }

}
