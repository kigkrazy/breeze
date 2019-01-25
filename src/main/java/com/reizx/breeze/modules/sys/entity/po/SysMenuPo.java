package com.reizx.breeze.modules.sys.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("sys_menu")
public class SysMenuPo {
    @TableId
    private Long menuId;//菜单ID
    private Long parentId;//父菜单ID，一级菜单为0
    private String name;//菜单名称
    private String url;//菜单URL
    private String perms;//授权(多个用逗号分隔，如：user:list,user:create)
    private Integer type;//类型 0：目录 1：菜单 2：按钮
    private String icon;//菜单图标
    private Integer orderNum;//排序

    @TableField(exist=false)
    private String parentName;//父菜单名称
    @TableField(exist=false)
    private Boolean open;//ztree属性
    @TableField(exist=false)
    private List<?> list;
}
