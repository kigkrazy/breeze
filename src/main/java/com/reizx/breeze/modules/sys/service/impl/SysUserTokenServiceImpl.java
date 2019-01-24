package com.reizx.breeze.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reizx.breeze.modules.sys.dao.SysUserTokenDao;
import com.reizx.breeze.modules.sys.entity.po.SysUserToken;
import com.reizx.breeze.modules.sys.service.SysUserTokenService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserToken> implements SysUserTokenService {
    @Override
    public SysUserToken queryByToken(String token) {
        return baseMapper.selectOne(new QueryWrapper<SysUserToken>().lambda().eq(SysUserToken::getToken, token));
    }


}
