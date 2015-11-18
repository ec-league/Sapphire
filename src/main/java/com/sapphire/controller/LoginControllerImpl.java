package com.sapphire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: EthanPark <br/>
 * Date: ${Date}<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
public class LoginControllerImpl {
   @RequestMapping("index")
   public String index() {
      return "index";
   }

   @RequestMapping("login")
   public String login() {
      return "login";
   }
}
