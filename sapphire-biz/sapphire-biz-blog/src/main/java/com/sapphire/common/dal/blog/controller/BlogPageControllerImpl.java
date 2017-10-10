package com.sapphire.common.dal.blog.controller;

import com.sapphire.common.dal.blog.service.BlogService;
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
        return "view/blog/blog.html";
    }

    @RequestMapping("/my-blogs.html")
    public String myBlogPage() {
        return "view/blog/blog-list.html";
    }

    @RequestMapping("/blog-edit.html")
    public String blogEdit() {
        return "view/blog/blog-edit.html";
    }

    @RequestMapping("/my-comment.html")
    public String myCommentPage() {
        return null;
    }
}
