package com.demo.demo002.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: zhouxiaofeng
 * @Date: 2022/10/27 20:29
 * @Description:
 */
@Controller
public class IndexController {

    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }

    @RequestMapping("/qrCodeSubmit")
    public String qrCodeSubmit() {
        return "qrCodeSubmit.html";
    }
}
