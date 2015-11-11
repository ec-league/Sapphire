package com.sapphire.controller;

import javax.inject.Inject;

import com.sapphire.dto.user.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.domain.User;
import com.sapphire.dto.JsonDto;
import com.sapphire.dto.user.UserJsonDto;
import com.sapphire.service.UserService;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/user")
public class UserControllerImpl {
   private static Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);
   @Autowired
   private UserService userService;

   @RequestMapping("/create.ep")
   public @ResponseBody JsonDto createUser(@RequestBody UserDto user) {
      try {
         userService.saveOrMerge(user);
         return new JsonDto().formSuccessDto();
      } catch (Exception ex) {
         logger.error(ex.getMessage());
         ex.printStackTrace();
         return new JsonDto().formFailureDto();
      }
   }

   @RequestMapping("/{id}/get.ep")
   public @ResponseBody JsonDto getUser(@PathVariable("id") long id) {
      try {
         User user = userService.getUserById(id);
         return new UserJsonDto(user).formSuccessDto();
      } catch (Exception ex) {
         logger.error(ex.getMessage());
         ex.printStackTrace();
         return new JsonDto().formFailureDto();
      }
   }
}
