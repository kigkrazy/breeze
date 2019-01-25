package com.reizx.breeze.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reizx.breeze.modules.sys.entity.po.SysUserPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDao extends BaseMapper<SysUserPo> {
    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPermissions(Long userId);

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId
     * @return
     */
    List<Long> queryAllMenuId(Long userId);
}
