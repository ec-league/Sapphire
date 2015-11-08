package com.sapphire.controller.page.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator <br/>
 * Date: 2015/11/8.<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/")
public class PageControllerImpl {

   @RequestMapping("index")
   public String index() {
      return "index";
   }
}
