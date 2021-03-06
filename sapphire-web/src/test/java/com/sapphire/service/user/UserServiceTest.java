package com.sapphire.service.user;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import com.sapphire.TransactionalBaseTest;
import com.sapphire.biz.user.dto.UserDto;
import com.sapphire.biz.user.service.UserService;
import com.sapphire.common.dal.user.domain.User;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/15<br/>
 * Email: byp5303628@hotmail.com
 */
public class UserServiceTest extends TransactionalBaseTest {
   @Autowired
   private UserService userService;

   @Test
   public void test() {
      UserDto dto = new UserDto();
      String username = RandomStringUtils.randomAlphabetic(10);
      String password = RandomStringUtils.randomAlphabetic(10);
      String email =
            String.format("%s@%s", RandomStringUtils.randomAlphanumeric(5),
                  RandomStringUtils.randomAlphanumeric(5));
      dto.setUserName(username);
      dto.setPassword(password);
      dto.setEmail(email);
      long userId = userService.createUser(dto);
      User user = userService.getUserByUserNameOrEmail(email);
      Assert.assertEquals(user.getUidPk(), userId);

      dto.setUserId(userId);

      Assert.assertTrue(userService.authenticateUser(username, password));
      Assert.assertTrue(userService.authenticateUser(email, password));

      String newPassword = RandomStringUtils.randomAlphanumeric(11);
      dto.setPassword(newPassword);

      userService.updateUserInfo(dto);

      Assert.assertFalse(userService.authenticateUser(username, password));
      Assert.assertFalse(userService.authenticateUser(email, password));

      Assert.assertTrue(userService.authenticateUser(username, newPassword));
      Assert.assertTrue(userService.authenticateUser(email, newPassword));
      User testUser = userService.getUserById(userId);

      Assert.assertEquals(DigestUtils.md5DigestAsHex(newPassword.getBytes()),
            testUser.getPassword());
      Assert.assertFalse(userService.getUsers().isEmpty());
   }

   @Test(expected = EntityExistsException.class)
   public void testAbnormal() {
      UserDto dto = new UserDto();
      String username = RandomStringUtils.randomAlphabetic(10);
      String password = RandomStringUtils.randomAlphabetic(10);
      String email =
            String.format("%s@%s", RandomStringUtils.randomAlphanumeric(5),
                  RandomStringUtils.randomAlphanumeric(5));
      dto.setUserName(username);
      dto.setPassword(password);
      dto.setEmail(email);
      userService.createUser(dto);
      dto.setUserName(RandomStringUtils.randomAlphabetic(11));
      userService.createUser(dto);
   }

   @Test(expected = EntityNotFoundException.class)
   public void testAbnormal2() {
      UserDto dto = new UserDto();
      String username = RandomStringUtils.randomAlphabetic(10);
      String password = RandomStringUtils.randomAlphabetic(10);
      String email =
            String.format("%s@%s", RandomStringUtils.randomAlphanumeric(5),
                  RandomStringUtils.randomAlphanumeric(5));
      dto.setUserName(username);
      dto.setPassword(password);
      dto.setEmail(email);
      userService.updateUserInfo(dto);
   }

   @Test(expected = EntityNotFoundException.class)
   public void testAbnormal3() {
      userService.getUserByUserNameOrEmail(RandomStringUtils
            .randomAlphanumeric(15));
   }

   @Test(expected = EntityNotFoundException.class)
   public void testAbnormal4() {
      Assert.assertFalse(userService.authenticateUser(
            RandomStringUtils.randomAlphabetic(11),
            RandomStringUtils.randomAlphabetic(12)));

      userService.getUserById(0);
   }
}
