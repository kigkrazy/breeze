package com.reizx.breeze.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * 自定义token类
 */
public class BreezeToken implements AuthenticationToken {    // 密钥
    private String token;

    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
