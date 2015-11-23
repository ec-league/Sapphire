package com.sapphire.controller.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/23<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/blog")
public class BlogPageControllerImpl {

   @RequestMapping("/page.html")
   public String blogPage() {
      return null;
   }

   @RequestMapping("/my-blog.html")
   public String myBlogPage() {
      return null;
   }

   @RequestMapping("/my-comment.html")
   public String myCommentPage() {
      return null;
   }
}
