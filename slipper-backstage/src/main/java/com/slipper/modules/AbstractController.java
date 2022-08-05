package com.slipper.modules;

import com.slipper.service.modules.administrator.model.dto.AdministratorRoleDTO;
import com.slipper.shiro.utils.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected AdministratorRoleDTO getAdministrator() {
		return ShiroUtils.getAdministrator();
	}

	protected Long getAdministratorId() {
		return ShiroUtils.getAdministratorId();
	}

	protected Long getEnterpriseId() {
		return ShiroUtils.getEnterpriseId();
	}
}
