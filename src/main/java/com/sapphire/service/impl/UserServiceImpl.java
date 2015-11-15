package com.sapphire.service.impl;

import com.sapphire.domain.impl.UserImpl;
import com.sapphire.dto.user.UserDto;
import com.sapphire.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.sapphire.domain.User;
import com.sapphire.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("userService")
public class UserServiceImpl implements UserService {

   @Resource
   private UserRepository userRepository;

   public long saveOrMerge(UserDto user) {
      User u = convertDtoToDomain(user);
      return userRepository.save(u).getUidPk();
   }

   private User convertDtoToDomain(UserDto user) {
      User u = new UserImpl();
      u.setUidPk(user.getUserId());
      u.setUsername(user.getUsername());
      u.setEmail(user.getEmail());
      u.setPassword(user.getPassword());
      return u;
   }

   public User getUserByUserNameOrEmail(String val) {
      return userRepository.findByUsernameOrEmail(val);
   }

   public User getUserById(long id) {
      return userRepository.findOne(id);
   }
}
