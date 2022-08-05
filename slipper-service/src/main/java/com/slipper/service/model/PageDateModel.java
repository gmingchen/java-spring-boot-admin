package com.slipper.service.model;

import lombok.Data;

/**
 * 分页时间数据模型
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class PageDateModel {
    /**
     * 当前页
     */
    private Integer current;
    /**
     * 分页大小
     */
    private Integer size;
    /**
     * 开始时间
     */
    private String start;
    /**
     * 结束时间
     */
    private String end;
}
