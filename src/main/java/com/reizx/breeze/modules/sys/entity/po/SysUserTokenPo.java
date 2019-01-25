package com.reizx.breeze.modules.sys.entity.po;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("sys_user_token")
public class SysUserTokenPo {
    @TableId(type = IdType.INPUT)
    private Long userId = 0L;//用户ID
    private String token = "";//token
    private Date expireTime = DateUtil.date();//过期时间
    private Date updateTime = DateUtil.date();//更新时间


}
