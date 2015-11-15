package com.stevezeidner.cabinlife.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by szeidner on 14/11/2015.
 */
public class Utility {

    public static String getTimeDifference(String pDate) {
        int diffInDays = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("EST"));
        Calendar c = Calendar.getInstance();
        String formattedDate = format.format(c.getTime());

        Date d1 = null;
        Date d2 = null;
        try {

            d1 = format.parse(formattedDate);
            d2 = format.parse(pDate);
            long diff = d1.getTime() - d2.getTime();

            diffInDays = (int) (diff / (1000 * 60 * 60 * 24));
            if (diffInDays > 0) {
                if (diffInDays == 1) {
                    return diffInDays + " day ago";
                } else {
                    return diffInDays + " days ago";
                }
            } else {
                int diffHours = (int) (diff / (60 * 60 * 1000));
                if (diffHours > 0) {
                    if (diffHours == 1) {
                        return diffHours + " hr ago";
                    } else {
                        return diffHours + " hrs ago";
                    }
                } else {

                    int diffMinutes = (int) ((diff / (60 * 1000) % 60));
                    if (diffMinutes == 1) {
                        return diffMinutes + " min ago";
                    } else {
                        return diffMinutes + " mins ago";
                    }

                }
            }

        } catch (ParseException e) {
            // System.out.println("Err: " + e);
            e.printStackTrace();
        }
        return "";
    }
}
