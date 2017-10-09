package com.sapphire.biz.user.service;

import com.sapphire.biz.user.domain.UserInfo;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/22<br/>
 * Email: byp5303628@hotmail.com
 */
public interface UserInfoService {
    long save(UserInfo userInfo);

    UserInfo getUserInfoByUserId(long userId);

    void delete(long uidPk);
}
