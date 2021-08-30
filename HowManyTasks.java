package com.don.newdaily;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.ArrayList;
import java.util.List;

public class HowManyTasks extends AppCompatActivity {
    private Button enterBtn;
    private TextView howManyTasks;
    private TextView howManyTasksHeader;
    private String taskHolder;
    public static int taskAmount;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_many_tasks);
//GET TASKS AND LOOP APPLY THEM TO ARRAY AND ITERATE A VAR TO GET AMT FOR LATER USE
        adView =(AdView) findViewById(R.id.adView1);
        howManyTasks = findViewById(R.id.userTaskAmount);
        enterBtn = findViewById(R.id.hoursEnter);
        howManyTasksHeader = findViewById(R.id.howManyTasksHeader);

        List<String> testDevices = new ArrayList<>();
        testDevices.add(AdRequest.DEVICE_ID_EMULATOR);
        RequestConfiguration requestConfiguration
                = new RequestConfiguration.Builder()
                .setTestDeviceIds(testDevices)
                .build();
        MobileAds.setRequestConfiguration(requestConfiguration);

        AdRequest adRequest = new AdRequest.Builder()
//               .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskHolder = "" + howManyTasks.getText();//getting task amt
                if(taskHolder.length() > 0) {
                    taskAmount = Integer.parseInt(taskHolder);
                    startActivity(new Intent(HowManyTasks.this, EnterTasks2.class));
                }else{
                    howManyTasksHeader.setText("Please enter the amount of tasks you wish to complete in descending order of importance(up to four tasks)");
                }
            }
        });
    }
}