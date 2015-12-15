package com.sapphire.service.user;

import com.sapphire.domain.User;
import com.sapphire.dto.user.UserDto;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/15<br/>
 * Email: byp5303628@hotmail.com
 */
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class UserServiceTest extends AbstractTestNGSpringContextTests {
   @Autowired
   private UserService userService;

   @Test
   @Rollback
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
}
