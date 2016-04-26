package com.sapphire.security;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sapphire.user.service.UserService;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/18<br/>
 * Email: byp5303628@hotmail.com
 */
public class MyUserDetailService implements UserDetailsService {
   @Autowired
   private UserService userService;

   public UserDetails loadUserByUsername(String username) {
      try {
         return userService.getUserByUserNameOrEmail(username);
      } catch (EntityNotFoundException e) {
         throw new UsernameNotFoundException(String.format("Username : \"%s\"",
               username), e);
      }
   }
}
