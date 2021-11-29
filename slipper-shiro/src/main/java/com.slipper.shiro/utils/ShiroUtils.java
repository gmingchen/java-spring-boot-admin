package com.slipper.shiro.utils;

import com.slipper.service.modules.system.admin.dto.AdminDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Shiro 工具类
 *
 * @author Loafer
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public class ShiroUtils {

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static AdminDto getAdmin() {
        return (AdminDto) SecurityUtils.getSubject().getPrincipal();
    }

    public static Long getAdminId() {
        return getAdmin().getId();
    }

    public static String getIp() {
        return getSubject().getSession().getHost();
    }

}
