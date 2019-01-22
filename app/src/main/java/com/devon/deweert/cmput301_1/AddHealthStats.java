package com.devon.deweert.cmput301_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddHealthStats extends AppCompatActivity {

    private Button addHealthStatsButton;
    private EditText heartRateEditText;
    private EditText diastolicEditText;
    private EditText systolicEditText;
    private EditText commentEditText;
    private boolean DATA_READY_FLAG = false;
    private ArrayList<MyHealthStats> myNewHealthStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health_stats);

        addHealthStatsButton = (Button) findViewById(R.id.SubmitHealthStats);
        heartRateEditText = (EditText) findViewById(R.id.HeartRateTextField);
        diastolicEditText = (EditText) findViewById(R.id.DiastolicPressureTextField);
        systolicEditText = (EditText) findViewById(R.id.SystolicPressureTextField);
        commentEditText = (EditText) findViewById(R.id.CommentTextField);
        heartRateEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        diastolicEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        systolicEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        myNewHealthStats = new ArrayList<MyHealthStats>();

        addHealthStatsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkHealthStatsData();
            }
        });
    }

    public void checkHealthStatsData(){
        String toShowInToast = "Data is not valid";
        Integer heartRate = tryParseInt(heartRateEditText.getText().toString());
        Integer diastolic = tryParseInt(diastolicEditText.getText().toString());
        Integer systolic = tryParseInt(systolicEditText.getText().toString());
        String comment = commentEditText.getText().toString();
        if(heartRate > 0 && diastolic > 0 && systolic > 0){
            toShowInToast = "Data entered into system";
            heartRateEditText.setText("");
            systolicEditText.setText("");
            diastolicEditText.setText("");
            commentEditText.setText("");
            DATA_READY_FLAG = true;
            myNewHealthStats.add(new MyHealthStats(heartRate, diastolic, systolic, comment));


        }
        Toast toast = Toast.makeText(this, toShowInToast, Toast.LENGTH_LONG);
        toast.show();
    }

    private Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch(NumberFormatException nfe) {
            nfe.printStackTrace();
            return 0;
        }
    }

    @Override
    public void onBackPressed(){
        if(DATA_READY_FLAG) {
            Intent intent = new Intent();
            String myHealthDataAsJson = new Gson().toJson(myNewHealthStats);
            intent.putExtra(MyHealthStats.DATA_STRING,  myHealthDataAsJson);
            setResult(RESULT_OK, intent);

        }else{
            setResult(RESULT_CANCELED);
        }
        finish();
    }
}
