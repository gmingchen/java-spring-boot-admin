package com.slipper.service.modules.log.login.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 当前管理员登录日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class LoginLogSimplifyVO implements Serializable {
    private static final long serialVersionUID = 1L;

    public Long id;
    /**
     * 登录信息
     */
    private String message;
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
