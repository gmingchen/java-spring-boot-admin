package com.slipper.service.modules.log.login.model.form;

import com.slipper.service.model.PageDateModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录日志分页参数
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class LoginLogPageDateForm extends PageDateModel implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 管理员 昵称 / 帐号
     */
    public String name;
    /**
     * 企业ID
     */
    public Long id;
}
