package com.sapphire.user.service;

import com.sapphire.user.domain.UserInfo;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/22<br/>
 * Email: byp5303628@hotmail.com
 */
public interface UserInfoService {
   long save(UserInfo userInfo);

   UserInfo getUserInfoByUserId(long userId);
}