package com.sapphire.controller.blog.impl;

import com.sapphire.common.TimeUtil;
import com.sapphire.constant.BlogStatus;
import com.sapphire.domain.blog.Blog;
import com.sapphire.domain.blog.Comment;
import com.sapphire.dto.DataJsonDto;
import com.sapphire.dto.Dto;
import com.sapphire.dto.JsonDto;
import com.sapphire.dto.ListJsonDto;
import com.sapphire.service.user.UserService;
import com.sapphire.service.blog.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/20<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/blog")
public class BlogControllerImpl {
   private static final Logger LOGGER = LoggerFactory
         .getLogger(BlogControllerImpl.class);
   @Autowired
   private BlogService blogService;
   @Autowired
   private UserService userService;

   /**
    * Get specified user's blog list, which contains title.
    * 
    * @param id
    * @return
    */
   @RequestMapping("/{id}/list.ep")
   @ResponseBody
   public JsonDto getBlogList(@PathVariable("id") long id) {
      try {
         List<Blog> blogs = blogService.getBlogListByUserId(id);
         List<BlogItem> blogItems = new ArrayList<BlogItem>();
         for (Blog blog : blogs) {
            blogItems.add(new BlogItem(blog));
         }
         return new ListJsonDto<BlogItem>(blogItems).formSuccessDto();
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         return new JsonDto().formFailureDto(e);
      }
   }

   /**
    * Get specified blog by uidPk, which contains title, content and comments.
    * 
    * @param id
    *           , specified blog uidPk.
    * @return
    */
   @RequestMapping("/{id}/get.ep")
   @ResponseBody
   public JsonDto getBlog(@PathVariable("id") long id) {
      try {
         Blog blog = blogService.getBlogByUidPk(id);
         BlogDetail blogDetail = new BlogDetail(blog);
         List<CommentDto> dtos = new ArrayList<CommentDto>();
         for (Comment comment : blogService.getCommentsByBlogId(id)) {
            dtos.add(new CommentDto(comment));
         }
         blogDetail.setComments(dtos);
         return new DataJsonDto<BlogDetail>(blogDetail).formSuccessDto();
      } catch (EntityNotFoundException e) {
         LOGGER.error(e.getMessage());
         return new JsonDto().formFailureDto(e);
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         return new JsonDto().formFailureDto(e);
      }
   }

   @RequestMapping("/{blogId}/save.ep")
   @ResponseBody
   public JsonDto saveBlog(@PathVariable("blogId") long blogId,
         @RequestBody BlogDto blogDto) {
      try {
         Blog blog = getBlog(blogId, blogDto);
         blogService.saveBlog(blog);
         return new JsonDto().formSuccessDto();
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         return new JsonDto().formFailureDto(e);
      }
   }

   @RequestMapping("/{blogId}/publish.ep")
   @ResponseBody
   public JsonDto publishBlog(@PathVariable("blogId") long blogId,
         @RequestBody BlogDto blogDto) {
      try {
         Blog blog = getBlog(blogId, blogDto);
         blog.setBlogStatus(BlogStatus.PUBLISHED);
         blogService.saveBlog(blog);
         return new JsonDto().formSuccessDto();
      } catch (Exception e) {
         LOGGER.error(e.getMessage(), e);
         return new JsonDto().formFailureDto(e);
      }
   }

   private Blog getBlog(long blogId, BlogDto blogDto) {
      Blog blog;
      if (blogId == 0) {
         blog = new Blog();
         blog.setCreateTime(TimeUtil.now());
         blog.setUser(userService.getUserById(blogDto.getUserId()));
      } else {
         blog = blogService.getBlogByUidPk(blogId);
         blog.setBlogContent(blogDto.getContent());
      }
      blog.setLastModifyTime(TimeUtil.now());
      blog.setBlogTitle(blogDto.getTitle());
      blog.setBlogContent(blogDto.getContent());
      return blog;
   }

   private static class BlogItem implements Dto {
      private long blogId;
      private String title;
      private String createTime;
      private String lastModifyTime;
      private String status;

      public BlogItem(Blog blog) {
         setBlogId(blog.getUidPk());
         setTitle(blog.getBlogTitle());
         setCreateTime(TimeUtil.formatTime(blog.getCreateTime()));
         setLastModifyTime(TimeUtil.formatTime(blog.getLastModifyTime()));
         setStatus(blog.getBlogStatus().toString());
      }

      public long getBlogId() {
         return blogId;
      }

      public void setBlogId(long blogId) {
         this.blogId = blogId;
      }

      public String getTitle() {
         return title;
      }

      public void setTitle(String title) {
         this.title = title;
      }

      public String getCreateTime() {
         return createTime;
      }

      public void setCreateTime(String createTime) {
         this.createTime = createTime;
      }

      public String getLastModifyTime() {
         return lastModifyTime;
      }

      public void setLastModifyTime(String lastModifyTime) {
         this.lastModifyTime = lastModifyTime;
      }

      public String getStatus() {
         return status;
      }

      public void setStatus(String status) {
         this.status = status;
      }
   }

   private static class BlogDetail implements Dto {
      private long blogId;
      private String title;
      private String content;
      private String lastModifyTime;
      private List<CommentDto> comments;

      public BlogDetail(Blog blog) {
         setBlogId(blog.getUidPk());
         setTitle(blog.getBlogTitle());
         setLastModifyTime(TimeUtil.formatTime(blog.getLastModifyTime()));
         setContent(blog.getBlogContent());
      }

      public String getLastModifyTime() {
         return lastModifyTime;
      }

      public void setLastModifyTime(String lastModifyTime) {
         this.lastModifyTime = lastModifyTime;
      }

      public List<CommentDto> getComments() {
         return comments;
      }

      public void setComments(List<CommentDto> comments) {
         this.comments = comments;
      }

      public long getBlogId() {
         return blogId;
      }

      public void setBlogId(long blogId) {
         this.blogId = blogId;
      }

      public String getTitle() {
         return title;
      }

      public void setTitle(String title) {
         this.title = title;
      }

      public String getContent() {
         return content;
      }

      public void setContent(String content) {
         this.content = content;
      }

   }

   private static class CommentDto implements Dto {
      private long commentId;
      private String commentContent;
      private String createTime;
      private String lastModifyTime;

      public CommentDto(Comment comment) {
         setCommentId(comment.getUidPk());
         setCommentContent(comment.getContent());
         setCreateTime(TimeUtil.formatTime(comment.getCreateTime()));
         setLastModifyTime(TimeUtil.formatTime(comment.getLastModifyTime()));
      }

      public long getCommentId() {
         return commentId;
      }

      public void setCommentId(long commentId) {
         this.commentId = commentId;
      }

      public String getCommentContent() {
         return commentContent;
      }

      public void setCommentContent(String commentContent) {
         this.commentContent = commentContent;
      }

      public String getCreateTime() {
         return createTime;
      }

      public void setCreateTime(String createTime) {
         this.createTime = createTime;
      }

      public String getLastModifyTime() {
         return lastModifyTime;
      }

      public void setLastModifyTime(String lastModifyTime) {
         this.lastModifyTime = lastModifyTime;
      }
   }

   private static class BlogDto implements Dto {
      private long userId;
      private String title;
      private String content;

      public long getUserId() {
         return userId;
      }

      public void setUserId(long userId) {
         this.userId = userId;
      }

      public String getTitle() {
         return title;
      }

      public void setTitle(String title) {
         this.title = title;
      }

      public String getContent() {
         return content;
      }

      public void setContent(String content) {
         this.content = content;
      }
   }
}
