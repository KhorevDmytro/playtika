package com.playtika.tests;

import com.playtika.common.Category1;
import com.playtika.common.Conditions;
import com.playtika.pages.Page1;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Created by KDAMAC on 02.02.17.
 */

public class Test1 {

    protected static Page1 page1;

    @BeforeClass
    public static void init() {
        page1 = new Page1();
    }

    @Category(Category1.class)
    @Test
    public void testMethod1() {
        System.out.println("testMethod1 " + page1.add(2,5));
    }

    @Test
    public void testMethod2() {
        Assume.assumeTrue(Conditions.getStatus());
        System.out.println("testMethod2 " + page1.div(8,2));
    }
}