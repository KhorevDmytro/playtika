package com.playtika.suites;

import com.playtika.tests.Test1;
import com.playtika.tests.Test2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;


/**
 * Created by KDAMAC on 02.02.17.
 */


@RunWith(Suite.class)
@Suite.SuiteClasses({Regression3.FilteredTest1.class, Test2.class})
public class Regression3 {

    public static class FilteredTest1 {
        @Test
        public void testMethod1() {

        JUnitCore junit = new JUnitCore();
        Request req = Request.method(Test1.class, "testMethod1");
        Result result = junit.run(req);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        Assert.assertTrue(result.wasSuccessful());
        }
    }
}