package com.reizx.breeze.sys;

import com.reizx.breeze.modules.sys.entity.po.SysMenuPo;
import com.reizx.breeze.modules.sys.service.SysMenuService;
import com.reizx.breeze.utils.PageUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMenuTest {
    @Autowired
    SysMenuService sysMenuService;
    @Test
    public void contextLoads() {
        List<SysMenuPo> menus = sysMenuService.getUserMenuList(1L);
        System.out.println(menus);
    }
}
