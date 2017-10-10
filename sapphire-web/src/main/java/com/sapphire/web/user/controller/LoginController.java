package com.sapphire.web.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: EthanPark <br/>
 * Date: ${Date}<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
public class LoginController {
    @RequestMapping("index.html")
    public String index() {
        return "index";
    }

    @RequestMapping("login.html")
    public String login() {
        return "login";
    }

    @RequestMapping("sign-up.html")
    public String signUp() {
        return "sign-up";
    }
}
