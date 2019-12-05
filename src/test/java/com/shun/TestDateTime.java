package com.shun;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestDateTime {
    @Test
    public void testTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY,23);
        System.out.println(calendar.getTime().getTime()-calendar1.getTime().getTime());
        String format = simpleDateFormat.format(calendar.getTime());
        System.out.println(format);
    }
}
