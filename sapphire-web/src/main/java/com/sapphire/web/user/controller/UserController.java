/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.sapphire.web.user.controller;

import com.sapphire.web.user.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author 三皇
 * @version $Id: UesrController.java, v 0.1 2018年05月13日 下午11:47 yunpeng.byp Exp $
 */
@Controller
public class UserController {
    @RequestMapping("/currentUser.ep")
    @ResponseBody
    public UserDto queryCurrent() {
        UserDto userDto = new UserDto();
        userDto.setName("三皇");
        userDto.setUid("0001");
        return userDto;
    }
}