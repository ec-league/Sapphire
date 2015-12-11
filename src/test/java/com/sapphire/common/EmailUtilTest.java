package com.sapphire.common;

import org.apache.commons.mail.EmailException;
import org.testng.annotations.Test;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/10<br/>
 * Email: byp5303628@hotmail.com
 */
public class EmailUtilTest {
   @Test
   public void test() throws EmailException {
      EmailUtil.sendEmail(new EmailUtil.EmailBuilder()
            .setEmail("136689664@qq.com").setContent("Ethan is God")
            .setTitle("Ethan"));
   }


}
