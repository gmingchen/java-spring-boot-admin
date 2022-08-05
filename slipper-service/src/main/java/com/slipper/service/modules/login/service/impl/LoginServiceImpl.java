package com.slipper.service.modules.login.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.gson.Gson;
import com.slipper.common.exception.RunException;
import com.slipper.common.utils.Constant;
import com.slipper.common.utils.HttpContextUtils;
import com.slipper.service.modules.administrator.entity.AdministratorEntity;
import com.slipper.service.modules.administrator.service.AdministratorService;
import com.slipper.service.modules.captcha.service.CaptchaService;
import com.slipper.service.modules.enterprise.entity.EnterpriseEntity;
import com.slipper.service.modules.enterprise.service.EnterpriseService;
import com.slipper.service.modules.log.login.entity.LoginLogEntity;
import com.slipper.service.modules.log.login.service.LoginLogService;
import com.slipper.service.modules.login.model.form.BackstageLoginForm;
import com.slipper.service.modules.login.service.LoginService;
import com.slipper.service.modules.token.entity.TokenEntity;
import com.slipper.service.modules.token.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
    private EnterpriseService enterpriseService;
    @Resource
    private AdministratorService administratorService;
    @Resource
    private TokenService tokenService;
    @Resource
    private LoginLogService loginLogService;

    @Override
    public TokenEntity backstageLogin(BackstageLoginForm backstageLoginForm) {
        boolean result = captchaService.validateCode(backstageLoginForm.getUuid(), backstageLoginForm.getCode(), Constant.STORAGE_MODE);
        if (!result) {
            throw new RunException(Constant.WARNING_CODE, "验证码不正确！");
        }

        String message = null;
        // 验证账户
        LambdaQueryWrapper<AdministratorEntity> wrapper = new LambdaQueryWrapper<AdministratorEntity>()
                .eq(AdministratorEntity::getUsername, backstageLoginForm.getUsername());
        AdministratorEntity administratorEntity = administratorService.getOne(wrapper);
        if (administratorEntity == null) {
            message = "帐号不存在！";
        } else if (administratorEntity.getStatus() == 0) {
            message = "账户已被冻结！";
        } else if (!administratorEntity.getPassword().equals(new Sha256Hash(backstageLoginForm.getPassword(), administratorEntity.getSalt()).toHex())) {
            message = "密码不正确！";
        }

        // 验证企业
        if (StringUtils.isBlank(message)) {
            LambdaQueryWrapper<EnterpriseEntity> enterpriseWrapper = new LambdaQueryWrapper<EnterpriseEntity>()
                    .eq(EnterpriseEntity::getId, administratorEntity.getEnterpriseId());
            EnterpriseEntity enterpriseEntity = enterpriseService.getOne(enterpriseWrapper);
            if (enterpriseEntity == null) {
                message = "企业不存在！";
            } else if (enterpriseEntity.getStatus() == 0) {
                message = "企业已被冻结！";
            }
        }

        // 添加登录日志
        addLog(
                backstageLoginForm,
                administratorEntity != null ? administratorEntity.getId() : null,
                StringUtils.isBlank(message) ? "登录成功" : message
        );

        if (StringUtils.isNotBlank(message)) {
            throw new RunException(Constant.WARNING_CODE, message);
        }

        return tokenService.createToken(administratorEntity.getId(), Constant.STORAGE_MODE);
    }

    @Override
    public void backstageLogout(Long id) {
        tokenService.destroyToken(id, Constant.STORAGE_MODE);
    }

    /**
     * 添加登录日志
     * @param loginForm 登录参数
     * @param administratorId 管理员ID
     * @param message 登录信息
     */
    private void addLog(BackstageLoginForm loginForm, Long administratorId, String message) {
        LoginLogEntity loginLogEntity = new LoginLogEntity();
        loginLogEntity.setAccount(loginForm.getUsername());
        loginLogEntity.setMessage(message);
        loginLogEntity.setRequestData(new Gson().toJson(loginForm));

        loginLogEntity.setIp(HttpContextUtils.getIp());
        loginLogEntity.setBrowser(HttpContextUtils.getBrowser());
        loginLogEntity.setOperatingSystem(HttpContextUtils.getOperatingSystem());
        loginLogEntity.setAgent(HttpContextUtils.getUserAgent());

        loginLogEntity.setCreator(administratorId);
        loginLogEntity.setCreatedAt(new Date());

        loginLogService.save(loginLogEntity);
    }
}
