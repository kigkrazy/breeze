package com.reizx.breeze.modules.sys.controller;

import com.reizx.breeze.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/sys")
public class SysLogin {
    @GetMapping(value = "/login")
    public R login() {
        return R.ok();
    }
}
