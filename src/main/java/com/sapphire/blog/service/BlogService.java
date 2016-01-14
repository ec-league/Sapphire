package com.sapphire.blog.service;

import com.sapphire.blog.domain.Blog;
import com.sapphire.blog.domain.Comment;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/20<br/>
 * Email: byp5303628@hotmail.com
 */
public interface BlogService {
   List<Blog> getBlogListByUserId(long userId);

   Blog getBlogByUidPk(long uidPk);

   long saveBlog(Blog blog);

   void loadBlog(long blogId);

   List<Comment> getCommentsByBlogId(long blogId);

   int getUserHitById(long userId);
}