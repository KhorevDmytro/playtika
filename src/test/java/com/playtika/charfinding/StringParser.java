package com.playtika.charfinding;

/**
 * Created by KDAMAC on 20.02.17.
 */
public class StringParser {

    public static void main(String [] args) {
        String uniqChars = findChar("aaggTdbsjjsoadd");
        System.out.println(uniqChars.charAt(0));
    }

    public static String findChar(String st) {
        String str = st;
        for (int i = 0; i < str.length(); i++){
            for (int j = i+1; j < str.length(); j++){
                if (str.charAt(i) == str.charAt(j)){
                    char ch = str.charAt(i);
                    str = str.replaceAll(String.valueOf(ch), "");
                    return findChar(str);
                }
            }
        }
        return str;
    }
}