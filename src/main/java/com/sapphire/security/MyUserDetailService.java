package com.sapphire.security;

import com.sapphire.domain.User;
import com.sapphire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityNotFoundException;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/18<br/>
 * Email: byp5303628@hotmail.com
 */
public class MyUserDetailService implements UserDetailsService {
//   @Autowired
//   private UserService userService;

   public UserDetails loadUserByUsername(String username)
         throws UsernameNotFoundException {
      try {
         User user = new User();
         System.out.println(user);
         user.setUsername("admin");
         user.setPassword("admin");

         return user;
      } catch (EntityNotFoundException e) {
         throw new UsernameNotFoundException(String.format("Username : \"%s\"",
               username), e);
      }
   }
}
