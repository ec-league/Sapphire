package com.sapphire.blog.service;

import com.sapphire.blog.domain.Comment;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/20<br/>
 * Email: byp5303628@hotmail.com
 */
public interface CommentService {
   long saveComment(Comment comment);

   void deleteComment(long id);

   List<Comment> getCommentsByUserId(long userId);
}
