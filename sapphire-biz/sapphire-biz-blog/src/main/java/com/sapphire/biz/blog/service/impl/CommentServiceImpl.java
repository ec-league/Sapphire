package com.sapphire.biz.blog.service.impl;

import java.util.List;

import com.sapphire.biz.blog.domain.Comment;
import com.sapphire.biz.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapphire.biz.blog.service.CommentService;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/20<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public long saveComment(Comment comment) {
        return commentRepository.save(comment).getUidPk();
    }

    public void deleteComment(long id) {
        commentRepository.delete(id);
    }

    public List<Comment> getCommentsByUserId(long userId) {
        return commentRepository.getAllCommentsByUserId(userId);
    }
}
