package com.slipper.service.modules.log.login.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 登录日志
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
@TableName("login_log")
public class LoginLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
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
    private String requestData;
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
