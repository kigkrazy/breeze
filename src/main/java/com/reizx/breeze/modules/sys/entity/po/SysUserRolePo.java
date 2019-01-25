package com.reizx.breeze.modules.sys.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sys_user_role")
public class SysUserRolePo {
    @TableId
    private Long id;
    private Long userId;//用户ID
    private Long roleId;//角色ID
}
