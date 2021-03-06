package com.reizx.breeze.modules.sys.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色与菜单对应关系
 */
@Data
@TableName("sys_role_menu")
public class SysRoleMenuPo {
    @TableId
    private Long id;
    private Long roleId;//角色ID
    private Long menuId;//菜单ID
}
