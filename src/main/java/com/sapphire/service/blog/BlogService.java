package com.sapphire.service.blog;

import com.sapphire.domain.blog.Blog;
import com.sapphire.domain.blog.Comment;

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

   List<Comment> getCommentsByBlogId(long blogId);
}
