package com.sapphire.repository;

import com.sapphire.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/13.<br/>
 * Email: byp5303628@hotmail.com
 */
public interface UserRepository extends CrudRepository<User, Long> {
   @Query("select u from User as u where u.username = :username or u.email = :email")
   User findUserByUsernameOrEmail(@Param("username") String username, @Param("email") String email);
}
