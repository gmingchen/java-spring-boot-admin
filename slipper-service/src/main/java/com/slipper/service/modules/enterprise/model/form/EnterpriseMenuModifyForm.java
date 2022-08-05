package com.slipper.service.modules.enterprise.model.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 企业菜单权限修改
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class EnterpriseMenuModifyForm implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 企业ID
     */
    @NotNull(message = "企业ID不能为空")
    @JsonProperty("enterprise_id")
    private Long enterpriseId;
    /**
     * 菜单ID
     */
    @Size(min = 1, message = "至少选择一个菜单权限")
    @JsonProperty("menu_ids")
    private List<Long> menuIds;

}
