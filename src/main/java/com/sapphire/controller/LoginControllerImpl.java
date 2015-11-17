package com.sapphire.controller;

import com.sapphire.domain.User;
import com.sapphire.dto.user.AuthDto;
import com.sapphire.dto.user.UserDto;
import com.sapphire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: EthanPark <br/>
 * Date: ${Date}<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/auth")
public class LoginControllerImpl {
   @Autowired
   private UserService userService;

   @RequestMapping("/login")
   public String loginIn(@RequestBody AuthDto dto) {
      try {
         if (userService.authenticateUser(dto.getUsername(), dto.getPassword())) {
            return "login_success";
         } else {
            return "login_failed";
         }
      } catch (Exception e) {
         return "login_failed";
      }
   }
}
