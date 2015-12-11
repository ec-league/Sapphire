package com.sapphire.service.blog;

import com.sapphire.common.TimeUtil;
import com.sapphire.constant.BlogStatus;
import com.sapphire.domain.blog.Blog;
import com.sapphire.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/11<br/>
 * Email: byp5303628@hotmail.com
 */
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class BlogServiceTest extends AbstractTestNGSpringContextTests {
   @Autowired
   private BlogService blogService;
   @Autowired
   private UserService userService;

   @Test
   public void test() {
      Blog blog = new Blog();
      blog.setCreateTime(TimeUtil.now());
      blog.setLastModifyTime(TimeUtil.now());
      blog.setBlogTitle("test");
      blog.setBlogStatus(BlogStatus.PUBLISHED);
      blog.setUser(userService.getUserById(1));
      blog.setBlogContent("test blog content");
      long blogId = blogService.saveBlog(blog);

      Blog testBlog = blogService.getBlogByUidPk(blogId);

      Assert.assertEquals(testBlog.getBlogContent(), blog.getBlogContent());
      Assert.assertEquals(testBlog.getBlogTitle(), blog.getBlogContent());

      List<Blog> blogList = blogService.getBlogListByUserId(blog.getUser().getUidPk());
      Assert.assertFalse(blogList.isEmpty());
      Assert.assertTrue(blogList.contains(testBlog));

      Assert.assertTrue(blogService.getCommentsByBlogId(blogId).isEmpty());
   }
}
