package com.slipper.service.modules.enterprise.model.form;

import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 企业 新增 编辑 参数
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class EnterpriseCreateOrUpdateForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID不能为空", groups = Update.class)
    private Long id;
    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = {Create.class, Update.class})
    private String name;
    /**
     * Logo
     */
    private String logo;
    /**
     * 描述
     */
    private String describe;
}
