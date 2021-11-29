package com.slipper.common.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Kaptche 图片验证配置
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Component
public class CaptchaConfig {

    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();

        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "no"); // 是否有边框
        properties.setProperty("kaptcha.border.color", "black"); // 边框颜色
        properties.setProperty("kaptcha.border.thickness", "1"); // 边框粗细
        properties.setProperty("kaptcha.producer.impl", "DefaultKaptcha"); // 验证码生成器
//        properties.setProperty("kaptcha.textproducer.impl", "DefaultTextCreator"); // 验证码文本生成器
        properties.setProperty("kaptcha.textproducer.char.string", "12345678abcdefghijklmnopqrstuvwxyz"); // 验证码文本字符内容范围
        properties.setProperty("kaptcha.textproducer.char.length", "4"); // 验证码文本字符长度
        properties.setProperty("kaptcha.textproducer.font.names", "Arial,Courier,宋体,楷体,微软雅黑"); //  验证码文本字体样式
        properties.setProperty("kaptcha.textproducer.font.size", "40"); // 验证码文本字符大小
        properties.setProperty("kaptcha.textproducer.font.color", "black"); // 验证码文本字符颜色
        properties.setProperty("kaptcha.textproducer.char.space", "6"); // 验证码文本字符间距
//        properties.setProperty("kaptcha.noise.impl", "DefaultNoise"); // 验证码噪点生成对象
        properties.setProperty("kaptcha.noise.color", "black"); // 验证码噪点颜色
//        properties.setProperty("kaptcha.obscurificator.impl", "WaterRipple"); // 验证码样式引擎
//        properties.setProperty("kaptcha.word.impl", "DefaultWordRenderer"); // 验证码文本字符渲染
//        properties.setProperty("kaptcha.background.impl", "DefaultBackground"); // 验证码背景生成器
        properties.setProperty("kaptcha.background.clear.from", "white"); // 验证码背景颜色渐进
        properties.setProperty("kaptcha.background.clear.to", "white"); // 验证码背景颜色渐进
        properties.setProperty("kaptcha.image.width", "200"); // 验证码图片宽度
        properties.setProperty("kaptcha.image.height", "50"); // 验证码图片高度
//        Color.white
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
