package com.sapphire.service;

import com.sapphire.domain.User;

/**
 * Created by Administrator <br/>
 * Date: 2015/11/4.<br/>
 * Email: byp5303628@hotmail.com
 */
public interface UserService {
   long saveOrMerge(User user);

   User getUserByUserName(String username);

   User getUserById(long id);
}
