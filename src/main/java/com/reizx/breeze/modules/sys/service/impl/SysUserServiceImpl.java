package com.reizx.breeze.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reizx.breeze.constant.Constant;
import com.reizx.breeze.modules.sys.dao.SysUserDao;
import com.reizx.breeze.modules.sys.dao.SysUserTokenDao;
import com.reizx.breeze.modules.sys.entity.po.SysMenuPo;
import com.reizx.breeze.modules.sys.entity.po.SysUserPo;
import com.reizx.breeze.modules.sys.service.SysMenuService;
import com.reizx.breeze.modules.sys.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserPo> implements SysUserService {
    @Autowired
    SysUserTokenDao sysUserTokenDao;
    @Autowired
    SysMenuService sysMenuService;//菜单情况

    @Override
    public SysUserPo queryByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<SysUserPo>().lambda().eq(SysUserPo::getUsername, username));
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

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    /**
     * 获取所有权限
     *
     * @return
     */
    private List<String> getSuperAdminPerms() {
        List<SysMenuPo> menuList = sysMenuService.list(null);
        List<String> permissions = new ArrayList<>();
        for(SysMenuPo menu : menuList){
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
