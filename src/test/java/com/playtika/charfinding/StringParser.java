package com.playtika.stringtask;

/**
 * Created by KDAMAC on 20.02.17.
 */
public class Test {

    public static void main(String [] args) {
        String a = test("aaggTdbsjjsoadd");
        System.out.println(a.charAt(0));
    }

    public static String test(String st) {
        String str = st;
        for (int i = 0; i < str.length(); i++){
            for (int j = i+1; j < str.length(); j++){
                if (str.charAt(i) == str.charAt(j)){
                    char ch = str.charAt(i);
                    str = str.replaceAll(String.valueOf(ch), "");
                    return test(str);
                }
            }
        }
        return str;
    }
}