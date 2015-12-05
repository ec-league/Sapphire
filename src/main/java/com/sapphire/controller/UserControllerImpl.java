package com.sapphire.controller;

import com.sapphire.domain.User;
import com.sapphire.dto.user.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.dto.JsonDto;
import com.sapphire.dto.user.UserJsonDto;
import com.sapphire.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/rest/user")
public class UserControllerImpl {
   private static Logger logger = LoggerFactory
         .getLogger(UserControllerImpl.class);
   @Autowired
   private UserService userService;

   @RequestMapping("/create.ep")
   public @ResponseBody JsonDto createUser(@RequestBody UserDto user) {
      try {
         userService.createUser(user);
         return new JsonDto().formSuccessDto();
      } catch (Exception ex) {
         logger.error(ex.getMessage());
         ex.printStackTrace();
         return new JsonDto().formFailureDto(ex);
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
         return new JsonDto().formFailureDto(ex);
      }
   }

   @RequestMapping("/getByVal.ep")
   public @ResponseBody JsonDto getUserByUsernameOrPassword(
         @RequestParam("val") String val) {
      try {
         User u = userService.getUserByUserNameOrEmail(val);
         return new UserJsonDto(u).formSuccessDto();
      } catch (Exception e) {
         logger.error(e.getMessage());
         e.printStackTrace();
         return new JsonDto().formFailureDto(e);
      }
   }

   @RequestMapping("/getCurrentUser.ep")
   public @ResponseBody JsonDto getCurrentUser() {
      try {
         User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         return new UserJsonDto(u).formSuccessDto();
      }catch (Exception e) {
         logger.error(e.getMessage());
         e.printStackTrace();
         return new JsonDto().formFailureDto(e);
      }
   }
}
