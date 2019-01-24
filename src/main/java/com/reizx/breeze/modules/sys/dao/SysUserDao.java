package com.reizx.breeze.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.reizx.breeze.modules.sys.entity.po.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {
}
