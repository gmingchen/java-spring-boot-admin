package com.slipper.service.modules.enterprise.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 企业菜单权限 精简信息
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class EnterpriseMenuSimplifyTreeVO implements Serializable {
    private static final long serialVersionUID = 1L;

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
     * 菜单ID
     */
    @JsonProperty("menuId")
    private Long menuId;
    /**
     * 菜单父级ID
     */
    @JsonProperty("parent_id")
    private Long parentId;
    /**
     * 子集
     */
    private List<EnterpriseMenuSimplifyTreeVO> children;

}
