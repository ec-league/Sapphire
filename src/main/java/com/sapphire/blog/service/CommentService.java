package com.sapphire.blog.service;

import java.util.List;

import com.sapphire.blog.domain.Comment;

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
