package com.reizx.breeze.modules.sys.controller;

import com.reizx.breeze.modules.sys.entity.po.SysUserPo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class SysAbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUserPo getUser() {
		return (SysUserPo) SecurityUtils.getSubject().getPrincipal();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
}
