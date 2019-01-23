package com.devon.deweert.deweert_CardioBook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ViewHealthStats extends AppCompatActivity {

    private Intent intent;
    private MyHealthStats myHealthStats;
    private int position;
    private boolean DATA_READY_FLAG = false;
    private EditText heartRateEditText;
    private EditText diastolicEditText;
    private EditText systolicEditText;
    private EditText commentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_health_stats);

        Button HealthStatsEditButton = (Button) findViewById(R.id.SubmitHealthStatsEdit);
        heartRateEditText = (EditText) findViewById(R.id.EditHeartRateTextField);
        diastolicEditText = (EditText) findViewById(R.id.EditDiastolicPressureTextField);
        systolicEditText = (EditText) findViewById(R.id.EditSystolicPressureTextField);
        commentEditText = (EditText) findViewById(R.id.EditCommentTextField);
        heartRateEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        diastolicEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        systolicEditText.setInputType(InputType.TYPE_CLASS_NUMBER);

        intent  = getIntent();
        getAllData();

        setTextFields();

        HealthStatsEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkHealthStatsData();
            }
        });
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
        heartRateEditText.setText(String.valueOf(heartRate));
        diastolicEditText.setText(String.valueOf(diastolic));
        systolicEditText.setText(String.valueOf(systolic));
        commentEditText.setText(comment);
    }

    public void checkHealthStatsData(){
        String toShowInToast = "Data is not valid";
        Integer heartRate = tryParseInt(heartRateEditText.getText().toString());
        Integer diastolic = tryParseInt(diastolicEditText.getText().toString());
        Integer systolic = tryParseInt(systolicEditText.getText().toString());
        String comment = commentEditText.getText().toString();
        if(heartRate > 0 && diastolic > 0 && systolic > 0){
            toShowInToast = "Data has been edited";
            DATA_READY_FLAG = true;
            myHealthStats = (new MyHealthStats(heartRate, diastolic, systolic, comment));
            onBackPressed();

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
            String myHealthDataAsJson = new Gson().toJson(myHealthStats);
            intent.putExtra(MyHealthStats.HEALTH_DATA,  myHealthDataAsJson);
            intent.putExtra(MyHealthStats.INT_DATA, position);
            setResult(RESULT_OK, intent);

        }else{
            setResult(RESULT_CANCELED);
        }
        finish();
    }
}
