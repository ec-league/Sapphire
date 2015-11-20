package com.sapphire.controller.blog;

import com.sapphire.common.TimeUtil;
import com.sapphire.constant.BlogStatusConstant;
import com.sapphire.domain.blog.Blog;
import com.sapphire.domain.blog.Comment;
import com.sapphire.dto.DataJsonDto;
import com.sapphire.dto.Dto;
import com.sapphire.dto.JsonDto;
import com.sapphire.dto.ListJsonDto;
import com.sapphire.service.UserService;
import com.sapphire.service.blog.BlogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
   private static Logger logger = LoggerFactory
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
   public @ResponseBody JsonDto getBlogList(@PathVariable("id") long id) {
      try {
         List<Blog> blogs = blogService.getBlogListByUserId(id);
         List<BlogItem> blogItems = new ArrayList<BlogItem>();
         for (Blog blog : blogs) {
            blogItems.add(new BlogItem(blog));
         }
         return new ListJsonDto<BlogItem>(blogItems).formSuccessDto();
      } catch (Exception e) {
         logger.error(e.getMessage());
         e.printStackTrace();
         return new JsonDto().formFailureDto();
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
   public @ResponseBody JsonDto getBlog(@PathVariable("id") long id) {
      try {
         Blog blog = blogService.getBlogByUidPk(id);
         BlogDetail blogDetail = new BlogDetail(blog);
         blogDetail.setComments(blogService.getCommentsByBlogId(id));
         return new DataJsonDto<BlogDetail>(blogDetail).formSuccessDto();
      } catch (Exception e) {
         logger.error(e.getMessage());
         e.printStackTrace();
         return new JsonDto().formFailureDto();
      }
   }

   @RequestMapping("/{blogId}/save.ep")
   public @ResponseBody JsonDto saveBlog(@PathVariable("blogId") long blogId,
         @RequestBody BlogDto blogDto) {
      try {
         Blog blog = getBlog(blogId, blogDto);
         blogService.saveBlog(blog);
         return new JsonDto().formSuccessDto();
      } catch (Exception e) {
         logger.error(e.getMessage());
         e.printStackTrace();
         return new JsonDto().formFailureDto();
      }
   }

   @RequestMapping("/{blogId}/publish.ep")
   public @ResponseBody JsonDto publishBlog(
         @PathVariable("blogId") long blogId, @RequestBody BlogDto blogDto) {
      try {
         Blog blog = getBlog(blogId, blogDto);
         blog.setStatus(BlogStatusConstant.PUBLISHED);
         blogService.saveBlog(blog);
         return new JsonDto().formSuccessDto();
      } catch (Exception e) {
         logger.error(e.getMessage());
         e.printStackTrace();
         return new JsonDto().formFailureDto();
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
         if (blog.getStatus() == BlogStatusConstant.PUBLISHED) {
            setStatus("PUBLISHED");
         } else {
            setStatus("UN_PUBLISHED");
         }
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
      private List<Comment> comments;

      public BlogDetail(Blog blog) {
         setBlogId(blog.getUidPk());
         setTitle(blog.getBlogTitle());
         setContent(blog.getBlogContent());
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

      public List<Comment> getComments() {
         return comments;
      }

      public void setComments(List<Comment> comments) {
         this.comments = comments;
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
