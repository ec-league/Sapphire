package com.sapphire.controller.page.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/8.<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("")
public class PageControllerImpl {

   @RequestMapping("")
   public String index() {
      return "index";
   }

   @RequestMapping("/login.html")
   public String login() {
      return "login";
   }

   @RequestMapping("/sign-up.html")
   public String signUp() {
      return "sign-up";
   }
}
