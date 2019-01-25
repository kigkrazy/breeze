package com.reizx.breeze.modules.sys.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reizx.breeze.modules.sys.dao.SysUserTokenDao;
import com.reizx.breeze.modules.sys.entity.po.SysUserToken;
import com.reizx.breeze.modules.sys.service.SysUserTokenService;
import org.springframework.stereotype.Service;

@Service
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserToken> implements SysUserTokenService {
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Override
    public SysUserToken queryByToken(String token) {
        return baseMapper.selectOne(new QueryWrapper<SysUserToken>().lambda().eq(SysUserToken::getToken, token));
    }

    @Override
    public SysUserToken setToken(long userId) {
        String token = IdUtil.simpleUUID();
        DateTime now = DateUtil.date();
        DateTime expireTime = DateUtil.offsetSecond(now, EXPIRE);
        //判断是否生成过token
        SysUserToken sysUserToken = this.getById(userId);
        if (sysUserToken == null) {
            //不存在
            sysUserToken = new SysUserToken();
            sysUserToken.setUserId(userId);
            sysUserToken.setToken(token);
            sysUserToken.setUpdateTime(now);
            sysUserToken.setExpireTime(expireTime);
            //保存Token到数据库
            this.save(sysUserToken);
        } else {
            //存在
            sysUserToken.setToken(token);
            sysUserToken.setUpdateTime(now);
            sysUserToken.setExpireTime(expireTime);
            //更新token
            this.updateById(sysUserToken);
        }
        return sysUserToken;
    }

    @Override
    public void refreshToken(long userId) {
        String token = IdUtil.simpleUUID();
        DateTime now = DateUtil.date();
        DateTime expire = DateUtil.offsetSecond(now, EXPIRE);

        SysUserToken sysUserToken = new SysUserToken();
        sysUserToken.setUserId(userId);
        sysUserToken.setToken(token);
        sysUserToken.setUpdateTime(now);
        sysUserToken.setExpireTime(expire);
        //更新
        updateById(sysUserToken);
    }
}
