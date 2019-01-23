package com.devon.deweert.deweert_CardioBook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ViewHealthStats extends AppCompatActivity {

    private Intent intent;
    private MyHealthStats myHealthStats;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_health_stats);
        intent  = getIntent();
        getAllData();

        setTextFields();
    }

    private void getAllData(){
        Bundle dataBundle = intent.getExtras();
        if(dataBundle != null){
            breakupData(dataBundle);
        }

    }

    private void breakupData(Bundle dataBundle){
        String healthStatsToView = dataBundle.getString(MyHealthStats.HEALTH_DATA);
        position = dataBundle.getInt(MyHealthStats.INT_DATA);
        Type type = new TypeToken<MyHealthStats>() {}.getType();
        myHealthStats = new Gson().fromJson(healthStatsToView, type);
    }

    private void setTextFields(){
        String dateMade = myHealthStats.getDateMeasured() + " " + myHealthStats.getTimeMeasured();
        int heartRate = myHealthStats.getHeartRate();
        int diastolic = myHealthStats.getDiastolicPressure();
        int systolic = myHealthStats.getSystolicPressure();
        String comment = myHealthStats.getComment();
        ((TextView)findViewById(R.id.DisplayDateMeasured)).setText(dateMade);
        ((TextView)findViewById(R.id.EditHeartRateTextField)).setText(String.valueOf(heartRate));
        ((TextView)findViewById(R.id.EditDiastolicPressureTextField)).setText(String.valueOf(diastolic));
        ((TextView)findViewById(R.id.EditSystolicPressureTextField)).setText(String.valueOf(systolic));
        ((TextView)findViewById(R.id.EditCommentTextField)).setText(comment);
    }
}
