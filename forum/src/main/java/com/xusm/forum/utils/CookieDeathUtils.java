package com.xusm.forum.utils;

import java.util.Calendar;

public class CookieDeathUtils {

    public static int getCookieDeath(){
        long now = Calendar.getInstance().getTimeInMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        long death = calendar.getTimeInMillis();
        int cookieMaxAge = (int) ((death-now)/(1000*60));

        return cookieMaxAge;
    }
}
