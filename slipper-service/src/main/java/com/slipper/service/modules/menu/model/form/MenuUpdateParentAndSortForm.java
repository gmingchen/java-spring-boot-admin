package com.slipper.service.modules.menu.model.form;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 菜单 更新 父级 和 排序 参数
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class MenuUpdateParentAndSortForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 父ID
     */
    @NotNull(message = "父级ID不能为空")
    @JsonProperty("parent_id")
    private Long parentId;
    /**
     * 排序ID 当前菜单同级的菜单ID数组
     */
    @Size(min = 1, message = "至少有一个排序ID")
    @JsonProperty("sort_ids")
    private List<Long> sortIds;
}
