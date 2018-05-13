package com.sapphire.web.user.controller;

import com.sapphire.common.dal.user.domain.User;
import com.sapphire.biz.user.dto.UserDto;
import com.sapphire.biz.user.dto.UserJsonDto;
import com.sapphire.biz.user.service.UserService;
import com.sapphire.common.utils.dto.DataJsonDto;
import com.sapphire.common.utils.dto.Dto;
import com.sapphire.common.utils.dto.JsonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/rest/user")
public class UserJsonControllerImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserJsonControllerImpl.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/create.ep")
    @ResponseBody
    public JsonDto createUser(@RequestBody UserDto user) {
        try {
            userService.createUser(user);
            return new JsonDto().formSuccessDto();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            return new JsonDto().formFailureDto(ex);
        }
    }

    @RequestMapping("/{id}/get.ep")
    @ResponseBody
    public JsonDto getUser(@PathVariable("id") long id) {
        try {
            User user = userService.getUserById(id);
            return new UserJsonDto(user).formSuccessDto();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            return new JsonDto().formFailureDto(ex);
        }
    }

    @RequestMapping("/getByVal.ep")
    @ResponseBody
    public JsonDto getUserByUsernameOrPassword(@RequestParam("val") String val) {
        try {
            User u = userService.getUserByUserNameOrEmail(val);
            return new UserJsonDto(u).formSuccessDto();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new JsonDto().formFailureDto(e);
        }
    }
}
