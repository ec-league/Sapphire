package com.sapphire.user.service.impl;

import com.sapphire.user.domain.UserInfo;
import com.sapphire.user.repository.UserInfoRepository;
import com.sapphire.user.service.UserInfoService;
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
