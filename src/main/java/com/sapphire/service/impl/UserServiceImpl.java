package com.sapphire.service.impl;

import org.springframework.stereotype.Service;

import com.sapphire.domain.User;
import com.sapphire.service.UserService;

/**
 * Created by Administrator <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
@Service("userService")
public class UserServiceImpl implements UserService {
   public long saveOrMerge(User user) {
      return 0;
   }

   public User getUserByUserName(String username) {
      return null;
   }
}
