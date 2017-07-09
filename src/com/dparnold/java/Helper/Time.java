package com.dparnold.java.Helper;

/**
 * Created by dominik on 7/9/17.
 */
public class Time {
    public static long unixTime (){
        return (System.currentTimeMillis() / 1000L);
    }
}
