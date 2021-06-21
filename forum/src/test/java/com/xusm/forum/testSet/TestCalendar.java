package com.xusm.forum.testSet;

import com.xusm.forum.utils.CookieDeathUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestCalendar {

    public static void main(String[] args) {
        long now = Calendar.getInstance().getTimeInMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        long death = calendar.getTimeInMillis();
        int cookieMaxAge = (int) ((death-now)/(1000*60));
        System.out.println(cookieMaxAge);

        System.out.println(CookieDeathUtils.getCookieDeath());
    }

//    public static void main(String[] args) {
//        long now = Calendar.getInstance().getTimeInMillis();
//        long last = now - (60 * 1000 * 60 * 24) * 20;
//        Date date = new Date(last);
//        String format = new SimpleDateFormat("MM-dd").format(date);
//        System.out.println(format);
//    }
}
