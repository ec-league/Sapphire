package com.sapphire.user.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sapphire.user.domain.UserInfo;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/22<br/>
 * Email: byp5303628@hotmail.com
 */
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
   @Query("select u from UserInfo as u where u.userId = ?1")
   UserInfo getUserInfoByUserId(long userId);
}
