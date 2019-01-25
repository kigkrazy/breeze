package com.reizx.breeze.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reizx.breeze.modules.sys.entity.po.SysUserToken;

public interface SysUserTokenService extends IService<SysUserToken> {
    /**
     * 查询用户
     *
     * @param token
     * @return
     */
    SysUserToken queryByToken(String token);


    /**
     * 设置token
     * 如果不存在则生成并保存，存在则更新。
     *
     * @param userId
     * @return
     */
    SysUserToken setToken(long userId);


    /**
     * 刷新token，logout时候使用
     * 如果不存在则生成并保存，存在则更新。
     *
     * @param userId
     * @return
     */
    void refreshToken(long userId);
}
