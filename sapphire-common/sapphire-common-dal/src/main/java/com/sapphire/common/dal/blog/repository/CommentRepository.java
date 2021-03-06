package com.sapphire.common.dal.blog.repository;

import com.sapphire.common.dal.blog.domain.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/19.<br/>
 * Email: byp5303628@hotmail.com
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {
    @Query("select c from Comment as c where c.blog.uidPk = :blogId")
    List<Comment> getAllCommentsByBlogId(@Param("blogId") long blogId);

    @Query("select c from Comment as c where c.user.uidPk = :userId")
    List<Comment> getAllCommentsByUserId(@Param("userId") long userId);
}
