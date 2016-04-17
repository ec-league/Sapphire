package com.sapphire;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/16<br/>
 * Email: byp5303628@hotmail.com
 */
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseTest {
   @Test
   public void baseTest() {

   }
}
