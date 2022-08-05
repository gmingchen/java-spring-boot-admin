package com.slipper.service.modules.menu.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单 只包括 ID 中英文名称 父级ID
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class MenuSimplifyTreeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 中文名称
     */
    @JsonProperty("name_cn")
    private String nameCn;
    /**
     * 英文名称
     */
    @JsonProperty("name_en")
    private String nameEn;
    /**
     * 类型：0-目录 1-菜单 2-按钮 3-iframe 4-外链
     */
    private Integer type;
    /**
     * 父级ID
     */
    @JsonProperty("parent_id")
    private Long parentId;
    /**
     * 子集
     */
    private List<MenuSimplifyTreeVO> children;
}
