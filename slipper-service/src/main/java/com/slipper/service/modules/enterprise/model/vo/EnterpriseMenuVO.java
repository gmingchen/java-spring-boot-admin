package com.slipper.service.modules.enterprise.model.vo;

import com.slipper.service.modules.enterprise.model.dto.EnterpriseMenuTreeDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 企业菜单权限
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class EnterpriseMenuVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 权限数组 逗号分割
     */
    private Set<String> permissions;
    /**
     * 菜单 树形结构
     */
    private List<EnterpriseMenuTreeDTO> menus;
}
