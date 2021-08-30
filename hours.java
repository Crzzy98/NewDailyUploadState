package com.don.newdaily;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class hours extends AppCompatActivity implements View.OnClickListener{

    public TextView input;
    public static String text;
    public Button enter;
//update VARs
    static int inputTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours);
        enter = (Button)findViewById(R.id.hoursEnter);
        enter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        input = (TextView) findViewById(R.id.userHours);
        text = "" + input.getText();//getting input hours

        if (text != null ) {
            if (TasksOrNormal.passed) {//user chose to set alarms
                String temp = ("" + input.getText()).trim();
                inputTime = Integer.parseInt(temp);

                if (inputTime > 0)
                    startActivity(new Intent(hours.this, FinalAct.class));
            }
            else{
                input.setText(R.string.hoursToContinue);
            }
        }else{
            input.setText(R.string.hoursToContinue);
        }
    }

}


