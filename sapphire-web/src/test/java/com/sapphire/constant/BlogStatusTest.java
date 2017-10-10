package com.sapphire.constant;


import org.junit.Assert;
import org.junit.Test;

import com.sapphire.common.dal.blog.constant.BlogStatus;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/14<br/>
 * Email: byp5303628@hotmail.com
 */
public class BlogStatusTest {
   @Test(expected = IllegalArgumentException.class)
   public void testBasic() {
      Assert.assertEquals(BlogStatus.PUBLISHED.getCode(), 1);

      Assert.assertEquals(BlogStatus.UN_PUBLISHED.getCode(), 0);

      BlogStatus status = BlogStatus.toBlogStatus(0);
      Assert.assertEquals(status, BlogStatus.UN_PUBLISHED);
      status = BlogStatus.toBlogStatus(1);
      Assert.assertEquals(status, BlogStatus.PUBLISHED);

      BlogStatus.toBlogStatus(2);
   }
}
