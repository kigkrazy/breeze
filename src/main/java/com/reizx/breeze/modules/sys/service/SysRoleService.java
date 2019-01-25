package com.reizx.breeze.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.reizx.breeze.modules.sys.entity.po.SysRolePo;
import com.reizx.breeze.utils.PageUtils;

/**
 * 角色服务
 */
public interface SysRoleService extends IService<SysRolePo> {
    /**
     * 查询界面
     *
     * @param current      当前页
     * @param limit        每页数量
     * @param query        查询关键词
     * @param createUserId 创建者，如果为NULL或者0则查询所有角色
     * @return
     */
    PageUtils.PageWrapper queryPage(long current, long limit, String query, Long createUserId);
}
