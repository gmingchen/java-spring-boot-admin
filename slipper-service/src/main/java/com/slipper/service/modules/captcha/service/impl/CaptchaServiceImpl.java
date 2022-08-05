package com.slipper.service.modules.captcha.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;
import com.slipper.common.exception.RunException;
import com.slipper.common.utils.Constant;
import com.slipper.common.utils.DateUtils;
import com.slipper.service.modules.captcha.dao.CaptchaDao;
import com.slipper.service.modules.captcha.entity.CaptchaEntity;
import com.slipper.service.modules.captcha.service.CaptchaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * 验证码
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Service("captchaService")
public class CaptchaServiceImpl extends ServiceImpl<CaptchaDao, CaptchaEntity> implements CaptchaService {

    @Resource
    private Producer producer;

    @Override
    public BufferedImage createCaptcha(String uuid, Integer mode) {
        if (StringUtils.isBlank(uuid)) {
            String message = Constant.VERIFICATION_ERROR_MESSAGE + "-UUID不能为空";
            throw new RunException(Constant.VERIFICATION_ERROR_CODE, message);
        }
        String code = producer.createText();
        Date now = new Date();
        CaptchaEntity captchaEntity = new CaptchaEntity();
        captchaEntity.setUuid(uuid);
        captchaEntity.setCode(code);
        captchaEntity.setExpiredAt(DateUtils.addMinutes(now, 1));
        captchaEntity.setCreatedAt(now);
        switch (mode) {
            case 1:
                this.save(captchaEntity);
                break;
            default:
        }

        return producer.createImage(code);
    }

    @Override
    public boolean validateCode(String uuid, String code, Integer mode) {
        CaptchaEntity captchaEntity = null;
        switch (mode) {
            case 1:
                LambdaQueryWrapper<CaptchaEntity> wrapper = new LambdaQueryWrapper<CaptchaEntity>()
                        .eq(CaptchaEntity::getUuid, uuid);
                captchaEntity = this.getOne(wrapper);
                // 验证存在验证码之后删除验证码
                if (captchaEntity != null) {
                    this.removeById(captchaEntity.getUuid());
                }
                break;
            default:
        }
        if (captchaEntity != null && captchaEntity.getCode().equals(code) && captchaEntity.getExpiredAt().getTime() >= System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

}