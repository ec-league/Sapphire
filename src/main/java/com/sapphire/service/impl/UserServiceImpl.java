package com.sapphire.service.impl;

import java.util.List;

import javax.persistence.*;

import com.sapphire.domain.impl.UserImpl;
import com.sapphire.dto.user.UserDto;
import com.sapphire.preload.JpaEntityManager;
import org.springframework.stereotype.Service;

import com.sapphire.domain.User;
import com.sapphire.service.UserService;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("userService")
public class UserServiceImpl implements UserService {


   public long saveOrMerge(UserDto user) {
      User u = convertDtoToDomain(user);
      JpaEntityManager.saveOrMerge(u);
      return 0;
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
      Query query =
            JpaEntityManager
                  .createQuery(
                        "select u from UserImpl as u where u.username = ?1 or u.email = ?2",
                        new Object[] { val, val });

      List<User> users = query.getResultList();

      if (users.size() == 0) {
         throw new EntityNotFoundException("Cannot find entity of val: " + val);
      }
      return users.get(0);
   }

   public User getUserById(long id) {
      Query q =
            JpaEntityManager.createQuery(
                  "select u from UserImpl as u where u.uidPk = ?1",
                  new Object[] { id });
      List<User> users = q.getResultList();
      if (users.size() == 0) {
         throw new EntityNotFoundException("User \"" + id
               + "\" does not exist!");
      }
      return users.get(0);
   }
}
