package com.devon.deweert.deweert_CardioBook;


import java.text.SimpleDateFormat;
import java.util.Date;

public class MyHealthStats {
    //public static String HEALTH_STAT_ID_EXTRA = "HEALTH_STAT_ID_EXTRA";

    private transient SimpleDateFormat myDateFormat= new SimpleDateFormat("yyyy-MM-dd");
    private transient SimpleDateFormat myTimeFormat = new SimpleDateFormat("hh:mm");

    private String dateMeasured;
    private String timeMeasured;

    private Integer systolicPressure;
    private Integer diastolicPressure;
    private Integer heartRate;

    private String comment;

    public static int ADD_KEY = 99;
    public static int VIEW_KEY = 66;
    public static String HEALTH_DATA = "HEALTH";
    public static String INT_DATA = "INT";
    public static final String FILENAME = "MyHealthStats.sav";

    
    public MyHealthStats( Integer first, Integer second, Integer third, String fourth){
        dateMeasured = myDateFormat.format(new Date());
        timeMeasured = myTimeFormat.format(new Date());
        heartRate = new Integer(first);
        diastolicPressure = new Integer(second);
        systolicPressure = new Integer(third);
        comment = fourth;
    }


    public String getDateMeasured() {
        return dateMeasured;
    }

    public String getTimeMeasured() {
        return timeMeasured;
    }

    public Integer getDiastolicPressure() {
        return diastolicPressure;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public Integer getSystolicPressure() {
        return systolicPressure;
    }

    public SimpleDateFormat getMyDateFormat() {
        return myDateFormat;
    }

    public SimpleDateFormat getMyTimeFormat() {
        return myTimeFormat;
    }

    public String getComment() {
        return comment;
    }

}
