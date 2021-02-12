package com.xusm.forum.testSet;

import com.xusm.forum.utils.CookieDeathUtils;

import java.util.Calendar;

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
}
