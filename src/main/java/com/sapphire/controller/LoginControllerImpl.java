package com.sapphire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: EthanPark <br/>
 * Date: ${Date}<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
public class LoginControllerImpl {
   @RequestMapping("index")
   public String index(HttpServletRequest request) {
      return "index";
   }

   @RequestMapping("login")
   public String login(Model model) {
      model.addAttribute("title", "login");
      return "login";
   }
}
