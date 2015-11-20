package com.sapphire.service.blog.impl;

import com.sapphire.domain.blog.Blog;
import com.sapphire.domain.blog.Comment;
import com.sapphire.repository.blog.BlogRepository;
import com.sapphire.repository.blog.CommentRepository;
import com.sapphire.service.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/20<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("blogServiceImpl")
public class BlogServiceImpl implements BlogService {
   @Autowired
   private BlogRepository blogRepository;
   @Autowired
   private CommentRepository commentRepository;


   public List<Blog> getBlogListByUserId(long userId) {
      List<Blog> blogs = blogRepository.getAllBlogsByUserId(userId);
      if (blogs == null || blogs.size() == 0) {
         return Collections.emptyList();
      }
      return blogs;
   }

   public Blog getBlogByUidPk(long uidPk) {
      Blog blog = blogRepository.findOne(uidPk);
      if (blog == null) {
         throw new EntityNotFoundException(String.format(
               "Blog not found : \"%d\"", uidPk));
      }
      return blogRepository.findOne(uidPk);
   }

   public long saveBlog(Blog blog) {
      return blogRepository.save(blog).getUidPk();
   }

   public List<Comment> getCommentsByBlogId(long blogId) {
      List<Comment> comments = commentRepository.getAllCommentsByBlogId(blogId);
      if (comments == null || comments.size() == 0) {
         return Collections.emptyList();
      }
      return commentRepository.getAllCommentsByBlogId(blogId);
   }
}