package com.slipper.service.modules.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.slipper.common.exception.RunException;
import com.slipper.common.utils.BeanUtils;
import com.slipper.common.utils.Constant;
import com.slipper.service.modules.menu.dao.MenuDao;
import com.slipper.service.modules.menu.entity.MenuEntity;
import com.slipper.service.modules.menu.model.form.MenuCreateOrUpdateForm;
import com.slipper.service.modules.menu.model.form.MenuUpdateParentAndSortForm;
import com.slipper.service.modules.menu.model.vo.MenuSimplifyTreeVO;
import com.slipper.service.modules.menu.model.vo.MenuTreeVO;
import com.slipper.service.modules.menu.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, MenuEntity> implements MenuService {

    @Override
    public List<MenuTreeVO> queryList() {
        List<MenuTreeVO> list = baseMapper.queryList();
        // 筛选企业下根节点菜单 并 筛选出所有的权限
        List<MenuTreeVO> rootList = list.stream().filter(item -> {
            return item.getParentId() == 0;
        }).collect(Collectors.toList());
        // 筛选子节点菜单
        List<MenuTreeVO> result = new ArrayList<>();
        rootList.forEach(item -> result.add(findChildren(item, list)));
        return result;
    }

    @Override
    public List<MenuSimplifyTreeVO> querySelectList() {
        List<MenuSimplifyTreeVO> list = baseMapper.querySelectList();
        // 筛选企业下根节点菜单 并 筛选出所有的权限
        List<MenuSimplifyTreeVO> rootList = list.stream().filter(item -> {
            return item.getParentId() == 0;
        }).collect(Collectors.toList());
        // 筛选子节点菜单
        List<MenuSimplifyTreeVO> result = new ArrayList<>();
        rootList.forEach(item -> result.add(findChildren(item, list)));
        return result;
    }

    @Override
    public Long create(MenuCreateOrUpdateForm createOrUpdateForm, Long administratorId) {
        MenuEntity menuEntity = new MenuEntity();
        BeanUtils.copyProperties(createOrUpdateForm, menuEntity);

        validated(menuEntity);

        menuEntity.setCreator(administratorId);
        menuEntity.setCreatedAt(new Date());
        this.save(menuEntity);
        return menuEntity.getId();
    }

    @Override
    public void update(MenuCreateOrUpdateForm createOrUpdateForm, Long administratorId) {
        MenuEntity menuEntity = new MenuEntity();
        BeanUtils.copyProperties(createOrUpdateForm, menuEntity);

        validated(menuEntity);

        menuEntity.setUpdater(administratorId);
        menuEntity.setUpdatedAt(new Date());
        this.updateById(menuEntity);
    }

    @Override
    public void delete(Long id) {
        LambdaQueryWrapper<MenuEntity> wrapper = new LambdaQueryWrapper<MenuEntity>()
                .eq(MenuEntity::getParentId, id);

        Long count = this.count(wrapper);

        if (count > 0) {
            throw new RunException(Constant.WARNING_CODE, "请先删除子集菜单");
        }

        this.removeById(id);
    }

    @Transactional(rollbackFor = RunException.class)
    @Override
    public void updateParentAndSort(MenuUpdateParentAndSortForm updateParentAndSortForm, Long administratorId) {
        MenuEntity updateParentMenu = new MenuEntity();
        BeanUtils.copyProperties(updateParentAndSortForm, updateParentMenu);
        Date now = new Date();
        updateParentMenu.setUpdater(administratorId);
        updateParentMenu.setUpdatedAt(now);
        this.updateById(updateParentMenu);


        List<MenuEntity> list = new ArrayList<>();
        for (int i = 0; i < updateParentAndSortForm.getSortIds().size(); i++) {
            Long id = updateParentAndSortForm.getSortIds().get(i);
            MenuEntity updateSortMenu = new MenuEntity();
            updateSortMenu.setId(id);
            updateSortMenu.setSort(i + 1);
            updateSortMenu.setUpdater(administratorId);
            updateSortMenu.setUpdatedAt(now);
            list.add(updateSortMenu);
        }
        baseMapper.updateSortBatch(list);
    }

    /**
     * 筛选子节点菜单
     * @param menuTreeVO
     * @param list
     * @return
     */
    private MenuTreeVO findChildren(MenuTreeVO menuTreeVO, List<MenuTreeVO> list) {
        list.stream().filter(item -> menuTreeVO.getId().equals(item.getParentId()))
                .collect(Collectors.toList())
                .forEach(item -> {
                    if (menuTreeVO.getChildren() == null) {
                        menuTreeVO.setChildren(new ArrayList<>());
                    }
                    menuTreeVO.getChildren().add(findChildren(item, list));
                });
        return menuTreeVO;
    }

    /**
     * 筛选子节点菜单
     * @param menuSimplifyTreeVO
     * @param list
     * @return
     */
    private MenuSimplifyTreeVO findChildren(MenuSimplifyTreeVO menuSimplifyTreeVO, List<MenuSimplifyTreeVO> list) {
        list.stream().filter(item -> menuSimplifyTreeVO.getId().equals(item.getParentId()))
                .collect(Collectors.toList())
                .forEach(item -> {
                    if (menuSimplifyTreeVO.getChildren() == null) {
                        menuSimplifyTreeVO.setChildren(new ArrayList<>());
                    }
                    menuSimplifyTreeVO.getChildren().add(findChildren(item, list));
                });
        return menuSimplifyTreeVO;
    }

    /**
     * 校验参数
     * @param menuEntity
     */
    private void validated(MenuEntity menuEntity) {
        List<String> messages = new ArrayList<>();
        MenuEntity parent;
        if (menuEntity.getParentId() == Constant.MenuType.CATALOG.getValue()) {
            parent = new MenuEntity();
            parent.setType(Constant.MenuType.CATALOG.getValue());
        } else {
            parent = this.getById(menuEntity.getParentId());
            if (parent == null) {
                throw new RunException("父级不存在！");
            }
        }

        if (menuEntity.getType() == Constant.MenuType.CATALOG.getValue()) {
            if (parent.getType() != 0) {
                messages.add("父级只能为目录类型");
            }
            if (StringUtils.isBlank(menuEntity.getIcon())) {
                messages.add("图标不能为空！");
            }
        } else if (menuEntity.getType() == Constant.MenuType.MENU.getValue()) {
            if (parent.getType() != 0 && parent.getType() != 1) {
                messages.add("父级只能为目录类型或者菜单类型");
            }
            if (StringUtils.isBlank(menuEntity.getPermission())) {
                messages.add("授权标识不能为空！");
            }
//            if (StringUtils.isBlank(menuEntity.getIcon())) {
//                messages.add("图标不能为空！");
//            }
        } else if (menuEntity.getType() == Constant.MenuType.BUTTON.getValue()) {
            if (parent.getType() != 1) {
                messages.add("父级只能为菜单类型");
            }
            if (StringUtils.isBlank(menuEntity.getPermission())) {
                messages.add("授权标识不能为空！");
            }
        } else {
            if (parent.getType() != 0 && parent.getType() != 1) {
                messages.add("父级只能为目录类型或者菜单类型");
            }
            if (StringUtils.isBlank(menuEntity.getUrl())) {
                messages.add("路由Path / Http[s] URL不能为空！");
            }
//            if (StringUtils.isBlank(menuEntity.getIcon())) {
//                messages.add("图标不能为空！");
//            }
        }

        if (messages.size() > 0) {
            throw new RunException(Constant.VERIFICATION_ERROR_CODE, messages.toString());
        }
    }

}
