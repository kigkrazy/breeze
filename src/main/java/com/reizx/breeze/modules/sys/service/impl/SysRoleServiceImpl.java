package com.reizx.breeze.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reizx.breeze.modules.sys.dao.SysRoleDao;
import com.reizx.breeze.modules.sys.entity.po.SysRole;
import com.reizx.breeze.modules.sys.service.SysRoleService;
import com.reizx.breeze.utils.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {
    @Override
    public PageUtils.PageWrapper queryPage(long current, long limit, String query) {
        IPage<SysRole> page = (Page<SysRole>) page(
                PageUtils.page(current, limit),
                new QueryWrapper<SysRole>()
                .lambda()
                .like(StringUtils.isBlank(query), SysRole::getRoleName, query)
        );
        return new PageUtils.PageWrapper(page);
    }
}
