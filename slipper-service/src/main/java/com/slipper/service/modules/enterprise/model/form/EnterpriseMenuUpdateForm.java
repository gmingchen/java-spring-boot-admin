package com.slipper.service.modules.enterprise.model.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 企业菜单编辑
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class EnterpriseMenuUpdateForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID不能为空")
    private Long id;
    /**
     * 中文名称
     */
    @NotBlank(message = "中文名称不能为空")
    @JsonProperty("name_cn")
    private String nameCn;
    /**
     * 英文名称
     */
    @NotBlank(message = "英文名称不能为空")
    @JsonProperty("name_en")
    private String nameEn;
    /**
     * 图标
     */
//    @NotBlank(message = "图标不能为空")
    private String icon;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 更新者
     */
    private Long updater;
    /**
     * 更新时间
     */
    @JsonProperty("updated_at")
    private Date updatedAt;
}
