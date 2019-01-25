package com.reizx.breeze.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reizx.breeze.modules.sys.entity.po.SysMenuPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuPo> {
    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenuPo> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuPo> queryNotButtonList();
}
