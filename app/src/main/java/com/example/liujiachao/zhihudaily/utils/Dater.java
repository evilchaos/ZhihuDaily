package com.example.liujiachao.zhihudaily.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * deals with string work like copy, parse.
 */
public class Dater {

    public static final String LAST_DATE = "lastDate";

    /**
     * Parse"yyyyMMdd" to "yyyy-MM-dd"
     *
     * @param date "yyyyMMdd" String date
     * @return "yyyy-MM-dd" for display
     */
    public static String getDisplayDate(String date) {
        Date d = parseStandardDate(date);
        return getDisplayDate(d);

    }

    /**
     * Get date String to display
     *
     * @param date Date object
     * @return "yyyy-MM-dd" for display
     */
    public static String getDisplayDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return formatter.format(date);
    }

    /**
     * Parse Date to String
     *
     * @param date Date object
     * @return "yyyyMMdd" String
     */
    public static String parseStandardDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        formatter.setLenient(false);
        return formatter.format(date);
    }

    /**
     * Parse "yyyyMMdd" to Date
     *
     * @param date "yyyyMMdd" String
     * @return Date object
     */
    public static Date parseStandardDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        formatter.setLenient(false);
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static Date lastDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        return c.getTime();
    }

    public static String lastDay(String date) {
        Date d = Dater.parseStandardDate(date);
        return parseStandardDate(lastDay(d));
    }

    public static String parseTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm",Locale.getDefault());
        Date date = new Date();
        date.setTime(time);
        return format.format(date);
    }

    public static String getNewsLabel(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String today = format.format(new Date());
        if (date.equals(today)) {
            return "今日热闻";
        } else {
            SimpleDateFormat format2 = new SimpleDateFormat("MM月dd日", Locale.getDefault());
            try {
                Date then = format.parse(date);
                String result = format2.format(then);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(then);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                switch (dayOfWeek) {
                    case Calendar.SUNDAY:
                        result += " 星期日";
                        break;
                    case Calendar.MONDAY:
                        result += " 星期一";
                        break;
                    case Calendar.TUESDAY:
                        result += " 星期二";
                        break;
                    case Calendar.WEDNESDAY:
                        result += " 星期三";
                        break;
                    case Calendar.THURSDAY:
                        result += " 星期四";
                        break;
                    case Calendar.FRIDAY:
                        result += " 星期五";
                        break;
                    case Calendar.SATURDAY:
                        result += " 星期六";
                        break;
                }
                return result;
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
        }
    }

}
