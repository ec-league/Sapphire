package com.sapphire.controller.blog;

import com.sapphire.domain.blog.Comment;
import com.sapphire.dto.DataJsonDto;
import com.sapphire.dto.Dto;
import com.sapphire.dto.JsonDto;
import com.sapphire.service.blog.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

   @Autowired
   private CommentService commentService;

   @RequestMapping("/{userId}/list.ep")
   public @ResponseBody JsonDto getUserComments(
         @PathVariable("userId") long userId) {
      List<Comment> commentList = commentService.getCommentsByUserId(userId);
      return new DataJsonDto<List<Comment>>(commentList).formSuccessDto();
   }

   public JsonDto addComment(CommentDto commentDto) {
      Comment comment = new Comment();
//      comment.setContent();
//      commentService.addComment()
      return null;
   }

   public JsonDto saveComment(CommentDto commentDto) {
      return null;
   }

   public JsonDto deleteComment(long id) {
      return null;
   }

   private static class CommentDto implements Dto {
      private String content;

   }
}
