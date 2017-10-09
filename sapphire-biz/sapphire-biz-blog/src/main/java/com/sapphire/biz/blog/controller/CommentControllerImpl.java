package com.sapphire.biz.blog.controller;

import com.sapphire.biz.blog.domain.Comment;
import com.sapphire.biz.blog.service.BlogService;
import com.sapphire.biz.blog.service.CommentService;
import com.sapphire.common.utils.TimeUtil;
import com.sapphire.common.utils.dto.Dto;
import com.sapphire.common.utils.dto.JsonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.biz.user.service.UserService;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/20<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/comment")
public class CommentControllerImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentControllerImpl.class);

    @Autowired
    private CommentService commentService;
    @Autowired
    private BlogService    blogService;
    @Autowired
    private UserService    userService;

    @RequestMapping("/list.ep")
    @ResponseBody
    public JsonDto getUserComments() {
        return null;
    }

    @RequestMapping("/save.ep")
    @ResponseBody
    public JsonDto saveComment(@RequestBody CommentDto commentDto) {
        try {
            LOGGER.info("Edit comment for blog!");
            Comment comment = convertDtoToDomain(commentDto);
            commentService.saveComment(comment);
            return new JsonDto().formSuccessDto();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new JsonDto().formFailureDto(e);
        }
    }

    @RequestMapping("/{id}/delete.ep")
    @ResponseBody
    public JsonDto deleteComment(@PathVariable("id") long id) {
        try {
            LOGGER.info("Delete comment for blog!");
            commentService.deleteComment(id);
            return new JsonDto().formSuccessDto();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new JsonDto().formFailureDto(e);
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
        private long   commentId;
        private long   blogId;
        private long   userId;
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
