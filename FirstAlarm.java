package com.don.newdaily;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class FirstAlarm extends AppCompatActivity {

    private Button stopAlarm, cancelAlarm;
    private MediaPlayer mediaPlayer;
   // Vibrator vibrator;
    Intent serviceIntent;
    int temp = 0;
    TextView nextAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_alarm);
        WakeLocker.acquire(this);

        //stop notif mediaplayer
        AlarmReciever.mediaPlayer.stop();

        //cancel notif
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(5);

        FinalAct.currentAlarm = 1;

        stopAlarm = findViewById(R.id.AlarmStop);
        cancelAlarm =  findViewById(R.id.cancelAlarm);
        nextAct = findViewById(R.id.nextAct);

        //nextAct.setText("NEXT:" + AlarmService.newTasks[1]);
        //Mediaplayer start
        // mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);
        mediaPlayer = MediaPlayer.create(this, R.raw.swiftly);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        stopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlayer();

                Context context = getApplicationContext();

                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);



                WakeLocker.release();


                //call startalarms
                AlarmService.startAlarms(0);

            }
        });
        cancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinalAct.ServiceStopper = true;

                //stop service
                serviceIntent = new Intent(getApplicationContext(),AlarmService.class);
                stopService(serviceIntent);//SERVICE STOPPED

                stopPlayer();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                String text = "DAILY PLANNER closed, all alarms cancelled " ;
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //stopPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stopPlayer();
    }
    private void  stopPlayer(){
        if(mediaPlayer != null ){
            mediaPlayer.stop();
            mediaPlayer = null;
            WakeLocker.release();
            Log.i("MEDIA", "Media should have stopped");
        }
       WakeLocker.release();
   }
    @Override
    public void onBackPressed() {// makes program exit to home screen
        Log.i("CDA", "onBackPressed Called");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
