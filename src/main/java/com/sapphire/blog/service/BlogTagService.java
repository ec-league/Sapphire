package com.sapphire.blog.service;

import java.util.List;

import com.sapphire.blog.domain.Blog;
import com.sapphire.blog.domain.BlogTag;

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
