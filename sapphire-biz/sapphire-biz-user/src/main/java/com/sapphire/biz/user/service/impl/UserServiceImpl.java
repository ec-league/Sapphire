package com.sapphire.biz.user.service.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.sapphire.biz.user.domain.User;
import com.sapphire.biz.user.dto.UserDto;
import com.sapphire.biz.user.repository.UserRepository;
import com.sapphire.biz.user.service.RoleService;
import com.sapphire.biz.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService    roleService;

    public long createUser(UserDto user) {
        User repo = convertDtoToDomain(user);
        if (userRepository.findUserByUsernameOrEmail(user.getUsername(), user.getEmail()) != null) {
            throw new EntityExistsException(
                String.format("Username : \"%s\", Email : \"%s\" already exists.",
                    user.getUsername(), user.getEmail()));
        }
        repo.setRole(roleService.getUserRole());
        return userRepository.save(repo).getUidPk();
    }

    public long updateUserInfo(UserDto user) {
        User repo = userRepository.findUserByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (repo == null) {
            throw new EntityNotFoundException(
                String.format("Username : \"%s\", Email : \"%s\" does not exists.",
                    user.getUsername(), user.getEmail()));
        }
        repo.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        repo.setEmail(user.getEmail());
        return userRepository.save(repo).getUidPk();
    }

    private static User convertDtoToDomain(UserDto user) {
        User u = new User();
        u.setUidPk(user.getUserId());
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return u;
    }

    public User getUserByUserNameOrEmail(String val) {
        User u = userRepository.findUserByUsernameOrEmail(val, val);
        if (u == null) {
            throw new EntityNotFoundException(
                "Cannot find entity with username or email :\"" + val + "\"");
        }
        return u;
    }

    public User getUserById(long id) {
        User u = userRepository.findOne(id);
        if (u == null) {
            throw new EntityNotFoundException("Id is : \"" + id + "\"");
        }
        return u;
    }

    public boolean authenticateUser(String username, String password) {
        User u = userRepository.findUserByUsernameOrEmail(username, username);
        if (u == null) {
            return false;
        }
        return u.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()));
    }

    public List<User> getUsers() {
        List<User> users = userRepository.getAllUsers();
        if (users == null || users.isEmpty()) {
            return Collections.emptyList();
        }
        return users;
    }
}
