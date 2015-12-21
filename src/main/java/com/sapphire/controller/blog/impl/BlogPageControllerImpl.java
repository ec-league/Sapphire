package com.sapphire.controller.blog.impl;

import com.sapphire.service.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/23<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/blog")
public class BlogPageControllerImpl {
   @Autowired
   private BlogService blogService;

   @RequestMapping("/page.html")
   public String blogPage(@RequestParam("blogId") long blogId) {
      blogService.loadBlog(blogId);
      return "blog/blog";
   }

   @RequestMapping("/my-blogs.html")
   public String myBlogPage() {
      return "blog/blog-list";
   }

   @RequestMapping("/blog-edit.html")
   public String blogEdit() {
      return "blog/blog-edit";
   }

   @RequestMapping("/my-comment.html")
   public String myCommentPage() {
      return null;
   }
}
