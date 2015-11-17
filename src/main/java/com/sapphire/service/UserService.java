package com.sapphire.service;

import com.sapphire.domain.User;
import com.sapphire.dto.user.UserDto;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
public interface UserService {
   long saveOrMerge(UserDto user);

   User getUserByUserNameOrEmail(String val);

   User getUserById(long id);

   boolean authenticateUser(String username, String password);
}
