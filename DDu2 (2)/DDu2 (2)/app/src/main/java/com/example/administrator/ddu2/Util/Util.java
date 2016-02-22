package com.example.administrator.ddu2.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016-02-20.
 */
public class Util {
    public static String getCurrentDate(){
        String today = "";
        Date date = new Date();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMdd");
        today = dayTime.format(date);

        return today;
    }
}
