package com.slipper.service.modules.log.login.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志 用户信息
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class LoginLogVO implements Serializable {
    private static final long serialVersionUID = 1L;

    public Long id;
    /**
     * 登录帐号
     */
    private String account;
    /**
     * 登录信息
     */
    private String message;
    /**
     * 请求参数
     */
    @JsonProperty("request_data")
    public String requestData;
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
