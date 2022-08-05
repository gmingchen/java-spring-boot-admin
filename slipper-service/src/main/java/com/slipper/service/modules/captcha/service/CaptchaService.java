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
     * @param uuid UUID
     * @param mode 形式：1-存储在数据库 2-存储在Redis
     * @return
     */
    BufferedImage createCaptcha(String uuid, Integer mode);

    /**
     * 验证验证码
     * @param uuid uuid
     * @param code 验证码
     * @param mode 形式：1-存储在数据库 2-存储在Redis
     * @return
     */
    boolean validateCode(String uuid, String code, Integer mode);
}
