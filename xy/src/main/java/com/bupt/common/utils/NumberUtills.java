package com.bupt.common.utils;

import java.util.Date;

/**
 * Created by mengying on 2017/9/26.
 */
public class NumberUtills {

    public static String getNumber() {
        String seqID = "";
        seqID+= getNO();
        seqID += String.valueOf(RandomI(0, 9));
        seqID += String.valueOf(RandomI(0, 9));
        return seqID;
    }
    public static String getNumber2() {
        String seqID = "";
        seqID += String.valueOf(RandomI(0, 9));
        seqID += String.valueOf(RandomI(0, 9));
        seqID += String.valueOf(RandomI(0, 9));
        seqID += String.valueOf(RandomI(0, 9));
        seqID += String.valueOf(RandomI(0, 9));
        seqID += String.valueOf(RandomI(0, 9));
        seqID += String.valueOf(RandomI(0, 9));
        seqID += String.valueOf(RandomI(0, 9));
        return seqID;
    }
    public static int RandomI(int m, int n) {
        double j = Math.round(Math.random() * (n - m));
        return (int) (j + m);
    }

    public static String getNO() {
        Long date = new Date().getTime();
        String time = date.toString();
        return time;
    }






}
