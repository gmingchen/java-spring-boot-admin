package com.slipper.modules.develop.menu;

import com.slipper.modules.AbstractController;
import com.slipper.service.annotation.OperationLog;
import com.slipper.service.modules.vo.StatusVo;
import com.slipper.common.exception.RunException;
import com.slipper.common.utils.Constant;
import com.slipper.common.utils.R;
import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import com.slipper.service.modules.develop.menu.dto.MenuDto;
import com.slipper.service.modules.develop.menu.entity.MenuEntity;
import com.slipper.service.modules.develop.menu.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限菜单
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
     * 当前用户 菜单、权限 TODO: 超级管理员返回所有
     *
     * @api {GET} /backstage/menu/self/info selfInfo
     * @apiDescription 当前用户 菜单、权限
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName selfInfo
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: {
     *          permissions:["backstage:menu:page", "backstage:menu:add"] // 权限列表
     *          menus: [{
     *         	   id: '', // ID
     *             parent_id: '', // 父级ID
     *             name_cn: '', // 中文名称
     *             name_en: '', // 英文名称
     *             url: '', // 路由 、url
     *             type: '', // 类型   0：目录   1：菜单   2：按钮
     *             icon: '', // 图标
     *             sort: '', // 排序
     *             is_display: '', // 是否显示
     *             is_tab: '', // 是否显示在标签栏
     *             children: [{
     *                  id: '', // ID
     *                  parent_id: '', // 父级ID
     *                  name_cn: '', // 中文名称
     *                  name_en: '', // 英文名称
     *                  url: '', // 路由 、url
     *                  type: '', // 类型   0：目录   1：菜单   2：按钮
     *                  icon: '', // 图标
     *                  sort: '', // 排序
     *                  is_display: '', // 是否显示
     *                  is_tab: '', // 是否显示在标签栏
     *                  children：[]
     *             }]
     *         }
     *     }
     * }
     */
    @GetMapping("/self/info")
    public R selfInfo() {
        Map<String, Object> result = new HashMap<>();
        result.put("menus", menuService.queryMenu(getAdminId()));
        result.put("permissions", menuService.queryPermission(getAdminId()));

       return R.success(result);
    }

    /**
     * 菜单下拉选择 TODO: 放回当前用户拥有的菜单 权限 超级管理员所有未删除的
     *
     * @api {GET} /backstage/menu/self/select selfSelect
     * @apiDescription 菜单下拉选择
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName selfSelect
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: {
     *     	   id: '', // ID
     *         parent_id: '', // 父级ID
     *         name_cn: '', // 中文名称
     *         name_en: '' // 英文名称
     *     }
     * }
     */
    @GetMapping("/self/select")
    @RequiresPermissions("backstage:menu:self:select")
    public R selfSelect() {
        return R.success(menuService.queryAllMenuByRole(getAdminId()));
    }

    /**
     * 菜单列表
     *
     * @api {GET} /backstage/menu/list list
     * @apiDescription 菜单列表
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName listByParent
     * @apiParamExample 请求参数示例
     * {
     *      parent_id: 0 // 父级ID 根目录为 0
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: {
     *     	   id: '', // ID
     *         parent_id: '', // 父级ID
     *         name_cn: '', // 中文名称
     *         name_en: '', // 英文名称
     *         url: '', // 路由 、url
     *         permission: '', // 权限
     *         type: '', // 类型   0：目录   1：菜单   2：按钮
     *         icon: '', // 图标
     *         sort: '', // 排序
     *         is_display: '', // 是否显示
     *         is_tab: '', // 是否显示在标签栏
     *         children: []
     *     }
     * }
     */
    @GetMapping("/list")
    @RequiresPermissions("backstage:menu:list")
    public R listByParent(@RequestParam("parent_id") Long parentId) {
        return R.success(menuService.queryAllMenuByParentId(parentId));
    }

    /**
     * 菜单信息
     *
     * @api {GET} /backstage/menu/info/{id} info
     * @apiDescription 菜单信息
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
     *     	   id: '', // ID
     *         parent_id: '', // 父级ID
     *         name_cn: '', // 中文名称
     *         name_en: '', // 英文名称
     *         url: '', // 路由 、url
     *         permission: '', // 权限
     *         type: '', // 类型   0：目录   1：菜单   2：按钮
     *         icon: '', // 图标
     *         sort: '', // 排序
     *         is_display: '', // 是否显示
     *         is_tab: '', // 是否显示在标签栏
     *         children: []
     *     }
     * }
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("backstage:menu:info")
    public R info(@PathVariable("id") Long id){
        return R.success(menuService.queryById(id));
    }

    /**
     * 菜单新增
     *
     * @api {POST} /backstage/menu/create create
     * @apiDescription 菜单新增
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName create
     * @apiParamExample 请求参数示例
     * {
     *         parent_id: '', // 父级ID
     *         name_cn: '', // 中文名称
     *         name_en: '', // 英文名称
     *         url: '', // 路由 、url
     *         permission: '', // 权限
     *         type: '', // 类型   0：目录   1：菜单   2：按钮
     *         icon: '', // 图标
     *         sort: '' // 排序
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @PostMapping("/create")
    @RequiresPermissions("backstage:menu:create")
    @OperationLog("新增菜单")
    public R create(@RequestBody @Validated(Create.class) MenuEntity MenuEntity) {
        validated(MenuEntity);
        MenuEntity.setCreator(getAdminId());
        menuService.create(MenuEntity);
        return R.success();
    }

    /**
     * 菜单编辑
     *
     * @api {POST} /backstage/menu/update update
     * @apiDescription 菜单新增
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName update
     * @apiParamExample 请求参数示例
     * {
     *     	   id: '', // ID
     *         parent_id: '', // 父级ID
     *         name_cn: '', // 中文名称
     *         name_en: '', // 英文名称
     *         url: '', // 路由 、url
     *         permission: '', // 权限
     *         type: '', // 类型   0：目录   1：菜单   2：按钮
     *         icon: '', // 图标
     *         sort: '' // 排序
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @PostMapping("/update")
    @RequiresPermissions("backstage:menu:update")
    @OperationLog("编辑菜单")
    public R update(@RequestBody @Validated(Update.class) MenuEntity MenuEntity) {
        validated(MenuEntity);
        MenuEntity.setUpdater(getAdminId());
        menuService.update(MenuEntity);
        return R.success();
    }

    /**
     * 菜单删除
     *
     * @api {POST} /backstage/menu/delete delete
     * @apiDescription 菜单删除
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName delete
     * @apiParamExample 请求参数示例
     * {
     *     	   id: '' // ID
     * }
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!'
     * }
     */
    @PostMapping("/delete")
    @RequiresPermissions("backstage:menu:delete")
    @OperationLog("删除菜单")
    public R delete(@RequestBody Map<String, Long> params) {
        Long id = params.get("id");

        List<MenuDto> list = menuService.queryAllMenuByParentId(id);

        if (list.size() > 0) {
            return R.error(Constant.WARNING_CODE, "请先删除子菜单或者按钮!");
        }

        menuService.removeById(id);
        return R.success();
    }

    /**
     * 是否显示
     *
     * @api {POST} /backstage/menu/display display
     * @apiDescription 是否显示
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName display
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
    @RequestMapping("/display")
    @RequiresPermissions("backstage:menu:display")
    @OperationLog("显示/隐藏菜单")
    public R display(@RequestBody @Validated StatusVo<Long, Integer> statusVo){
        MenuEntity MenuEntity = new MenuEntity();
        MenuEntity.setId(statusVo.getKey());
        MenuEntity.setIsDisplay(statusVo.getValue());

        menuService.updateById(MenuEntity);

        return R.success();
    }

    /**
     * 是否缓存
     *
     * @api {POST} /backstage/menu/alive alive
     * @apiDescription 是否显示
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName alive
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
    @RequestMapping("/alive")
    @RequiresPermissions("backstage:menu:alive")
    @OperationLog("缓存/不缓存菜单")
    public R alive(@RequestBody @Validated StatusVo<Long, Integer> statusVo){
        MenuEntity MenuEntity = new MenuEntity();
        MenuEntity.setId(statusVo.getKey());
        MenuEntity.setIsAlive(statusVo.getValue());

        menuService.updateById(MenuEntity);

        return R.success();
    }

    /**
     * 是否显示在标签栏
     *
     * @api {POST} /backstage/menu/tab tab
     * @apiDescription 是否显示
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName tab
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
    @RequestMapping("/tab")
    @RequiresPermissions("backstage:menu:tab")
    @OperationLog("标签栏显示/隐藏菜单")
    public R tab(@RequestBody @Validated StatusVo<Long, Integer> statusVo){
        MenuEntity MenuEntity = new MenuEntity();
        MenuEntity.setId(statusVo.getKey());
        MenuEntity.setIsTab(statusVo.getValue());

        menuService.updateById(MenuEntity);

        return R.success();
    }

    /**
     * 是否支持标签栏多开
     *
     * @api {POST} /backstage/menu/multiple multiple
     * @apiDescription 是否显示
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName multiple
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
    @RequestMapping("/multiple")
    @RequiresPermissions("backstage:menu:multiple")
    @OperationLog("支持/不支持标签多开菜单")
    public R multiple(@RequestBody @Validated StatusVo<Long, Integer> statusVo){
        MenuEntity MenuEntity = new MenuEntity();
        MenuEntity.setId(statusVo.getKey());
        MenuEntity.setIsMultiple(statusVo.getValue());

        menuService.updateById(MenuEntity);

        return R.success();
    }

    /**
     * 菜单下拉选择 TODO:不包括按钮
     *
     * @api {GET} /backstage/menu/select select
     * @apiDescription 菜单下拉选择
     * @apiVersion 1.0.0
     * @apiGroup Menu
     * @apiName get
     * @apiParamExample 请求参数示例
     * {}
     * @apiSuccessExample 响应结果示例
     * {
     *     code: 0,
     *     status: 'success',
     *     message: '成功!',
     *     data: {
     *     	   id: '', // ID
     *         parent_id: '', // 父级ID
     *         name_cn: '', // 中文名称
     *         name_en: '' // 英文名称
     *     }
     * }
     */
    @GetMapping("/select")
    @RequiresPermissions("backstage:menu:select")
    public R select() {
        return R.success(menuService.queryNoButtonMenu());
    }

    /**
     * 校验菜单参数
     * @param MenuEntity 菜单实体
     */
    private void validated(MenuEntity MenuEntity) {
        if (MenuEntity.getType() == Constant.MenuType.MENU.getValue()) {
            if (StringUtils.isBlank(MenuEntity.getUrl())) {
                throw new RunException(Constant.WARNING_CODE, "菜单URL不能为空");
            }
        }

        // 目录
        if (MenuEntity.getType() == Constant.MenuType.CATALOG.getValue()) {
            if (MenuEntity.getParentId() != 0) {
                throw new RunException(Constant.WARNING_CODE, "上级菜单只能为目录类型");
            }
        }
        // 菜单
        if (MenuEntity.getType() == Constant.MenuType.MENU.getValue()) {
            if (MenuEntity.getParentId() != 0) {
                int parentType = menuService.queryById(MenuEntity.getParentId()).getType();
                if (parentType != Constant.MenuType.CATALOG.getValue()) {
                    throw new RunException(Constant.WARNING_CODE, "上级菜单只能为目录类型");
                }
            }
        }
        // 按钮
        if (MenuEntity.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (MenuEntity.getParentId() == 0) {
                throw new RunException(Constant.WARNING_CODE, "上级菜单只能为目录类型");
            }
            if (MenuEntity.getParentId() != 0) {
                int parentType = menuService.queryById(MenuEntity.getParentId()).getType();
                if (parentType != Constant.MenuType.MENU.getValue()) {
                    throw new RunException(Constant.WARNING_CODE, "上级菜单只能为菜单类型");
                }
            }
        }
    }

}
