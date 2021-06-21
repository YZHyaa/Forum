package com.xusm.forum.testSet;

import com.xusm.forum.dao.WebsiteDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDate {

    @Autowired
    private WebsiteDao websiteDao;

    public static void main(String[] args) {
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar cal = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.dd-HH");
        System.out.println(simpleDateFormat.format(new Date()));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        System.out.println(calendar.getTime());
        long timeInMillis = calendar.getTimeInMillis();
        System.out.println(timeInMillis);
        System.out.println(new Date(timeInMillis));
        System.out.println();

        long timeInMillis1 = Calendar.getInstance().getTimeInMillis();
        System.out.println(new Date(timeInMillis1));
        int min = (int) ((timeInMillis-timeInMillis1)/(1000*60));
        System.out.println(min);

//        System.out.println(Calendar.getInstance().getTimeInMillis());
//        System.out.println(new Date(Calendar.getInstance().getTimeInMillis()));


//        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
//        String format = simpleDateFormat1.format(new Date());
//        try {
//            Date date = simpleDateFormat1.parse(f);
//            Calendar c = Calendar.getInstance();
//            c.setTime(date);
//            long timeInMillis = c.getTimeInMillis();
//            c.setTime(new Date());
//            long t = c.getTimeInMillis();
//            System.out.println(timeInMillis-t);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }


    }
}
