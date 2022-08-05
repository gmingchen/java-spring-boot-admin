package com.slipper.service.modules.administrator.model.form;

import com.slipper.service.model.PageDateModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 管理员列表分页参数
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class EnterpriseAdministratorPageDateForm extends PageDateModel implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 昵称 / 帐号
     */
    private String name;
    /**
     * 企业ID
     */
    private Long id;
}
