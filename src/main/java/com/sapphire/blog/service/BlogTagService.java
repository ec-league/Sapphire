package com.sapphire.blog.service;

import com.sapphire.blog.domain.Blog;
import com.sapphire.blog.domain.BlogTag;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2016/1/14<br/>
 * Email: byp5303628@hotmail.com
 */
public interface BlogTagService {
   List<Blog> getBlogsByTagName(String tagName);

   List<BlogTag> getBlogTagsByBlogId(long blogId);

   long addBlogTag(BlogTag tag);

   void deleteBlogTag(long blogTagId);
}
