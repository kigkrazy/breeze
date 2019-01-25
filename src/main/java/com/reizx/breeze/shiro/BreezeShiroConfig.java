package com.reizx.breeze.shiro;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * shiro相关配置
 */
@Configuration
public class BreezeShiroConfig {
    public final static String BREEZE_SHIRO_FILTER = "breezeShiroFilter";

    /**
     * 注入SecurityManager
     *
     * @param breezeRealm 自定义域
     * @return SecurityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(BreezeRealm breezeRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 绑定Realm
        manager.setRealm(breezeRealm);
        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);
        return manager;
    }

    /**
     * 注入Filter
     *
     * @param securityManager
     * @return ShiroFilterFactoryBean
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put(BREEZE_SHIRO_FILTER, new BreezeShiroFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);
//        factoryBean.setUnauthorizedUrl("/401");//设置没有权限时候跳转

        /*
         * 自定义url规则
         * http://shiro.apache.org/web.html#urls-
         */
        Map<String, String> filterRuleMap = new HashMap<>();
        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/**", BREEZE_SHIRO_FILTER);
        // 访问401和404页面不通过我们的Filter
//        filterRuleMap.put("/401", "anon");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }
}
