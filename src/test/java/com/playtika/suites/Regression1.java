package com.playtika.suites;

import com.playtika.common.Category1;
import com.playtika.tests.Test1;
import com.playtika.tests.Test2;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by KDAMAC on 02.02.17.
 */


@RunWith(Categories.class)
@Categories.IncludeCategory(Category1.class)
@Suite.SuiteClasses({Test1.class, Test2.class})
public class Regression1 {
}