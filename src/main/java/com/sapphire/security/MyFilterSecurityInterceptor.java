package com.sapphire.security;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/18<br/>
 * Email: byp5303628@hotmail.com
 */
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
   //�����ļ�ע��
   private FilterInvocationSecurityMetadataSource securityMetadataSource;

   //��½��ÿ�η�����Դ��ͨ���������������
   public void doFilter(ServletRequest request, ServletResponse response,
         FilterChain chain) throws IOException, ServletException {
      FilterInvocation fi = new FilterInvocation(request, response, chain);
      invoke(fi);
   }

   public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
      return this.securityMetadataSource;
   }

   public Class<? extends Object> getSecureObjectClass() {
      return FilterInvocation.class;
   }

   public void invoke(FilterInvocation fi) throws IOException, ServletException {
      //fi������һ�������ص�url
      //�������MyInvocationSecurityMetadataSource��getAttributes(Object object)���������ȡfi��Ӧ������Ȩ��
      //�ٵ���MyAccessDecisionManager��decide������У���û���Ȩ���Ƿ��㹻
      InterceptorStatusToken token = super.beforeInvocation(fi);
      try {
         //ִ����һ��������
         fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
      } finally {
         super.afterInvocation(token, null);
      }
   }

   public SecurityMetadataSource obtainSecurityMetadataSource() {
      return this.securityMetadataSource;
   }

   public void setSecurityMetadataSource(
         FilterInvocationSecurityMetadataSource newSource) {
      this.securityMetadataSource = newSource;
   }

   public void destroy() {

   }

   public void init(FilterConfig arg0) throws ServletException {

   }
}
