package com.sapphire.biz.blog.service;

import java.util.List;

import com.sapphire.common.dal.blog.domain.BlogTag;
import com.sapphire.common.dal.blog.domain.Blog;

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
