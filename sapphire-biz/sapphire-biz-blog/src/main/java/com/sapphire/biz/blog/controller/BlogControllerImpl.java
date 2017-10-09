package com.sapphire.biz.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.sapphire.biz.blog.domain.Comment;
import com.sapphire.biz.blog.service.BlogService;
import com.sapphire.common.utils.MarkDownUtil;
import com.sapphire.common.utils.TimeUtil;
import com.sapphire.common.utils.dto.DataJsonDto;
import com.sapphire.common.utils.dto.Dto;
import com.sapphire.common.utils.dto.JsonDto;
import com.sapphire.common.utils.dto.ListJsonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapphire.biz.blog.constant.BlogStatus;
import com.sapphire.biz.blog.domain.Blog;
import com.sapphire.biz.user.domain.User;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/20<br/>
 * Email: byp5303628@hotmail.com
 */
@Controller
@RequestMapping("/blog")
public class BlogControllerImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogControllerImpl.class);
    @Autowired
    private BlogService blogService;

    /**
    * Get specified user's blog list, which contains title.
    * 
    * @return
    */
    @RequestMapping("/list.ep")
    @ResponseBody
    public JsonDto getUserBlogList() {
        try {
            User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Blog> blogs = blogService.getBlogListByUserId(u.getUidPk());
            List<BlogItem> blogItems = new ArrayList<>();
            for (Blog blog : blogs) {
                blogItems.add(new BlogItem(blog));
            }
            return new ListJsonDto<BlogItem>(blogItems).formSuccessDto();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new JsonDto().formFailureDto(e);
        }
    }

    /**
    * Get specified blog by uidPk, which contains title, contentOrigin and
    * comments.
    * 
    * @param id
    *           , specified blog uidPk.
    * @return
    */
    @RequestMapping("/{id}/get.ep")
    @ResponseBody
    public JsonDto getBlog(@PathVariable("id") long id) {
        try {
            Blog blog = blogService.getBlogByUidPk(id);
            BlogDetail blogDetail = new BlogDetail(blog);
            List<CommentDto> dtos = new ArrayList<CommentDto>();
            for (Comment comment : blogService.getCommentsByBlogId(id)) {
                dtos.add(new CommentDto(comment));
            }
            blogDetail.setComments(dtos);
            return new DataJsonDto<BlogDetail>(blogDetail).formSuccessDto();
        } catch (EntityNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new JsonDto().formFailureDto(e);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new JsonDto().formFailureDto(e);
        }
    }

    @RequestMapping("/user-info.ep")
    @ResponseBody
    public JsonDto getUserInfo() {
        try {
            User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            BlogUserDto dto = new BlogUserDto();
            dto.setUsername(u.getUsername());
            dto.setUserId(dto.getUserId());
            dto.setTotalHit(blogService.getUserHitById(u.getUidPk()));
            dto.setBlogs(blogService.getBlogListByUserId(u.getUidPk()).size());
            return new DataJsonDto<BlogUserDto>(dto).formSuccessDto();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new JsonDto().formFailureDto(e);
        }
    }

    @RequestMapping("/{blogId}/save.ep")
    @ResponseBody
    public JsonDto saveBlog(@PathVariable("blogId") long blogId, @RequestBody BlogDto blogDto) {
        try {
            Blog blog = getBlog(blogId, blogDto);
            User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            blog.setUser(u);
            blogService.saveBlog(blog);
            JsonDto dto = new DataJsonDto<BlogDetail>(new BlogDetail(blog));
            return dto.formSuccessDto();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new JsonDto().formFailureDto(e);
        }
    }

    @RequestMapping("/{blogId}/publish.ep")
    @ResponseBody
    public JsonDto publishBlog(@PathVariable("blogId") long blogId, @RequestBody BlogDto blogDto) {
        try {
            Blog blog = getBlog(blogId, blogDto);
            blog.setBlogStatus(BlogStatus.PUBLISHED);
            User u = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            blog.setUser(u);
            blogService.saveBlog(blog);
            return new JsonDto().formSuccessDto();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return new JsonDto().formFailureDto(e);
        }
    }

    private Blog getBlog(long blogId, BlogDto blogDto) {
        Blog blog;
        if (blogId == 0) {
            blog = new Blog();
            blog.setCreateTime(TimeUtil.now());
        } else {
            blog = blogService.getBlogByUidPk(blogId);
            blog.setBlogContent(blogDto.getContent());
        }
        blog.setLastModifyTime(TimeUtil.now());
        blog.setBlogTitle(blogDto.getTitle());
        blog.setBlogContent(blogDto.getContent());
        return blog;
    }

    private static class BlogUserDto implements Dto {
        private long   userId;
        private String username;
        private int    blogs;
        private int    totalHit;

        public int getBlogs() {
            return blogs;
        }

        public void setBlogs(int blogs) {
            this.blogs = blogs;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getTotalHit() {
            return totalHit;
        }

        public void setTotalHit(int totalHit) {
            this.totalHit = totalHit;
        }
    }

    private static class BlogItem implements Dto {
        private static final int BLOG_SNAPSHOT_LENGTH = 290;
        private long             blogId;
        private String           title;
        private String           createTime;
        private String           lastModifyTime;
        private String           status;
        private String           contentSnapshot;
        private long             hit;

        public BlogItem(Blog blog) {
            setBlogId(blog.getUidPk());
            setTitle(blog.getBlogTitle());
            setCreateTime(TimeUtil.formatTime(blog.getCreateTime()));
            setLastModifyTime(TimeUtil.formatTime(blog.getLastModifyTime()));
            setStatus(blog.getBlogStatus().toString());
            setHit(blog.getBlogHit());
            String s = blog.getBlogContent().length() < BLOG_SNAPSHOT_LENGTH ? blog.getBlogContent()
                : blog.getBlogContent().substring(0, BLOG_SNAPSHOT_LENGTH) + "...";
            setContentSnapshot(s);
        }

        public String getContentSnapshot() {
            return contentSnapshot;
        }

        public void setContentSnapshot(String contentSnapshot) {
            this.contentSnapshot = contentSnapshot;
        }

        public long getHit() {
            return hit;
        }

        public void setHit(long hit) {
            this.hit = hit;
        }

        public long getBlogId() {
            return blogId;
        }

        public void setBlogId(long blogId) {
            this.blogId = blogId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getLastModifyTime() {
            return lastModifyTime;
        }

        public void setLastModifyTime(String lastModifyTime) {
            this.lastModifyTime = lastModifyTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    private static class BlogDetail implements Dto {
        private long             blogId;
        private String           title;
        private String           contentOrigin;
        private String           contentMarkdown;
        private String           lastModifyTime;
        private long             hit;
        private List<CommentDto> comments;

        public BlogDetail(Blog blog) {
            setBlogId(blog.getUidPk());
            setTitle(blog.getBlogTitle());
            setLastModifyTime(TimeUtil.formatTime(blog.getLastModifyTime()));
            setContentMarkdown(MarkDownUtil.toHtml(blog.getBlogContent()));
            setContentOrigin(blog.getBlogContent());
            setHit(blog.getBlogHit());
        }

        public String getContentMarkdown() {
            return contentMarkdown;
        }

        public void setContentMarkdown(String contentMarkdown) {
            this.contentMarkdown = contentMarkdown;
        }

        public long getHit() {
            return hit;
        }

        public void setHit(long hit) {
            this.hit = hit;
        }

        public String getLastModifyTime() {
            return lastModifyTime;
        }

        public void setLastModifyTime(String lastModifyTime) {
            this.lastModifyTime = lastModifyTime;
        }

        public List<CommentDto> getComments() {
            return comments;
        }

        public void setComments(List<CommentDto> comments) {
            this.comments = comments;
        }

        public long getBlogId() {
            return blogId;
        }

        public void setBlogId(long blogId) {
            this.blogId = blogId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContentOrigin() {
            return contentOrigin;
        }

        public void setContentOrigin(String contentOrigin) {
            this.contentOrigin = contentOrigin;
        }

    }

    private static class CommentDto implements Dto {
        private long   commentId;
        private String commentContent;
        private String createTime;
        private String lastModifyTime;

        public CommentDto(Comment comment) {
            setCommentId(comment.getUidPk());
            setCommentContent(comment.getContent());
            setCreateTime(TimeUtil.formatTime(comment.getCreateTime()));
            setLastModifyTime(TimeUtil.formatTime(comment.getLastModifyTime()));
        }

        public long getCommentId() {
            return commentId;
        }

        public void setCommentId(long commentId) {
            this.commentId = commentId;
        }

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getLastModifyTime() {
            return lastModifyTime;
        }

        public void setLastModifyTime(String lastModifyTime) {
            this.lastModifyTime = lastModifyTime;
        }
    }

    private static class BlogDto implements Dto {
        private String title;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
