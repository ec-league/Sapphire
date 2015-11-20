package com.sapphire.service.blog;

import com.sapphire.domain.blog.Comment;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/20<br/>
 * Email: byp5303628@hotmail.com
 */
public interface CommentService {
   long addComment(Comment comment);

   long saveComment(Comment comment);

   void deleteComment(Comment comment);

   List<Comment> getCommentsByUserId(long userId);
}
