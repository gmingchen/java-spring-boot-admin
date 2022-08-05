package com.slipper.shiro.utils;

import com.slipper.service.modules.administrator.model.dto.AdministratorRoleDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Shiro 工具类
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public class ShiroUtils {

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static AdministratorRoleDTO getAdministrator() {
        return (AdministratorRoleDTO) SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getAdministratorId() {
        return getAdministrator().getId();
    }

    public static Long getEnterpriseId() {
        return getAdministrator().getEnterpriseId();
    }

    public static String getIp() {
        return getSubject().getSession().getHost();
    }

}
