package com.reizx.breeze.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reizx.breeze.modules.sys.entity.po.SysUserPo;

import java.util.List;
import java.util.Set;

public interface SysUserService extends IService<SysUserPo> {
    /**
     * 查询用户
     *
     * @param username 用户名
     * @return
     */
    SysUserPo queryByUsername(String username);


    /**
     * 获取用户权限列表
     *
     * @param userId 用户id
     * @return
     */
    Set<String> getUserPermissions(long userId);

    /**
     * 查询用户的所有菜单ID
     * @param userId 用户ID
     * @return
     */
    List<Long> queryAllMenuId(Long userId);

}
