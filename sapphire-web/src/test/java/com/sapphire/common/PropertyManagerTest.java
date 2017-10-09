package com.sapphire.common;

import com.sapphire.common.utils.PropertyManager;
import com.sapphire.common.utils.exception.PropertyNotFoundException;
import org.junit.Before;
import org.junit.Test;


/**
 * Author: EthanPark <br/>
 * Date: 2015/12/14<br/>
 * Email: byp5303628@hotmail.com
 */
public class PropertyManagerTest {
   @Before
   public void init() {
      PropertyManager.load(PropertyManagerTest.class);
   }

   @Test(expected = PropertyNotFoundException.class)
   public void test() {
      PropertyManager.getProperty("ABC");
   }
}
