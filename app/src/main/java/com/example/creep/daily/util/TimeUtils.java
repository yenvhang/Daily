package com.example.creep.daily.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by creep on 2016/9/8.
 */

public class TimeUtils {
    public static String getTimeByMills(String mills){

        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(Long.parseLong(mills)*1000);

    }
    public static int addOneDay(String sDate){
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date  =simpleDateFormat.parse(sDate);
            Calendar calendar =Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH,1);
            return (int) (calendar.getTime().getTime()/1000);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int substractOne(String sDate){


        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");


        try {
            Date date = simpleDateFormat.parse(sDate);
            Calendar calendar =Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH,-1);
            return (int) (calendar.getTime().getTime()/1000);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }
}
