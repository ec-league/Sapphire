package com.sapphire.service.impl;

import com.sapphire.domain.User;
import com.sapphire.dto.user.UserDto;
import com.sapphire.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapphire.service.UserService;

import javax.persistence.EntityNotFoundException;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("userService")
public class UserServiceImpl implements UserService {
   @Autowired
   private UserRepository userRepository;

   public long saveOrMerge(UserDto user) {
      userRepository.save(convertDtoToDomain(user));
      return userRepository.save(convertDtoToDomain(user)).getUidPk();
   }

   private User convertDtoToDomain(UserDto user) {
      User u = new User();
      u.setUidPk(user.getUserId());
      u.setUsername(user.getUsername());
      u.setEmail(user.getEmail());
      u.setPassword(user.getPassword());
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
         throw new EntityNotFoundException(
               "Cannot find entity with username or email :\"" + username + "\"");
      }
      return u.getPassword().equals(password);
   }

   public boolean authenticateUser(User user) {
      return false;
   }
}
