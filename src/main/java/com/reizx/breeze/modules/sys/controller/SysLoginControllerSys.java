package com.reizx.breeze.modules.sys.controller;

import com.reizx.breeze.modules.sys.entity.po.SysUser;
import com.reizx.breeze.modules.sys.entity.po.SysUserToken;
import com.reizx.breeze.modules.sys.service.SysUserService;
import com.reizx.breeze.modules.sys.service.SysUserTokenService;
import com.reizx.breeze.utils.R;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
public class SysLoginControllerSys extends SysAbstractController {
    private final static Logger logger = LoggerFactory.getLogger(SysLoginControllerSys.class);

    @Autowired
    SysUserService  sysUserService;//用户信息

    @Autowired
    SysUserTokenService sysUserTokenService;//用户Token信息

    @GetMapping(value = "/login")
    public R login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        //用户信息
        SysUser sysUser = sysUserService.queryByUsername(username);

        //账号不存在、密码错误
        if(sysUser == null || !sysUser.getPassword().equals(new Sha256Hash(password, sysUser.getSalt()).toHex())) {
            return R.error("账号或密码不正确");
        }

        //账号锁定
        if(sysUser.getStatus() == 0){
            return R.error("账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        SysUserToken sysUserToken = sysUserTokenService.setToken(sysUser.getUserId());
        return R.ok().put("token", sysUserToken.getToken()).put("expire", sysUserToken.getExpireTime());
    }

    @GetMapping(value = "/logout")
    public R logout() {
        sysUserTokenService.refreshToken(getUserId());
        return R.ok();
    }

    @GetMapping(value = "/hello")
    public R hello() {
        return R.ok();
    }
}
