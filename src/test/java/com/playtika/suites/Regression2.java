package com.playtika.suites;

import com.playtika.common.Conditions;
import com.playtika.tests.Test1;
import com.playtika.tests.Test2;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by KDAMAC on 02.02.17.
 */


@RunWith(Suite.class)
@Suite.SuiteClasses({Test1.class, Test2.class})
public class Regression2 {
    @BeforeClass
    public static void IgnoreTest(){
        Conditions.setStatus(false);
    }
}



