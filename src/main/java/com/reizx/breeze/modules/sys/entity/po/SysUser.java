package com.reizx.breeze.modules.sys.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.reizx.breeze.common.validator.group.AddGroup;
import com.reizx.breeze.common.validator.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * 用户信息
 */
@Data
@TableName("sys_user")
public class SysUser {
    @TableId
    private Long userId;//用户ID
    @NotBlank(message="用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;//用户名
    @NotBlank(message="密码不能为空", groups = AddGroup.class)
    private String password;//密码
    private String salt;//盐，密码加密用的盐，避免密码泄露
    @NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    private String email;//邮箱
    private String mobile;//手机号
    private Integer status;//状态  0：禁用   1：正常
    private Long createUserId;//创建者ID
    private Date createTime;//创建时间
    @TableField(exist=false)
    private List<Long> roleIdList;//角色ID列表


}
