package com.reizx.breeze.modules.sys.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reizx.breeze.modules.sys.dao.SysUserTokenDao;
import com.reizx.breeze.modules.sys.entity.po.SysUserTokenPo;
import com.reizx.breeze.modules.sys.service.SysUserTokenService;
import org.springframework.stereotype.Service;

@Service
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserTokenPo> implements SysUserTokenService {
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Override
    public SysUserTokenPo queryByToken(String token) {
        return baseMapper.selectOne(new QueryWrapper<SysUserTokenPo>().lambda().eq(SysUserTokenPo::getToken, token));
    }

    @Override
    public SysUserTokenPo setToken(long userId) {
        String token = IdUtil.simpleUUID();
        DateTime now = DateUtil.date();
        DateTime expireTime = DateUtil.offsetSecond(now, EXPIRE);
        //判断是否生成过token
        SysUserTokenPo sysUserTokenPo = this.getById(userId);
        if (sysUserTokenPo == null) {
            //不存在
            sysUserTokenPo = new SysUserTokenPo();
            sysUserTokenPo.setUserId(userId);
            sysUserTokenPo.setToken(token);
            sysUserTokenPo.setUpdateTime(now);
            sysUserTokenPo.setExpireTime(expireTime);
            //保存Token到数据库
            this.save(sysUserTokenPo);
        } else {
            //存在
            sysUserTokenPo.setToken(token);
            sysUserTokenPo.setUpdateTime(now);
            sysUserTokenPo.setExpireTime(expireTime);
            //更新token
            this.updateById(sysUserTokenPo);
        }
        return sysUserTokenPo;
    }

    @Override
    public void refreshToken(long userId) {
        String token = IdUtil.simpleUUID();
        DateTime now = DateUtil.date();
        DateTime expire = DateUtil.offsetSecond(now, EXPIRE);

        SysUserTokenPo sysUserTokenPo = new SysUserTokenPo();
        sysUserTokenPo.setUserId(userId);
        sysUserTokenPo.setToken(token);
        sysUserTokenPo.setUpdateTime(now);
        sysUserTokenPo.setExpireTime(expire);
        //更新
        updateById(sysUserTokenPo);
    }
}
