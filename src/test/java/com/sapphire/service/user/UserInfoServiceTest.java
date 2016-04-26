package com.sapphire.service.user;

import com.sapphire.BaseTest;
import com.sapphire.user.domain.UserInfo;
import com.sapphire.user.service.UserInfoService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityExistsException;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/22<br/>
 * Email: byp5303628@hotmail.com
 */
public class UserInfoServiceTest extends BaseTest {
   @Autowired
   private UserInfoService userInfoService;

   @Test
   public void testBasic() {
      UserInfo userInfo = new UserInfo();
      userInfo.setUserId(1);
      userInfo.setImgSrc("");
      userInfo.setName("EthanPark");
      userInfoService.save(userInfo);

      UserInfo testUserInfo = userInfoService.getUserInfoByUserId(1);

      Assert.assertEquals(testUserInfo.getName(), userInfo.getName());
   }

   @Test(expected = EntityExistsException.class)
   public void testAbnormal() {
      UserInfo userInfo = new UserInfo();
      userInfo.setUserId(1);
      userInfo.setImgSrc("");
      userInfo.setName("EthanPark");
      userInfoService.save(userInfo);
      UserInfo newUserInfo = new UserInfo();
      newUserInfo.setName("");
      newUserInfo.setImgSrc("");
      newUserInfo.setUserId(1);
      userInfoService.save(newUserInfo);
   }
}
