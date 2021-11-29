package com.slipper.shiro.config;

import com.slipper.shiro.filter.OAuth2Filter;
import com.slipper.shiro.realm.OAuth2Realm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 注入自定义 Realm
     * @return
     */
    @Bean
    public OAuth2Realm oAuth2Realm() {
        OAuth2Realm oAuth2Realm = new OAuth2Realm();
        return oAuth2Realm;
    }

    /**
     * 配置Realm权限管理认证
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(oAuth2Realm());
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    /**
     * 配置过滤
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("oAuth2", new OAuth2Filter());
        shiroFilter.setFilters(filterMap);

        Map<String, String> mappingMap = new HashMap<>();
        mappingMap.put("/druid/**", "anon"); // Druid 监控
        mappingMap.put("/static/apidoc/**", "anon"); // api 文档
        mappingMap.put("/backstage/captcha.jpg", "anon"); // 图片验证码
        mappingMap.put("/backstage/login", "anon"); // 登录
        mappingMap.put("/oss/file/flow/**", "anon"); // 文件流
        mappingMap.put("/websocket/**", "anon"); // websocket
        mappingMap.put("/**", "oAuth2");
        shiroFilter.setFilterChainDefinitionMap(mappingMap);

        return shiroFilter;
    }

    /**
     * 管理 Shiro 生命周期
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 代理生成器 TODO: 注释掉 不注释会执行两次 doGetAuthorizationInfo
     * 需 SpringAOP 来扫描 @RequiresRoles、@RequiresPermissions
     * 需配合 AuthorizationAttributeSourceAdvisor 一起使用 否则注解无效
     * @return
     */
    /*@Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }*/

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
