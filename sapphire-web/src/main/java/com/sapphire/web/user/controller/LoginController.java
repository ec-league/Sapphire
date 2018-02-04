package com.sapphire.web.user.controller;

import com.sapphire.biz.user.dto.UserDto;
import com.sapphire.biz.user.service.UserService;
import com.sapphire.common.dal.user.domain.User;
import com.sapphire.web.user.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: EthanPark <br/>
 * Date: ${Date}<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/account")
    @ResponseBody
    public LoginDto account(@RequestBody UserDto userDto) {

        User user = userService.getUserByUserNameOrEmail(userDto.getUsername());

        if (user == null) {
            return errorDto();
        }

        if (!user.getPassword().equals(userDto.getPassword())) {
            return errorDto();
        }

        LoginDto loginDto = new LoginDto();
        loginDto.setStatus("ok");
        loginDto.setCurrentAuthorith(user.getRole().getRoleName());

        return loginDto;
    }

    private LoginDto errorDto() {
        LoginDto loginDto = new LoginDto();
        loginDto.setStatus("error");
        loginDto.setCurrentAuthorith("guest");

        return loginDto;
    }
}
