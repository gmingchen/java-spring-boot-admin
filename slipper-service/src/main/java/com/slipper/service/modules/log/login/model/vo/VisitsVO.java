package com.slipper.service.modules.log.login.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 最近登录用户量
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class VisitsVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 日期
     */
    private String date;
    /**
     * 访问量
     */
    private Integer count;
}
