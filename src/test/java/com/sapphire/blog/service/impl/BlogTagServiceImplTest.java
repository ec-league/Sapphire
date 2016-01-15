package com.sapphire.blog.service.impl;

import com.sapphire.BaseTest;
import com.sapphire.blog.domain.Blog;
import com.sapphire.blog.domain.BlogTag;
import com.sapphire.blog.service.BlogService;
import com.sapphire.blog.service.BlogTagService;
import com.sapphire.user.domain.User;
import com.sapphire.user.dto.UserDto;
import com.sapphire.user.service.UserService;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityExistsException;
import java.util.List;

/**
 * BlogTagServiceImpl Tester.
 * 
 * @author EthanPark
 * @since <pre>
 * Ò»ÔÂ 15, 2016
 * </pre>
 * @version 1.0
 */
public class BlogTagServiceImplTest extends BaseTest {
   @Autowired
   private BlogTagService blogTagService;
   @Autowired
   private BlogService blogService;
   @Autowired
   private UserService userService;

   @Before
   public void before() throws Exception {

   }

   @After
   public void after() throws Exception {
   }

   /**
    * 
    * Method: getBlogsByTagName(String tagName)
    * 
    */
   @Test(expected = EntityExistsException.class)
   public void testGetBlogsByTagName() throws Exception {
      Blog blog = new Blog();
      blog.setBlogTitle(RandomStringUtils.randomAlphanumeric(10));
      blog.setBlogContent(RandomStringUtils.randomAlphanumeric(150));
      UserDto dto = new UserDto();
      dto.setUsername(RandomStringUtils.randomAlphabetic(8));
      dto.setPassword(RandomStringUtils.randomAlphabetic(8));
      long id = userService.createUser(dto);
      User user = userService.getUserById(id);
      blog.setUser(user);
      long blogId = blogService.saveBlog(blog);

      BlogTag tag = new BlogTag();
      String tagName = RandomStringUtils.randomAlphanumeric(4);
      tag.setTagName(tagName);
      tag.setBlogId(blogId);
      blogTagService.addBlogTag(tag);

      List<BlogTag> tags = blogTagService.getBlogTagsByBlogId(blogId);
      Assert.assertEquals(tags.size(), 1);
      Assert.assertEquals(tags.get(0).getTagName(), tag.getTagName());

      tag.setTagName(RandomStringUtils.randomAlphanumeric(5));
      blogTagService.addBlogTag(tag);
      tags = blogTagService.getBlogTagsByBlogId(blogId);
      Assert.assertEquals(tags.size(), 2);

      tag.setTagName(tagName);
      blogTagService.addBlogTag(tag);
   }

   /**
    * 
    * Method: getBlogTagsByBlogId(long blogId)
    * 
    */
   @Test
   public void testGetBlogTagsByBlogId() throws Exception {
      //TODO: Test goes here... 
   }

   /**
    * 
    * Method: addBlogTag(BlogTag tag)
    * 
    */
   @Test
   public void testAddBlogTag() throws Exception {
      //TODO: Test goes here... 
   }

   /**
    * 
    * Method: deleteBlogTag(long blogTagId)
    * 
    */
   @Test
   public void testDeleteBlogTag() throws Exception {
      //TODO: Test goes here... 
   }
}
