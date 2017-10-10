package com.sapphire.biz.user.dto;

import com.sapphire.common.dal.user.domain.User;
import com.sapphire.common.utils.dto.JsonDto;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/7.<br/>
 * Email: byp5303628@hotmail.com
 */
public class UserJsonDto extends JsonDto {
    private User data;

    public UserJsonDto(User data) {
        this.data = data;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
