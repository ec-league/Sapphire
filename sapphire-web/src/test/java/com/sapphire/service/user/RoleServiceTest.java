package com.sapphire.service.user;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sapphire.TransactionalBaseTest;
import com.sapphire.biz.user.service.RoleService;
import com.sapphire.common.dal.user.constant.RoleNameConstant;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/15<br/>
 * Email: byp5303628@hotmail.com
 */
public class RoleServiceTest extends TransactionalBaseTest {
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
