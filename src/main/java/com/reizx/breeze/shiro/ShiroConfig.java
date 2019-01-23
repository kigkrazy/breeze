package com.reizx.breeze.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * shiro相关配置
 */
@Configuration
public class ShiroConfig {
    /**
     * 注入SecurityManager
     *
     * @param breezeRealm 自定义域
     * @return SecurityManager
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(BreezeRealm breezeRealm) {
        return null;
    }

    /**
     * 注入Filter
     *
     * @param securityManager
     * @return ShiroFilterFactoryBean
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
        return null;
    }
}
