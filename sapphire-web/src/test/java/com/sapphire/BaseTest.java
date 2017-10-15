package com.sapphire;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/16<br/>
 * Email: byp5303628@hotmail.com
 */
@ContextConfiguration(locations = { "classpath*:test/applicationContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {
    @Test
    public void baseTest() {

    }
}
