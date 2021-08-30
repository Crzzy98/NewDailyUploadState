package com.don.newdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class Normal extends AppCompatActivity {

    private NormalDay day = new NormalDay(4);          //HOURS CALCULATED HERE
    protected String act1, act2, act3, act4;
    protected TextView v1, v2, v3, v4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        FinalAct.firstTime = FinalAct.setTime(day.getT(1), day.getT(11));
        FinalAct.secondTime = FinalAct.setTime(day.getT(2), day.getT(22));
        FinalAct.thirdTime = FinalAct.setTime(day.getT(3), day.getT(33));
        FinalAct.fourthTime = FinalAct.setTime(day.getT(4), day.getT(44));

        act1 = "Time you have for WORK is " + day.getT(1) + " hours and " + day.getT(11) + " minutes";
        act2 = "Time you have for ART is " + day.getT(2) + " hours and " + day.getT(22) + " minutes";
        act3 = "Time you have for LEISURE is " + day.getT(3) + " hours and " + day.getT(33) + " minutes";
        act4 = "Time you have for DAILY PLANNING is " + day.getT(4) + " hours and " + day.getT(44) + " minutes";

        // MAKE 4 TEXTFIELDS SEt TEXT TO ACTS VARS

        v1 = (TextView)findViewById(R.id.v1);
        v2 = (TextView)findViewById(R.id.v2);
        v3 = (TextView)findViewById(R.id.v3);
        v4 = (TextView)findViewById(R.id.v4);

        v1.setText(act1);
        v2.setText(act2);
        v3.setText(act3);
        v4.setText(act4);

        if(TasksOrNormal.passed){
            AlarmService.startAlarms(0);
        }
    }
    private void startAlarms(){

        AlarmManager alarm =  (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, FirstAlarm.class);
        PendingIntent alarmIntent = PendingIntent.getActivity(this, 0, intent, 0);

        assert alarm != null;
        alarm.setExact(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + FinalAct.setTime(day.getT(1), day.getT(11)) , alarmIntent); //SystemClock.elapsedRealtime() might need to be used******

        Time time;
        time = new Time(System.currentTimeMillis() + FinalAct.setTime(day.getT(1), day.getT(11)));
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");

        //TOAST MESSAGE DISPLAYED HERE
        String text = "First alarm set for " + formatter.format(time);
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
        toast.show();

    }
}
