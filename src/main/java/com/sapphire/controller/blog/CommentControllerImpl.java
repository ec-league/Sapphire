package com.sapphire.controller.blog;

import com.sapphire.common.TimeUtil;
import com.sapphire.domain.blog.Comment;
import com.sapphire.dto.DataJsonDto;
import com.sapphire.dto.Dto;
import com.sapphire.dto.JsonDto;
import com.sapphire.service.UserService;
import com.sapphire.service.blog.BlogService;
import com.sapphire.service.blog.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/20<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/comment")
public class CommentControllerImpl {

   private static Logger logger = LoggerFactory
         .getLogger(CommentControllerImpl.class);

   @Autowired
   private CommentService commentService;
   @Autowired
   private BlogService blogService;
   @Autowired
   private UserService userService;

   @RequestMapping("/{userId}/list.ep")
   public @ResponseBody JsonDto getUserComments(
         @PathVariable("userId") long userId) {
      List<Comment> commentList = commentService.getCommentsByUserId(userId);
      return new DataJsonDto<List<Comment>>(commentList).formSuccessDto();
   }

   @RequestMapping("/add.ep")
   public @ResponseBody JsonDto addComment(@RequestBody CommentDto commentDto) {
      try {
         logger.info("Add comment for blog!");
         Comment comment = convertDtoToDomain(commentDto);
         commentService.addComment(comment);
         return new JsonDto().formSuccessDto();
      } catch (Exception e) {
         logger.error(e.getMessage());
         e.printStackTrace();
         return new JsonDto().formFailureDto();
      }
   }

   @RequestMapping("/save.ep")
   public @ResponseBody JsonDto saveComment(@RequestBody CommentDto commentDto) {
      try {
         logger.info("Edit comment for blog!");
         Comment comment = convertDtoToDomain(commentDto);
         commentService.saveComment(comment);
         return new JsonDto().formSuccessDto();
      } catch (Exception e) {
         logger.error(e.getMessage());
         e.printStackTrace();
         return new JsonDto().formFailureDto();
      }
   }

   @RequestMapping("/{id}/delete.ep")
   public @ResponseBody JsonDto deleteComment(@PathVariable("id") long id) {
      try {
         logger.info("Delete comment for blog!");
         commentService.deleteComment(id);
         return new JsonDto().formSuccessDto();
      } catch (Exception e) {
         logger.error(e.getMessage());
         e.printStackTrace();
         return new JsonDto().formFailureDto();
      }
   }

   private Comment convertDtoToDomain(CommentDto dto) {
      Comment comment = new Comment();
      comment.setBlog(blogService.getBlogByUidPk(dto.getBlogId()));
      comment.setUser(userService.getUserById(dto.getUserId()));
      comment.setContent(dto.getContent());
      comment.setCreateTime(TimeUtil.now());
      comment.setLastModifyTime(TimeUtil.now());
      comment.setUidPk(dto.getCommentId());
      return comment;
   }

   private static class CommentDto implements Dto {
      private long commentId;
      private long blogId;
      private long userId;
      private String content;

      public long getBlogId() {
         return blogId;
      }

      public void setBlogId(long blogId) {
         this.blogId = blogId;
      }

      public long getUserId() {
         return userId;
      }

      public void setUserId(long userId) {
         this.userId = userId;
      }

      public String getContent() {
         return content;
      }

      public void setContent(String content) {
         this.content = content;
      }

      public long getCommentId() {
         return commentId;
      }

      public void setCommentId(long commentId) {
         this.commentId = commentId;
      }
   }
}
