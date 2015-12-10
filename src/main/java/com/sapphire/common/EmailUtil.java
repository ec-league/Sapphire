package com.sapphire.common;


import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/10<br/>
 * Email: byp5303628@hotmail.com
 */
public class EmailUtil {
   public static void sendEmail(EmailBuilder builder) throws EmailException {
      HtmlEmail email = new HtmlEmail();
      email.setHostName("smtp.sina.com");
      email.setAuthentication("ethanpark", "mylove3315");

      try {
         //���÷���Դ��ַ
         email.setFrom("ethanpark@sina.com", "CP4");
         //����Ŀ���ַ
         email.addTo(builder.getEmail());
         //��������
         email.setSubject(builder.getTitle());
         //�����ʼ�����
         email.setMsg(builder.getContent());
         //�����ʼ�
         email.send();
      } catch (Exception e) {
         throw new EmailException(e);
      }
   }

   public static class EmailBuilder {
      private String email;
      private String title;
      private String content;

      public String getEmail() {
         return email;
      }

      public EmailBuilder setEmail(String email) {
         this.email = email;
         return this;
      }

      public String getTitle() {
         return title;
      }

      public EmailBuilder setTitle(String title) {
         this.title = title;
         return this;
      }

      public String getContent() {
         return content;
      }

      public EmailBuilder setContent(String content) {
         this.content = content;
         return this;
      }
   }
}
