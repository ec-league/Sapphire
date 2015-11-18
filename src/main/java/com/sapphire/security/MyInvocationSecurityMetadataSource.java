package com.sapphire.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/18<br/>
 * Email: byp5303628@hotmail.com
 */
public class MyInvocationSecurityMetadataSource implements
      FilterInvocationSecurityMetadataSource {
   private UrlMatcher urlMatcher = new AntUrlPathMatcher();
   private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

   //tomcat����ʱʵ����һ��
   public MyInvocationSecurityMetadataSource() {
      loadResourceDefine();
   }

   //tomcat����ʱ����һ�Σ���������url��Ȩ�ޣ����ɫ���Ķ�Ӧ��ϵ
   private void loadResourceDefine() {
      resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
      Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
      ConfigAttribute ca = new SecurityConfig("ROLE_USER");
      atts.add(ca);
      resourceMap.put("/index.jsp", atts);
      Collection<ConfigAttribute> attsno = new ArrayList<ConfigAttribute>();
      ConfigAttribute cano = new SecurityConfig("ROLE_NO");
      attsno.add(cano);
      resourceMap.put(
            "/http://blog.csdn.net/u012367513/article/details/other.jsp",
            attsno);
   }

   //������Ҫ���ʵ�url���������url���ڵ�����Ȩ�ޣ����ɫ��
   public Collection<ConfigAttribute> getAttributes(Object object)
         throws IllegalArgumentException {
      // ������תΪurl
      String url = ((FilterInvocation) object).getRequestUrl();
      Iterator<String> ite = resourceMap.keySet().iterator();
      while (ite.hasNext()) {
         String resURL = ite.next();
         if (urlMatcher.pathMatchesUrl(resURL, url)) {
            return resourceMap.get(resURL);
         }
      }
      return null;
   }

   public boolean supports(Class<?> clazz) {
      return true;
   }

   public Collection<ConfigAttribute> getAllConfigAttributes() {
      return null;
   }
}
