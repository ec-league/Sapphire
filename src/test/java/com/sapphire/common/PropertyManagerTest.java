package com.sapphire.common;

import com.sapphire.common.exception.PropertyNotFoundException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Author: EthanPark <br/>
 * Date: 2015/12/14<br/>
 * Email: byp5303628@hotmail.com
 */
public class PropertyManagerTest {
    @BeforeClass
    public void init() {
        PropertyManager.load(this.getClass());
    }

    @Test(expectedExceptions = PropertyNotFoundException.class)
    public void test() {
        PropertyManager.getProperty("ABC");
    }
}
