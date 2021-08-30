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
import android.widget.Toast;

public class FourthAlarm extends AppCompatActivity {
    private Button stopAlarm;
    private MediaPlayer mediaPlayer;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_alarm);
        WakeLocker.acquire(this);
        //stop notif mediaplayer
        AlarmReciever.mediaPlayer.stop();
        //cancel notif
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(5);

        FinalAct.currentAlarm ++;

        stopAlarm = findViewById(R.id.AlarmStop);
        mediaPlayer = MediaPlayer.create(this, R.raw.swiftly);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        stopAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlayer();
                //stop service
                serviceIntent = new Intent(getApplicationContext(),AlarmService.class);
                stopService(serviceIntent);//SERVICE STOPPED

                String text = "DAILY PLANNER closed,end of alarms" ;
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();    }

    @Override
    protected void onPause() {
        super.onPause();
        stopPlayer();    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPlayer();
    }
    private void  stopPlayer(){
        if(mediaPlayer != null ){
            mediaPlayer.stop();
            mediaPlayer = null;
            WakeLocker.release();
            Log.i("MEDIA", "Media should have stopped");
        }
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
