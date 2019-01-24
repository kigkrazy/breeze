package com.reizx.breeze.modules.sys.controller;

import com.reizx.breeze.modules.sys.entity.po.SysUser;
import com.reizx.breeze.modules.sys.entity.po.SysUserToken;
import com.reizx.breeze.modules.sys.service.SysUserService;
import com.reizx.breeze.modules.sys.service.SysUserTokenService;
import com.reizx.breeze.utils.R;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/sys")
public class SysLogin {
    private final static Logger logger = LoggerFactory.getLogger(SysLogin.class);
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

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
        SysUserToken sysUserToken = sysUserTokenService.setByToken(sysUser.getUserId());
        return R.ok().put("token", sysUserToken.getToken()).put("expire", EXPIRE);
    }
}
