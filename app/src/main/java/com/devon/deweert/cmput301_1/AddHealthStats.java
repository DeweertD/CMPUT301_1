package com.devon.deweert.cmput301_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddHealthStats extends AppCompatActivity {

    private Button addHealthStatsButton;
    private EditText heartRateEditText;
    private EditText diastolicEditText;
    private EditText systolicEditText;
    private EditText commentEditText;

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

        addHealthStatsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkHealthStatsData();
            }
        });
    }

    public void checkHealthStatsData(){
        String toShowinToast = "Data is not valid";
        Integer heartRate = tryParseInt(heartRateEditText.getText().toString());
        Integer diastolic = tryParseInt(diastolicEditText.getText().toString());
        Integer systolic = tryParseInt(systolicEditText.getText().toString());
        String comment = commentEditText.getText().toString();
        if(heartRate > 0 && diastolic > 0 && systolic > 0){
            toShowinToast = "Data entered into system";
            heartRateEditText.setText("");
            systolicEditText.setText("");
            diastolicEditText.setText("");
            commentEditText.setText("");
        }
        Toast toast = Toast.makeText(this, toShowinToast, Toast.LENGTH_SHORT);
        toast.show();
    }

    private Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch(NumberFormatException nfe) {
            // Log exception.
            return 0;
        }
    }
}
