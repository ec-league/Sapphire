package com.sapphire.service.user.impl;

import com.sapphire.domain.user.UserInfo;
import com.sapphire.repository.user.UserInfoRepository;
import com.sapphire.service.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/22<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
   @Autowired
   private UserInfoRepository userInfoRepository;

   public long save(UserInfo userInfo) {
      return userInfoRepository.save(userInfo).getUidPk();
   }

   public UserInfo getUserInfoByUserId(long userId) {
      return userInfoRepository.getUserInfoByUserId(userId);
   }
}
