package com.reizx.breeze.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reizx.breeze.constant.Constant;
import com.reizx.breeze.modules.sys.constant.MenuType;
import com.reizx.breeze.modules.sys.dao.SysMenuDao;
import com.reizx.breeze.modules.sys.entity.po.SysMenuPo;
import com.reizx.breeze.modules.sys.service.SysMenuService;
import com.reizx.breeze.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuPo> implements SysMenuService {
    @Autowired
    SysUserService sysUserService;

    @Override
    public List<SysMenuPo> getUserMenuList(Long userId) {
        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN) {
            return getAllMenuList(null);
        }

        //用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    /**
     * 获取所有菜单列表
     */
    private List<SysMenuPo> getAllMenuList(List<Long> menuIdList) {
        //查询根菜单列表
        List<SysMenuPo> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);
        return menuList;
    }

    @Override
    public List<SysMenuPo> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenuPo> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<SysMenuPo> userMenuList = new ArrayList<>();
        for (SysMenuPo menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenuPo> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    /**
     * 递归
     */
    private List<SysMenuPo> getMenuTreeList(List<SysMenuPo> menuList, List<Long> menuIdList) {
        List<SysMenuPo> subMenuList = new ArrayList<SysMenuPo>();

        for (SysMenuPo entity : menuList) {
            //目录
            if (entity.getType() == MenuType.CATALOG.getValue()) {
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }
        return subMenuList;
    }
}
