package com.devon.deweert.cmput301_1;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyHealthStats {
    public static String HEALTH_STAT_ID_EXTRA = "HEALTH_STAT_ID_EXTRA";

    private transient SimpleDateFormat myDateFormat= new SimpleDateFormat("yyyy-MM-dd");
    private transient SimpleDateFormat myTimeFormat = new SimpleDateFormat("hh:mm");

    private String dateMeasured;
    private String timeMeasured;

    private Integer systolicPressure;
    private Integer diastolicPressure;
    private Integer heartRate;

    private String comment;

    public static int DATA_KEY = 99;
    public static String DATA_STRING = "GSON";

    
    public MyHealthStats( Integer first, Integer second, Integer third, String fourth){
        dateMeasured = myDateFormat.format(new Date());
        timeMeasured = myTimeFormat.format(new Date());
        heartRate = first;
        diastolicPressure = second;
        systolicPressure = third;
        comment = fourth;
    }

    public MyHealthStats( Integer first, Integer second, Integer third){
        dateMeasured = myDateFormat.format(new Date());
        timeMeasured = myTimeFormat.format(new Date());
        heartRate = first;
        diastolicPressure = second;
        systolicPressure = third;
        comment = "";
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
