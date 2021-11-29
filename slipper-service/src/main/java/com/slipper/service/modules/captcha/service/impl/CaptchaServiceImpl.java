package com.slipper.service.modules.captcha.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;
import com.slipper.service.modules.captcha.dao.CaptchaDao;
import com.slipper.service.modules.captcha.entity.CaptchaEntity;
import com.slipper.service.modules.captcha.service.CaptchaService;
import com.slipper.common.exception.RunException;
import com.slipper.common.utils.Constant;
import com.slipper.common.utils.DateUtils;
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
    public BufferedImage createCaptcha(String uuid) {
        if (StringUtils.isBlank(uuid)) {
            throw new RunException(Constant.VERIFICATION_ERROR_CODE, Constant.VERIFICATION_ERROR);
        }
        String code = producer.createText();
        CaptchaEntity captchaEntity = new CaptchaEntity();
        captchaEntity.setUuid(uuid);
        captchaEntity.setCode(code);
        captchaEntity.setExpiredAt(DateUtils.addMinutes(new Date(), 1));
        this.save(captchaEntity);

        return producer.createImage(code);
    }

    @Override
    public boolean validateCode(String uuid, String code) {
        QueryWrapper<CaptchaEntity> wrapper =
                new QueryWrapper<CaptchaEntity>()
                        .eq("uuid", uuid);
        CaptchaEntity captchaEntity = this.getOne(wrapper);
        if(captchaEntity == null) {
            return false;
        }

        // 验证存在验证码之后删除验证码
        this.removeById(captchaEntity.getUuid());

        if (captchaEntity.getCode().equals(code) && captchaEntity.getExpiredAt().getTime() >= new Date().getTime()) {
            return true;
        }

        return false;
    }
}