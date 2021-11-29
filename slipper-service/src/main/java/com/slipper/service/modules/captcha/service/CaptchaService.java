package com.slipper.service.modules.captcha.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.slipper.service.modules.captcha.entity.CaptchaEntity;

import java.awt.image.BufferedImage;

/**
 * 验证码
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
public interface CaptchaService extends IService<CaptchaEntity> {

    /**
     * 获取验证码
     * @param uuid uuid
     * @return
     */
    BufferedImage createCaptcha(String uuid);

    /**
     * 验证验证码
     * @param uuid uuid
     * @param code 验证码
     * @return
     */
    boolean validateCode(String uuid, String code);
}

