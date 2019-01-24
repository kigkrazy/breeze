package com.reizx.breeze.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reizx.breeze.modules.sys.dao.SysUserDao;
import com.reizx.breeze.modules.sys.entity.po.SysUser;
import com.reizx.breeze.modules.sys.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
    @Override
    public SysUser queryByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, username));
    }

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList = new ArrayList<>();

        return null;
    }

    /**
     * 获取所有权限
     *
     * @return
     */
    private List<String> getAllPerms() {
        return null;
    }
}
