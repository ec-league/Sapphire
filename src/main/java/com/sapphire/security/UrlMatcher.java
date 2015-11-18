package com.sapphire.security;

/**
 * Author: EthanPark <br/>
 * Date: 2015/11/18<br/>
 * Email: byp5303628@hotmail.com
 */
public interface UrlMatcher {
   Object compile(String paramString);
   boolean pathMatchesUrl(Object paramObject, String paramString);
   String getUniversalMatchPattern();
   boolean requiresLowerCaseUrl();
}
