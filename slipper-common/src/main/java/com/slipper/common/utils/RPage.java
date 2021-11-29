package com.slipper.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果封装工具类
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 * @param <T>
 */
@Data
public class RPage<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 当前页
     */
    private long current;
    /**
     * 分页大小
     */
    private long size;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 总页数
     */
    private long pages;
    /**
     * 记录
     */
    private List<T> list;

    public RPage(IPage<T> iPage) {
        this.current = iPage.getCurrent();
        this.size = iPage.getSize();
        this.total = iPage.getTotal();
        this.pages = iPage.getPages();
        this.list = iPage.getRecords();
    }
}
