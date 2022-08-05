package com.slipper.service.modules.menu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.slipper.service.modules.menu.entity.MenuEntity;
import com.slipper.service.modules.menu.model.vo.MenuSimplifyTreeVO;
import com.slipper.service.modules.menu.model.vo.MenuTreeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单权限
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Mapper
public interface MenuDao extends BaseMapper<MenuEntity> {
    /**
     * 查询所有菜单 树形结构
     * @return
     */
    List<MenuTreeVO> queryList();
    /**
     * 查询所有菜单 只包括 ID 中英文名称 父级ID
     * @return
     */
    List<MenuSimplifyTreeVO> querySelectList();

    /**
     * 批量修改排序
     * @param list
     */
    void updateSortBatch(@Param("list") List<MenuEntity> list);
}
