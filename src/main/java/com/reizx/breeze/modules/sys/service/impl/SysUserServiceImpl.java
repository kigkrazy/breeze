package com.reizx.breeze.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reizx.breeze.constant.Constant;
import com.reizx.breeze.modules.sys.dao.SysUserDao;
import com.reizx.breeze.modules.sys.dao.SysUserTokenDao;
import com.reizx.breeze.modules.sys.entity.po.SysMenu;
import com.reizx.breeze.modules.sys.entity.po.SysUser;
import com.reizx.breeze.modules.sys.service.SysMenuService;
import com.reizx.breeze.modules.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
    @Autowired
    SysUserTokenDao sysUserTokenDao;
    @Autowired
    SysMenuService sysMenuService;//菜单情况

    @Override
    public SysUser queryByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, username));
    }

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permissions = new ArrayList<>();
        if (userId == Constant.SUPER_ADMIN) {
            permissions = getSuperAdminPerms();
        } else {
            permissions = baseMapper.queryAllPermissions(userId);
        }
        //解析权限
        return parsePermissions(permissions);
    }

    /**
     * 获取所有权限
     *
     * @return
     */
    private List<String> getSuperAdminPerms() {
        List<SysMenu> menuList = sysMenuService.list(null);
        List<String> permissions = new ArrayList<>();
        for(SysMenu menu : menuList){
            permissions.add(menu.getPerms());
        }
        return permissions;
    }

    private Set<String> parsePermissions(List<String> permissions) {
        Set<String> permsSet = new HashSet<>();
        for(String permission : permissions){
            if(StringUtils.isBlank(permission)){
                continue;
            }
            permsSet.addAll(Arrays.asList(permission.trim().split(",")));
        }
        return permsSet;
    }
}
