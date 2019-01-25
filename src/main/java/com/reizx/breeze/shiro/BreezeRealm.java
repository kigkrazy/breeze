package com.reizx.breeze.shiro;


import com.reizx.breeze.modules.sys.entity.po.SysUser;
import com.reizx.breeze.modules.sys.entity.po.SysUserToken;
import com.reizx.breeze.modules.sys.service.SysUserService;
import com.reizx.breeze.modules.sys.service.SysUserTokenService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BreezeRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(BreezeRealm.class);
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysUserTokenService sysUserTokenService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof BreezeToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //权限认证
        SysUser sysUser = (SysUser)principals.getPrimaryPrincipal();
        Long userId = sysUser.getUserId();
        //用户权限列表
        Set<String> permsSet = sysUserService.getUserPermissions(userId);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * 登陆验证后，进行权限验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        //用户验证
//        SysUser sysUser = sysUserService.queryByUsername(auth.get)
        SysUserToken sysUserToken = sysUserTokenService.queryByToken((String) auth.getPrincipal());
        //token失效
        if(sysUserToken == null || sysUserToken.getExpireTime().getTime() < System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        //查询用户信息
        SysUser sysUser = sysUserService.getById(sysUserToken.getUserId());
        //判断账号状态
        if(sysUser.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        return new SimpleAuthenticationInfo(sysUser, sysUserToken.getToken(), getName());
    }
}
