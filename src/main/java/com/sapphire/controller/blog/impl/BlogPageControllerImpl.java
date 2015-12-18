package com.sapphire.controller.blog.impl;

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
      return "blog/blog";
   }

   @RequestMapping("/my-blogs.html")
   public String myBlogPage() {
      return "blog/blog-list";
   }

   @RequestMapping("/blog-edit.html")
   public String blogEdit(){
      return "blog/blog-edit";
   }

   @RequestMapping("/my-comment.html")
   public String myCommentPage() {
      return null;
   }
}
