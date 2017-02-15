package com.playtika.common;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;


/**
 * Created by KDAMAC on 12.02.17.
 */
public class Xmlparser {

    //private String xmlFile = "Regression1.xml";
    private String xmlFile = System.getProperty("suite");
    private ArrayList<ArrayList> classesAndMethodsMap = new ArrayList<ArrayList>();

    public ArrayList<ArrayList> parser() throws DocumentException, ClassNotFoundException {
        File f = new File("");
        File xml = new File(f.getAbsolutePath().toString()+"/src/test/java/com/playtika/suites/"+xmlFile);
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
                    for (Method met : testClass.getDeclaredMethods()){
                        for (Annotation ann : met.getDeclaredAnnotations()){
                            if (ann.annotationType().getName().equals("org.junit.Test")){
                                ArrayList<String> arr1 = new ArrayList<String>();
                                arr1.add(testClass.getName());
                                arr1.add(met.getName());
                                classesAndMethodsMap.add(arr1);
                            }
                        }
                    }
                } else {
                    for (String methodClass : methodsClasses) {
                        ArrayList<String> arr2 = new ArrayList<String>();
                        arr2.add(testClass.getName());
                        arr2.add(methodClass);
                        classesAndMethodsMap.add(arr2);
                    }

                }
            }
        }
        return classesAndMethodsMap;
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