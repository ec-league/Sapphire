package com.sapphire.web.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.biz.user.dto.UserDto;
import com.sapphire.biz.user.service.UserService;
import com.sapphire.common.dal.user.domain.User;
import com.sapphire.web.user.dto.LoginDto;

/**
 * Author: EthanPark <br/>
 * Date: ${Date}<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/account.ep", method = { RequestMethod.POST })
    @ResponseBody
    public LoginDto account(@RequestBody UserDto userDto) {
        if (!userDto.getUserName().equals("admin") || !userDto.getPassword().equals("888888")) {
            return errorDto();
        }

        LoginDto loginDto = new LoginDto();
        loginDto.setStatus("ok");
        loginDto.setCurrentAuthorith("admin");
        loginDto.setType(userDto.getType());
        return loginDto;
    }

    private LoginDto errorDto() {
        LoginDto loginDto = new LoginDto();
        loginDto.setStatus("error");
        loginDto.setCurrentAuthorith("guest");
        loginDto.setType("account");

        return loginDto;
    }
}
