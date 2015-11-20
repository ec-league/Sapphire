package com.sapphire.service.blog.impl;

import com.sapphire.domain.blog.Comment;
import com.sapphire.repository.blog.CommentRepository;
import com.sapphire.service.blog.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/20<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
   @Autowired
   private CommentRepository commentRepository;

   public long addComment(Comment comment) {
      return commentRepository.save(comment).getUidPk();
   }

   public long saveComment(Comment comment) {
      return commentRepository.save(comment).getUidPk();
   }

   public void deleteComment(Comment comment) {
      commentRepository.delete(comment);
   }

   public List<Comment> getCommentsByUserId(long userId) {
      return commentRepository.getAllCommentsByUserId(userId);
   }
}
