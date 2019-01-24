package com.reizx.breeze.modules.sys.entity.po;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@TableName("sys_role")
public class SysRole {
    @TableId
    private Long roleId = 0L;//角色ID
    @NotBlank(message="角色名称不能为空")
    private String roleName ="";//角色名称
    private String remark = "";//备注
    private Long createUserId  = 0L;//创建者ID
    private Date createTime = DateUtil.date();//创建时间
    @TableField(exist=false)
    private List<Long> menuIdList = new ArrayList<>();
}
