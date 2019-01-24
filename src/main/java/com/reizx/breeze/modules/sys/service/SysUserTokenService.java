package com.reizx.breeze.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reizx.breeze.modules.sys.entity.po.SysUserToken;

public interface SysUserTokenService extends IService<SysUserToken> {
    /**
     * 查询用户
     */
    SysUserToken queryByToken(String token);
}
