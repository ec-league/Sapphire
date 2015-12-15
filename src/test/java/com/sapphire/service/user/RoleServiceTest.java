package com.sapphire.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sapphire.constant.RoleNameConstant;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/15<br/>
 * Email: byp5303628@hotmail.com
 */
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
@TestExecutionListeners({ TransactionalTestExecutionListener.class })
public class RoleServiceTest extends AbstractTestNGSpringContextTests {

   @Autowired
   private RoleService roleService;

   @Test
   public void test() {
      Assert.assertEquals(roleService.getAdminRole().getRoleName(),
            RoleNameConstant.ADMIN_ROLE_NAME);
      Assert.assertEquals(roleService.getGuestRole().getRoleName(),
            RoleNameConstant.GUEST_ROLE_NAME);
      Assert.assertEquals(roleService.getUserRole().getRoleName(),
            RoleNameConstant.USER_ROLE_NAME);
   }
}
