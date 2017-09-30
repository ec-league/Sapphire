package com.sapphire.security;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/17<br/>
 * Email: byp5303628@hotmail.com
 */
public class MyPasswordEncoder extends MessageDigestPasswordEncoder {
    public MyPasswordEncoder(String algorithm) {
        super(algorithm);
    }

    @Override
    public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
        return encPass.equals(DigestUtils.md5DigestAsHex(rawPass.getBytes()));
    }
}
