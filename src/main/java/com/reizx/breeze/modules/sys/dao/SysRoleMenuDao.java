package com.reizx.breeze.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reizx.breeze.modules.sys.entity.po.SysRoleMenuPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenuPo> {
    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);
}
