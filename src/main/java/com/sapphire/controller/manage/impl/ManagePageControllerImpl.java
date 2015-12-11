package com.sapphire.controller.manage.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Author: EthanPark <br/>
 * Date: 2015/11/25<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/manage")
public class ManagePageControllerImpl {
   @RequestMapping("/project-page.html")
   public String projectPage() {
      return null;
   }

   @RequestMapping("/dashboard.html")
   public String dashboardPage() {
      return "dashboard";
   }

   @RequestMapping("/project-list.html")
   public String projectListPage() {
      return null;
   }

   @RequestMapping("/ticket-page.html")
   public String ticketPage() {
      return null;
   }
}
