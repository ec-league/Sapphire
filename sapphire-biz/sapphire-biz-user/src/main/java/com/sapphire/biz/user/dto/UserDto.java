package com.sapphire.biz.user.dto;

import com.sapphire.common.utils.dto.Dto;

/**
 * Author: Ethan <br/>
 * Date: 2015/11/8.<br/>
 * Email: byp5303628@hotmail.com
 */
public class UserDto implements Dto {
    private long   userId;
    private String userName;
    private String password;
    private String type;
    private String email;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    /**
     * Getter method for property <tt>type</tt>.
     *
     * @return property value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     *
     * @param type  value to be assigned to property type
     */
    public void setType(String type) {
        this.type = type;
    }
}
