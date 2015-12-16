package com.sapphire.service.blog;

import com.sapphire.BaseTest;
import com.sapphire.common.TimeUtil;
import com.sapphire.constant.BlogStatus;
import com.sapphire.domain.User;
import com.sapphire.domain.blog.Blog;
import com.sapphire.domain.blog.Comment;
import com.sapphire.dto.user.UserDto;
import com.sapphire.service.user.UserService;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/11<br/>
 * Email: byp5303628@hotmail.com
 */
public class BlogServiceTest extends BaseTest {
   @Autowired
   private BlogService blogService;
   @Autowired
   private UserService userService;
   @Autowired
   private CommentService commentService;

   @Test
   public void test() {
      UserDto dto = new UserDto();
      dto.setUsername(RandomStringUtils.randomAlphabetic(8));
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
}
