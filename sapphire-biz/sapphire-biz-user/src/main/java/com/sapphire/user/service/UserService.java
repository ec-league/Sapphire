package com.sapphire.user.service;

import java.util.List;

import com.sapphire.user.domain.User;
import com.sapphire.user.dto.UserDto;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
public interface UserService {
    long createUser(UserDto user);

    long updateUserInfo(UserDto user);

    User getUserByUserNameOrEmail(String val);

    User getUserById(long id);

    boolean authenticateUser(String username, String password);

    List<User> getUsers();
}
