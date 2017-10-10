package com.sapphire.biz.blog.service;

import java.util.List;

import com.sapphire.common.dal.blog.domain.Comment;
import com.sapphire.common.dal.blog.domain.Blog;

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
