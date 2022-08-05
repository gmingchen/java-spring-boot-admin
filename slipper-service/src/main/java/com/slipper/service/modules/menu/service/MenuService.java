package com.slipper.service.modules.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.service.modules.menu.entity.MenuEntity;
import com.slipper.service.modules.menu.model.form.MenuCreateOrUpdateForm;
import com.slipper.service.modules.menu.model.form.MenuUpdateParentAndSortForm;
import com.slipper.service.modules.menu.model.vo.MenuSimplifyTreeVO;
import com.slipper.service.modules.menu.model.vo.MenuTreeVO;

import java.util.List;

/**
 * 菜单权限
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface MenuService extends IService<MenuEntity> {

    /**
     * 查询所有菜单 树形结构
     * @return
     */
    List<MenuTreeVO> queryList();

    /**
     * 查询所有菜单 只包括 ID 中英文名称 父级ID 树形结构
     * @return
     */
    List<MenuSimplifyTreeVO> querySelectList();

    /**
     * 菜单创建
     * @param createOrUpdateForm 参数
     * @param administratorId 管理员ID
     * @return
     */
    Long create(MenuCreateOrUpdateForm createOrUpdateForm, Long administratorId);

    /**
     * 菜单更新
     * @param createOrUpdateForm 参数
     * @param administratorId 管理员ID
     */
    void update(MenuCreateOrUpdateForm createOrUpdateForm, Long administratorId);

    /**
     * 菜单删除
     * @param id
     */
    void delete(Long id);

    /**
     * 修改菜单 父级 和 排序
     * @param updateParentAndSortForm 参数
     * @param administratorId 管理员ID
     */
    void updateParentAndSort(MenuUpdateParentAndSortForm updateParentAndSortForm, Long administratorId);
}
