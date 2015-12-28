package com.sapphire.common;

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/28<br/>
 * Email: byp5303628@hotmail.com
 */
public class MarkDownUtil {
   public static String toHtml(String s) {
      PegDownProcessor pegDownProcessor = new PegDownProcessor(Extensions.ALL);
      return pegDownProcessor.markdownToHtml(s);
   }
}
