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
      //������������
      email.setHostName("stmp.hotmail.com");
      //�����û���������
      email.setAuthentication("byp5303628", "mylove3315");
      //�����ַ����뷽ʽ
      email.setCharset("GB2312");
      try {
         //���÷���Դ��ַ
         email.setFrom("cc@hotmail.com");
         //����Ŀ���ַ
         email.addTo("136689664@qq.com");
         //��������
         email.setSubject("test");
         //�����ʼ�����
         email.setMsg("test");
         //�����ʼ�
         email.send();
         result = "successful";
      } catch (Exception e) {
         e.printStackTrace();
         result = "not successful";
      }
      return result;
   }
}
