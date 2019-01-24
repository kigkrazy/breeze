package com.reizx.breeze;

import com.reizx.breeze.modules.sys.dao.SysUserTokenDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BreezeApplicationTests {
    @Autowired
    SysUserTokenDao sysUserTokenDao;
    @Test
    public void contextLoads() {
        System.out.println("xxx");
    }
}

