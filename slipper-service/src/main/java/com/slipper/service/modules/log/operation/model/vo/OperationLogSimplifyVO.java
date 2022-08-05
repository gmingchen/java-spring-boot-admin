package com.slipper.service.modules.log.operation.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 当前管理员操作日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class OperationLogSimplifyVO implements Serializable {
    private static final long serialVersionUID = 1L;

    public Long id;
    /**
     * 操作
     */
    private String operation;
    /**
     * IP地址
     */
    public String ip;
    /**
     * 地址
     */
    public String address;
    /**
     * 浏览器
     */
    public String browser;
    /**
     * 操作系统
     */
    @JsonProperty("operating_system")
    public String operatingSystem;
    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    public Date createdAt;
}
