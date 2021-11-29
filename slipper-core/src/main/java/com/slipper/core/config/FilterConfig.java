package com.slipper.core.config;

import com.slipper.core.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * Filter配置
 *
 * @author slipper
 * @email 1240235512@qq.com
 * @date 1995-08-30 00:00:00
 */
@Configuration
public class FilterConfig {

    /**
     * shiro 过滤
     * @return
     */
    @Bean
    public FilterRegistrationBean shiroFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setEnabled(true);
        registration.setOrder(Integer.MAX_VALUE - 1);
        registration.addUrlPatterns("/*");
        return registration;
    }

    /**
     * xss 过滤
     * @return
     */
    @Bean
    public FilterRegistrationBean xssFilterRegister() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 设置系统过滤器 (setFilter就是你所定义的过滤器filter类)
        registration.setFilter(new XssFilter());
        // 过滤所有路径
        registration.addUrlPatterns("/*");
        // 过滤器名称
        registration.setName("XssFilter");
        // 优先级
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }

}
