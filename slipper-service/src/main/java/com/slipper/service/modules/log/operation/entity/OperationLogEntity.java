package com.slipper.service.modules.log.operation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
@TableName("operation_log")
public class OperationLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    private String username;
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
    private String params;
    /**
     * 类名和方法名
     */
    @JsonProperty("class_name")
    private String className;
    /**
     * 执行时长
     */
    private Long times;
    /**
     * ip地址
     */
    private String ip;
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

}
