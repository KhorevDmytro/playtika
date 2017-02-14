package com.playtika.suites;

import com.playtika.common.Xmlparser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.runners.Parameterized.Parameters;


/**
 * Created by KDAMAC on 12.02.17.
 */
@RunWith(Parameterized.class)
public class Runner {

    private String classPar;
    private String methodPar;

    public Runner(ArrayList<String> classAndMethod) {
        classPar = classAndMethod.get(0);
        methodPar = classAndMethod.get(1);
    }

    @Parameters
    public static ArrayList<ArrayList> data() throws ClassNotFoundException, DocumentException {
        return new Xmlparser().parser();
    }

    @Test
    public void testsRunner() throws ClassNotFoundException {
        JUnitCore junit = new JUnitCore();
        Request req = Request.method(Class.forName(classPar), methodPar);
        Result result = junit.run(req);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        Assert.assertTrue(result.wasSuccessful());
    }

}


