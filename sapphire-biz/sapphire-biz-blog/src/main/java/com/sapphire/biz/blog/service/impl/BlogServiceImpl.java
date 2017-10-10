package com.sapphire.biz.blog.service.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.sapphire.common.dal.blog.domain.Comment;
import com.sapphire.common.dal.blog.repository.BlogRepository;
import com.sapphire.common.dal.blog.repository.CommentRepository;
import com.sapphire.biz.blog.service.BlogService;
import com.sapphire.common.dal.blog.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/20<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository    blogRepository;
    @Autowired
    private CommentRepository commentRepository;

    /**
    * Get user's blog list ignore the blog status, whether the blog is published
    * or not.
    * 
    * @param userId
    *           , User's Id
    * @return
    */
    public List<Blog> getBlogListByUserId(long userId) {
        List<Blog> blogs = blogRepository.getAllBlogsByUserId(userId);
        if (blogs == null || blogs.isEmpty()) {
            return Collections.emptyList();
        }
        return blogs;
    }

    public Blog getBlogByUidPk(long uidPk) {
        Blog blog = blogRepository.findOne(uidPk);
        if (blog == null) {
            throw new EntityNotFoundException(String.format("Blog not found : \"%d\"", uidPk));
        }
        return blogRepository.findOne(uidPk);
    }

    public long saveBlog(Blog blog) {
        return blogRepository.save(blog).getUidPk();
    }

    public void loadBlog(long blogId) {
        Blog blog = blogRepository.findOne(blogId);
        if (blog == null) {
            throw new EntityNotFoundException(String.format("Blog id %d does not exist!", blogId));
        }
        blog.setBlogHit(blog.getBlogHit() + 1);
        blogRepository.save(blog);
    }

    public List<Comment> getCommentsByBlogId(long blogId) {
        List<Comment> comments = commentRepository.getAllCommentsByBlogId(blogId);
        if (comments == null || comments.isEmpty()) {
            return Collections.emptyList();
        }
        return commentRepository.getAllCommentsByBlogId(blogId);
    }

    public int getUserHitById(long userId) {
        return blogRepository.getUserHitById(userId);
    }
}
