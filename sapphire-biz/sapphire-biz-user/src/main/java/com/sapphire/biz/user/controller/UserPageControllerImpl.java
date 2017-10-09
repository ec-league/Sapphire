package com.sapphire.biz.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/7<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/user")
public class UserPageControllerImpl {
    @RequestMapping("/user-info.html")
    public String userInfoPage() {
        return "user-info";
    }
}
