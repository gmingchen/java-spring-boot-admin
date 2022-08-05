package com.slipper.service.modules.log.operation.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志 用户信息
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class OperationLogVO implements Serializable {
    private static final long serialVersionUID = 1L;

    public Long id;
    /**
     * 操作
     */
    public String operation;
    /**
     * 请求URL
     */
    public String url;
    /**
     * 请求方式
     */
    public String method;
    /**
     * 请求参数
     */
    @JsonProperty("request_data")
    public String requestData;
    /**
     * 响应参数
     */
    @JsonProperty("response_data")
    public String responseData;
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
     * 用户代理
     */
    public String agent;
    /**
     * 执行时长
     */
    public Long times;
    /**
     * 完整方法名
     */
    @JsonProperty("method_name")
    public String methodName;
    /**
     * 创建者
     */
    public Long creator;
    /**
     * 头像
     */
    public String avatar;
    /**
     * 用户名
     */
    public String username;
    /**
     * 昵称
     */
    public String nickname;
    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    public Date createdAt;
}
