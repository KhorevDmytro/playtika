package com.playtika.common;

import com.playtika.tests.Test1;
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
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.runners.Parameterized.*;


/**
 * Created by KDAMAC on 12.02.17.
 */
public class Xmlparser {

    private String xmlFile = System.getProperty("suite");

    @Test
    public void Xmlparser() throws DocumentException, ClassNotFoundException {
        File xml = new File("/Users/dmitrijhorev/Documents/Repo2/playtika/src/test/java/com/playtika/suites/"+xmlFile);
        SAXReader reader = new SAXReader();
        Document doc = reader.read(xml);
        Element suite = doc.getRootElement();
        String suiteName = suite.attributeValue("name");
        ArrayList<String> arrTests = parse(suite, suiteName);

        for (int t = 0; t < arrTests.size(); t++) {
            ArrayList<String> arrClasses = parse(suite, arrTests.get(t));

            for (int c = 0; c < arrClasses.size(); c++) {
                ArrayList<String> methodsClasses = parse(suite, arrClasses.get(c));

                Class testClass = Class.forName("com.playtika.tests." + arrClasses.get(c));
                if(methodsClasses.size() == 0) {
                    testsRunner(testClass);
                } else {
                    for (String methodClass : methodsClasses) {
                        testsRunner(testClass, methodClass);
                    }

                }
            }
        }
    }

    private void testsRunner(Class cl, String method) throws ClassNotFoundException {
        JUnitCore junit = new JUnitCore();
        Request req = Request.method(cl, method);
        Result result = junit.run(req);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        Assert.assertTrue(result.wasSuccessful());
    }

    private void testsRunner(Class cl){
        JUnitCore junit = new JUnitCore();
        Result result = junit.run(cl);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        Assert.assertTrue(result.wasSuccessful());
    }


    private ArrayList<String> parse(Element block, String nameParent) {
        ArrayList<String> arrNames = new ArrayList<String>();
        if (block.attributeValue("name") == nameParent) {
            for (int i = 0; i < block.elements().size(); i++) {
                Element element = (Element) block.elements().get(i);
                if (element.attributeValue("name") == null) {
                    for (int iter = 0; iter < element.elements().size(); iter++) {
                        Element elementChild = (Element) element.elements().get(iter);
                        arrNames.add(elementChild.attributeValue("name"));
                    }
                    return arrNames;
                } else {
                    arrNames.add(element.attributeValue("name"));
                }
            }
            return arrNames;
        } else if (block.elements().size() > 0) {
                ArrayList<ArrayList> ar = new ArrayList<ArrayList>();
                for (int iterator = 0; iterator < block.elements().size(); iterator++) {
                    ArrayList<String> result = parse((Element) block.elements().get(iterator), nameParent);
                    if (result != null){
                        return result;
                    }
                }
        }

        return null;
    }
}


