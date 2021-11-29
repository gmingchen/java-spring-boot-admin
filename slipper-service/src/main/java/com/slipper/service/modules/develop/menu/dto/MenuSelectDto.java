package com.slipper.service.modules.develop.menu.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MenuSelectDto {
    @TableId
    private Long id;
    /**
     * 父ID
     */
    @JsonProperty("parent_id")
    private Long parentId;
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
}
