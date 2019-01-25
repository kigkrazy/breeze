package com.reizx.breeze.sys;

import com.reizx.breeze.modules.sys.service.SysRoleService;
import com.reizx.breeze.utils.PageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleTest {
    @Autowired
    SysRoleService sysRoleService;
    @Test
    public void contextLoads() {
        PageUtils.PageWrapper page = sysRoleService.queryPage(1, 10, "", 0L);
    }
}
