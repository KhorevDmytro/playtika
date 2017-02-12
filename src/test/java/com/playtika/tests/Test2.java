package com.playtika.tests;

import com.playtika.pages.Page2;
import org.junit.*;


/**
 * Created by KDAMAC on 02.02.17.
 */
public class Test2 {

    protected static Page2 page2;

    @BeforeClass
    public static void init() {
        page2 = new Page2();
    }

    @Test
    public void testMethod3() {
        System.out.println("testMethod3 " + page2.plus(2,5, 3));
    }

    @Test
    public void testMethod4() {
        System.out.println("testMethod4 " + page2.divAndPlus(8,1,3));
    }
}