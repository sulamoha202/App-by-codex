package com.example.simpleaccountant.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtils {
    private DateUtils() {}

    public static String today() {
        return new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault()).format(new Date());
    }

    public static String now() {
        return new SimpleDateFormat(Constants.DATE_TIME_FORMAT, Locale.getDefault()).format(new Date());
    }

    public static String[] thisMonthRange() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String start = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault()).format(calendar.getTime());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String end = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault()).format(calendar.getTime());
        return new String[]{start, end};
    }
}
