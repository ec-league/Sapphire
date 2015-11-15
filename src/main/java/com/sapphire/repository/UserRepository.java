package com.sapphire.repository;

import com.sapphire.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/13.<br/>
 * Email: byp5303628@hotmail.com
 */
@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
   @Query("select u from UserImpl as u where u.username = :val or u.email = :val")
   User findByUsernameOrEmail(@Param("val") String val);
}
