package com.reizx.breeze.modules.sys.controller;

import com.reizx.breeze.constant.Constant;
import com.reizx.breeze.modules.sys.entity.po.SysRolePo;
import com.reizx.breeze.modules.sys.service.SysRoleMenuService;
import com.reizx.breeze.modules.sys.service.SysRoleService;
import com.reizx.breeze.utils.PageUtils;
import com.reizx.breeze.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends SysAbstractController {
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    SysRoleMenuService sysRoleMenuService;

    /**
     * 角色列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:role:list")
    public R list(@RequestParam(value = "current") long current, @RequestParam(value = "limit") long limit, @RequestParam(value = "query") String query) {
        Long createUserid = 0L;
        //如果不是超级管理员，则只查询自己创建的角色列表
        if (getUserId() != Constant.SUPER_ADMIN) {
            createUserid = getUserId();
        }

        PageUtils.PageWrapper page = sysRoleService.queryPage(current, limit, query, createUserid);
        return R.ok().put("page", page);
    }

    /**
     * 角色信息
     */
    @GetMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public R info(@PathVariable("roleId") Long roleId){
        SysRolePo role = sysRoleService.getById(roleId);
        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);
        return R.ok().put("role", role);
    }
}
