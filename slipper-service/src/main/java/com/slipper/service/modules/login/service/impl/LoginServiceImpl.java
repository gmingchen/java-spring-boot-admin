package com.slipper.service.modules.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.slipper.common.exception.RunException;
import com.slipper.common.utils.Constant;
import com.slipper.service.modules.captcha.service.CaptchaService;
import com.slipper.service.modules.login.service.LoginService;
import com.slipper.service.modules.login.vo.LoginVo;
import com.slipper.service.modules.system.admin.entity.AdminEntity;
import com.slipper.service.modules.system.admin.service.AdminService;
import com.slipper.service.modules.system.token.entity.TokenEntity;
import com.slipper.service.modules.system.token.service.TokenService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登录
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Resource
    private CaptchaService captchaService;
    @Resource
    private AdminService adminService;
    @Resource
    private TokenService tokenService;

    @Override
    public TokenEntity login(LoginVo loginVo) {
        boolean result = captchaService.validateCode(loginVo.getUuid(), loginVo.getCode());
        if (!result) {
            throw new RunException(Constant.WARNING_CODE, "验证码不正确!");
        }

        QueryWrapper<AdminEntity> wrapper =
                new QueryWrapper<AdminEntity>()
                        .eq("username", loginVo.getUsername());
        AdminEntity adminEntity = adminService.getOne(wrapper);

        if (adminEntity == null) {
            throw new RunException(Constant.WARNING_CODE, "帐号不存在");
        }
        if (adminEntity.getStatus() == 0) {
            throw new RunException(Constant.WARNING_CODE, "账户已被冻结!");
        }
        if (!adminEntity.getPassword().equals(new Sha256Hash(loginVo.getPassword(), adminEntity.getSalt()).toHex())) {
            throw new RunException(Constant.WARNING_CODE, "密码不正确!");
        }

        return tokenService.createToken(adminEntity.getId());
    }

    @Override
    public void logout(Long adminId) {
        tokenService.destroyToken(adminId);
    }
}
