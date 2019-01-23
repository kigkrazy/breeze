package com.reizx.breeze.modules.sys.controller;

import com.reizx.breeze.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/sys")
public class SysLogin {
    private final static Logger logger = LoggerFactory.getLogger(SysLogin.class);

    @GetMapping(value = "/login")
    public R login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        logger.debug("the username : %s, password : %s", username, password);
        return R.ok();
    }
}
