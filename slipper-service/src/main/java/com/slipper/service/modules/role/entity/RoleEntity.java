package com.slipper.service.modules.role.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.slipper.common.validator.group.Create;
import com.slipper.common.validator.group.Update;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
@TableName("role")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID不能为空", groups = Update.class)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = {Create.class, Update.class})
    private String name;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否显示：0-否 1-是
     * 用于不给用户操作的角色 系统角色
     */
    @TableField("`show`")
    private Integer show;
    /**
     * 企业ID
     */
    @JsonProperty("enterprise_id")
    private Long enterpriseId;
    /**
     * 创建者
     */
    private Long creator;
    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    private Date createdAt;
    /**
     * 更新者
     */
    private Long updater;
    /**
     * 更新时间
     */
    @JsonProperty("updated_at")
    private Date updatedAt;
    /**
     * 是否删除：0-否 1-是
     */
    @TableLogic
    private Integer deleted;
}
