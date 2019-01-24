package com.reizx.breeze.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reizx.breeze.modules.sys.entity.po.SysUser;

import java.util.Set;

public interface SysUserService extends IService<SysUser> {
    /**
     * 查询用户
     *
     * @param username 用户名
     * @return
     */
    SysUser queryByUsername(String username);


    /**
     * 获取用户权限列表
     *
     * @param userId 用户id
     * @return
     */
    Set<String> getUserPermissions(long userId);
}
