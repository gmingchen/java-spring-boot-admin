package com.slipper.service.modules.login.service;

import com.slipper.service.modules.login.model.form.BackstageLoginForm;
import com.slipper.service.modules.token.entity.TokenEntity;

/**
 * 登录
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface LoginService {

    /**
     * 后台登录
     * @param backstageLoginForm 登录参数
     * @return
     */
    TokenEntity backstageLogin(BackstageLoginForm backstageLoginForm);

    /**
     * 后台登出
     * @param id 管理员ID
     */
    void backstageLogout(Long id);
}
