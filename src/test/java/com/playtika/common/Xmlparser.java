package com.playtika.common;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by KDAMAC on 12.02.17.
 */
public class Xmlparser {

    @Test
    public void parser() throws DocumentException {
        File xml = new File("/Users/dmitrijhorev/Documents/Repo2/playtika/src/test/java/com/playtika/suites/Regression1.xml");
        SAXReader reader = new SAXReader();
        Document doc = reader.read(xml);
        Element suite = doc.getRootElement();
        String suiteName = suite.attributeValue("name");

        

        ArrayList<String> arrTests = parse(suite, suiteName);
        System.out.println(arrTests);
        ArrayList<String> arrClasses = parse(suite, arrTests.get(0));
        System.out.println(arrClasses);
        ArrayList<String> methodsClasses = parse(suite, arrClasses.get(1));
        System.out.println(methodsClasses);
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


