package com.sapphire.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.domain.User;
import com.sapphire.service.UserService;
import com.sapphire.util.JsonDto;

/**
 * Created by Administrator <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/user")
public class UserControllerImpl {
   private UserService userService;

   @Inject
   public UserControllerImpl(UserService userService) {
      this.userService = userService;
   }

   @RequestMapping("/create.ep")
   public @ResponseBody JsonDto createUser(@RequestBody User user) {
      try {
         userService.saveOrMerge(user);
         return new JsonDto().formSuccessDto();
      } catch (Exception ex) {
         return new JsonDto().formFailureDto();
      }
   }

   @RequestMapping("/{id}/get.ep")
   public @ResponseBody JsonDto getUser(@PathVariable("id") long id) {
      if (id == 1) {
         return new JsonDto().formSuccessDto();
      }
      return new JsonDto().formFailureDto();
   }
}
