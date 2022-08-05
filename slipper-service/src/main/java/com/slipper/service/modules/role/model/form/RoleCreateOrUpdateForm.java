package com.slipper.service.modules.role.model.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 角色 新增 编辑 参数
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class RoleCreateOrUpdateForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID不能为空", groups = Update.class)
    public Long id;
    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = {Create.class, Update.class})
    public String name;
    /**
     * 备注
     */
    public String remark;
    /**
     * 企业菜单ID
     */
    @Size(min = 1, message = "至少选择一个权限")
    @JsonProperty("enterprise_menu_ids")
    public List<Long> enterpriseMenuIds;
    /**
     * 企业ID
     */
    @JsonProperty("enterprise_id")
    private Long enterpriseId;
}
