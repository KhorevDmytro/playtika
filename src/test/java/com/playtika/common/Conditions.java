package com.playtika.common;

/**
 * Created by KDAMAC on 03.02.17.
 */
public class Conditions {
    private static Boolean isRun = true;

    public static void setStatus(boolean status) {
        isRun = status;
    }
    public static boolean getStatus() {
        return isRun;
    }
}
