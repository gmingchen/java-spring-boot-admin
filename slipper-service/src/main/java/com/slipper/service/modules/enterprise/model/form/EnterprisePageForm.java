package com.slipper.service.modules.enterprise.model.form;

import com.slipper.service.model.PageDateModel;
import com.slipper.service.model.PageModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 企业列表分页参数
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Data
public class EnterprisePageForm extends PageModel implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 名称
     */
    private String name;
}
