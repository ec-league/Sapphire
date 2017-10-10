package com.sapphire.common.dal.user.dto;

import com.sapphire.common.utils.dto.Dto;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/8.<br/>
 * Email: byp5303628@hotmail.com
 */
public class UserDto implements Dto {
    private long   userId;
    private String username;
    private String password;
    private String email;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
