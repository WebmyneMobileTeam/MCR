package com.webmyne.myclassroom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Android on 12-02-2015.
 */
public class DateUtilities {
    
    public static String formatString = "dd-MM-yyyy hh:mm a";
    
    public static Date parse(String string){
        Date date = null;
        try {
             date = new SimpleDateFormat(formatString).parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
    public static String format(Date date){
        String string = null;
        try {
            string = new SimpleDateFormat(formatString).format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }      
        return string;   
    }
    
    public static double difference(long start_time,long end_time){
        double difference = (end_time - start_time);
        return  difference;
    }
}
