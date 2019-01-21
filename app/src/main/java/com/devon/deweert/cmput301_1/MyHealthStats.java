package com.devon.deweert.cmput301_1;


import java.text.SimpleDateFormat;
import java.util.Date;

public class MyHealthStats {
    public static String HEALTH_STAT_ID_EXTRA = "HEALTH_STAT_ID_EXTRA";
    private Date dateMeasured;
    private SimpleDateFormat myDateFormat= new SimpleDateFormat("yyyy-MM-dd");
    private Date timeMeasured;
    private  SimpleDateFormat myTimeFormat = new SimpleDateFormat("hh:mm");
    private Integer systolicPressure;
    private Integer diastolicPressure;
    private Integer heartRate;
    private String comment;
    
    public MyHealthStats(){
        dateMeasured = new Date();
    }

    public MyHealthStats(MyHealthStats obj){
        dateMeasured = obj.getDateMeasured();
        timeMeasured = obj.getTimeMeasured();
        systolicPressure = obj.getSystolicPressure();
        diastolicPressure = obj.getDiastolicPressure();
        heartRate = obj.getHeartRate();
        comment = obj.getComment();
    }
    
    public MyHealthStats(String first){

    }
    
    public MyHealthStats(String first, String second){

    }
    
    public MyHealthStats(String first, String second, String third){

    }
    
    public MyHealthStats(String first, String second, String third, String fourth){

    }
    
    public MyHealthStats(String first, String second, String third, String fourth, String fifth){

    }

    public Date getDateMeasured() {
        return dateMeasured;
    }

    public Date getTimeMeasured() {
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

    public String getMyDateFormat() {
        return myDateFormat.format(dateMeasured);
    }

    public String getMyTimeFormat(){
        return myTimeFormat.format(timeMeasured);
    }

    public String getComment() {
        return comment;
    }

}
