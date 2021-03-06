package com.sapphire.service.blog;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.TransactionalBaseTest;
import com.sapphire.biz.blog.service.BlogService;
import com.sapphire.biz.blog.service.CommentService;
import com.sapphire.biz.user.dto.UserDto;
import com.sapphire.biz.user.service.UserService;
import com.sapphire.common.dal.blog.constant.BlogStatus;
import com.sapphire.common.dal.blog.domain.Blog;
import com.sapphire.common.dal.blog.domain.Comment;
import com.sapphire.common.dal.user.domain.User;
import com.sapphire.common.utils.TimeUtil;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/11<br/>
 * Email: byp5303628@hotmail.com
 */
public class BlogServiceTest extends TransactionalBaseTest {
   @Autowired
   private BlogService blogService;
   @Autowired
   private UserService userService;
   @Autowired
   private CommentService commentService;

   @Test
   public void test() {
      UserDto dto = new UserDto();
      dto.setUserName(RandomStringUtils.randomAlphabetic(8));
      dto.setPassword(RandomStringUtils.randomAlphabetic(12));
      dto.setEmail(String.format("%s@%s",
            RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(5)));
      long userId = userService.createUser(dto);
      User user = userService.getUserById(userId);
      Blog blog = new Blog();
      blog.setCreateTime(TimeUtil.now());
      blog.setLastModifyTime(TimeUtil.now());
      blog.setBlogTitle("test");
      blog.setBlogStatus(BlogStatus.PUBLISHED);
      blog.setUser(user);
      blog.setBlogContent("test blog content");
      long blogId = blogService.saveBlog(blog);

      Blog testBlog = blogService.getBlogByUidPk(blogId);

      Assert.assertEquals(testBlog.getBlogContent(), blog.getBlogContent());
      Assert.assertEquals(testBlog.getBlogTitle(), blog.getBlogTitle());

      List<Blog> blogList =
            blogService.getBlogListByUserId(blog.getUser().getUidPk());
      Assert.assertFalse(blogList.isEmpty());
      boolean hasBlog = false;
      for (Blog b : blogList) {
         if (b.getUidPk() == testBlog.getUidPk()) {
            hasBlog = true;
            break;
         }
      }
      Assert.assertTrue(hasBlog);

      Assert.assertTrue(blogService.getCommentsByBlogId(blogId).isEmpty());

      Comment comment = new Comment();
      comment.setContent("test comment");
      comment.setBlog(testBlog);
      comment.setUser(userService.getUserById(userId));
      comment.setCreateTime(TimeUtil.now());
      comment.setLastModifyTime(TimeUtil.now());

      long commentId = commentService.saveComment(comment);

      Assert.assertFalse(blogService.getCommentsByBlogId(blogId).isEmpty());
      Comment testComment1 = blogService.getCommentsByBlogId(blogId).get(0);
      Comment testComment2 = commentService.getCommentsByUserId(userId).get(0);

      Assert.assertEquals(commentId, testComment1.getUidPk());
      Assert.assertEquals(commentId, testComment2.getUidPk());

      commentService.deleteComment(commentId);
      Assert.assertTrue(blogService.getCommentsByBlogId(blogId).isEmpty());
      Assert.assertTrue(commentService.getCommentsByUserId(userId).isEmpty());
   }

   @Test(expected = EntityNotFoundException.class)
   public void testEmpty() {
      long userId = 0;
      Assert.assertTrue(blogService.getBlogListByUserId(userId).isEmpty());

      long blogId = 0;
      blogService.getBlogByUidPk(blogId);
   }

   @Test
   public void testIncrease() {
      UserDto dto = new UserDto();
      dto.setUserName(RandomStringUtils.randomAlphabetic(8));
      dto.setPassword(RandomStringUtils.randomAlphabetic(12));
      dto.setEmail(String.format("%s@%s",
            RandomStringUtils.randomAlphabetic(5),
            RandomStringUtils.randomAlphabetic(5)));
      long userId = userService.createUser(dto);
      User user = userService.getUserById(userId);
      Blog blog = new Blog();
      blog.setCreateTime(TimeUtil.now());
      blog.setLastModifyTime(TimeUtil.now());
      blog.setBlogTitle("test");
      blog.setBlogStatus(BlogStatus.PUBLISHED);
      blog.setUser(user);
      blog.setBlogContent("test blog content");
      long blogId = blogService.saveBlog(blog);

      int nowHit = blogService.getUserHitById(userId);
      Assert.assertEquals(nowHit, 0);
      blogService.loadBlog(blogId);
      blog = blogService.getBlogByUidPk(blogId);
      nowHit = blogService.getUserHitById(userId);
      Assert.assertEquals(nowHit, 1);
      Assert.assertEquals(blog.getBlogHit(), 1);
   }

   @Test(expected = EntityNotFoundException.class)
   public void testIncreaseEmpty() {
      blogService.loadBlog(0);
   }
}
