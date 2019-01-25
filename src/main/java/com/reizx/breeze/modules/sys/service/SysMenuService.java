package com.reizx.breeze.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reizx.breeze.modules.sys.entity.po.SysMenuPo;

import java.util.List;

public interface SysMenuService extends IService<SysMenuPo> {
    /**
     * 获取用户菜单列表
     */
    List<SysMenuPo> getUserMenuList(Long userId);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     */
    List<SysMenuPo> queryListParentId(Long parentId, List<Long> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenuPo> queryListParentId(Long parentId);
}
