package com.reizx.breeze.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reizx.breeze.modules.sys.entity.po.SysUser;

public interface SysUserService extends IService<SysUser> {
    /**
     * 删除用户
     */
    void deleteBatch(Long[] userIds);
}
