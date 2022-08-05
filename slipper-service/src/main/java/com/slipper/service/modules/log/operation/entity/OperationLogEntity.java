package com.slipper.service.modules.log.operation.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
@TableName("operation_log")
public class OperationLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 操作
     */
    private String operation;
    /**
     * 请求URL
     */
    private String url;
    /**
     * 请求方式
     */
    private String method;
    /**
     * 请求参数
     */
    @JsonProperty("request_data")
    private String requestData;
    /**
     * 响应参数
     */
    @JsonProperty("response_data")
    private String responseData;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 地址
     */
    private String address;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * 操作系统
     */
    @JsonProperty("operating_system")
    private String operatingSystem;
    /**
     * 用户代理
     */
    private String agent;
    /**
     * 执行时长
     */
    private Long times;
    /**
     * 完整方法名
     */
    @JsonProperty("method_name")
    private String methodName;
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
     * 是否删除：0-否 1-是
     */
    @TableLogic
    private Integer deleted;
}
