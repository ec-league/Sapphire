package com.sapphire.common;

import org.apache.commons.mail.SimpleEmail;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/10<br/>
 * Email: byp5303628@hotmail.com
 */
public class EmailUtil {
   public static String send() {
      String result = null;
      SimpleEmail email = new SimpleEmail();
      //设置主机名称
      email.setHostName("stmp.hotmail.com");
      //设置用户名，密码
      email.setAuthentication("byp5303628", "mylove3315");
      //设置字符编码方式
      email.setCharset("GB2312");
      try {
         //设置发送源地址
         email.setFrom("cc@hotmail.com");
         //设置目标地址
         email.addTo("136689664@qq.com");
         //设置主题
         email.setSubject("test");
         //设置邮件内容
         email.setMsg("test");
         //发送邮件
         email.send();
         result = "successful";
      } catch (Exception e) {
         e.printStackTrace();
         result = "not successful";
      }
      return result;
   }
}
