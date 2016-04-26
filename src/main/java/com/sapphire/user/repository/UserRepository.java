package com.sapphire.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sapphire.user.domain.User;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/13.<br/>
 * Email: byp5303628@hotmail.com
 */
public interface UserRepository extends CrudRepository<User, Long> {
   @Query("select u from User as u where u.username = :username or u.email = :email")
   User findUserByUsernameOrEmail(@Param("username") String username,
         @Param("email") String email);

   @Query("select u from User as u order by u.uidPk")
   List<User> getAllUsers();
}
