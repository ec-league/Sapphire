package com.sapphire.service.user;

import com.sapphire.BaseTest;
import com.sapphire.domain.User;
import com.sapphire.dto.user.UserDto;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/15<br/>
 * Email: byp5303628@hotmail.com
 */
public class UserServiceTest extends BaseTest {
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
      dto.setUsername(username);
      dto.setPassword(password);
      dto.setEmail(email);
      long userId = userService.createUser(dto);

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

      Assert.assertEquals(newPassword, testUser.getPassword());
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
      dto.setUsername(username);
      dto.setPassword(password);
      dto.setEmail(email);
      userService.createUser(dto);
      dto.setUsername(RandomStringUtils.randomAlphabetic(11));
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
      dto.setUsername(username);
      dto.setPassword(password);
      dto.setEmail(email);
      userService.updateUserInfo(dto);
   }

   @Test(expected = EntityNotFoundException.class)
   public void testAbnormal3() {
      userService.getUserByUserNameOrEmail(RandomStringUtils
            .randomAlphanumeric(15));
   }

   @Test
   public void testAbnormal4() {
      Assert.assertFalse(userService.authenticateUser(
            RandomStringUtils.randomAlphabetic(11),
            RandomStringUtils.randomAlphabetic(12)));
   }
}
