package com.playtika.suites;

import com.playtika.common.Xmlparser;
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
@Suite.SuiteClasses({Xmlparser.class})
public class Regression {
}