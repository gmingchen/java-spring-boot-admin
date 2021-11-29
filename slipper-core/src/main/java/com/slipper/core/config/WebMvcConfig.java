package com.slipper.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web mvc config
 *
 * @author gumingchen
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 页面跨域访问Controller过滤
     * @param corsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        // 设置允许跨域的路由
        corsRegistry.addMapping("/**")
                .allowedOriginPatterns("*") // 设置允许跨域请求的域名
                .allowCredentials(true) // 是否允许证书（cookies）
                .allowedMethods("*") // 设置允许的方法
                .allowedHeaders("*"); // 设置允许的请求头
    }
}
