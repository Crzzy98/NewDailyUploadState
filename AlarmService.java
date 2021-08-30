    package com.don.newdaily;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class AlarmService extends Service {


    public static NotificationCompat.Builder notificationBuilder;
    public static Intent[] alarmArray = { FinalAct.firstAlarm, FinalAct.secondAlarm, FinalAct.thirdAlarm, FinalAct.fourthAlarm };
    static int temp = 0;
    static Context context;
   // public static Object [] newTasks = FinalAct.tempTasks;
    public AlarmService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
            startMyOwnForeground();
        else
            startForeground(1, new Notification());

        Log.i ("Service status", "Running");

        context = this.getApplicationContext();

        if(FinalAct.ServiceStopper == false) {//if service is launched less than once
            startAlarms(FinalAct.newTime);//BROADCAST STARTED HERE
        }else{
            startAlarms(FinalAct.newTime);//BROADCAST STARTED HERE
            Log.i ("Service status", "Restarted and updated");
            String text = "Alarm Service restarted";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private void startMyOwnForeground()
    {
        String NOTIFICATION_CHANNEL_ID = "example.permanence";
        String channelName = "Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_LOW);//changed to high importance
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("DAILY PLANNER is running in the background")
                .setPriority(NotificationManager.IMPORTANCE_MAX)
                .setCategory(Notification.CATEGORY_SERVICE)
                .setSmallIcon(R.drawable.simple_clock_icon)//format art
                .build();
        startForeground(2, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //startalarms()
    public static void startAlarms(int newTime ){//
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReciever.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        assert alarm != null;

        switch(FinalAct.currentAlarm){
            case 0:
                temp = FinalAct.firstTime;
                break;
            case 1:
                temp = FinalAct.secondTime;
                break;
            case 2:
                temp = FinalAct.thirdTime;
                break;
            case 3:
                temp = FinalAct.fourthTime;
                break;
        }
        if(FinalAct.currentAlarm < 1) {
            if (newTime < 1) {
//System.currentTimeMillis() + temp
                alarm.setExact(AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + temp, alarmIntent);
                //FinalAct.timeHolder = (int) (System.currentTimeMillis() + temp );
                FinalAct.timeHolder = (int) (System.currentTimeMillis() + temp);
                Time time;
                time = new Time(System.currentTimeMillis() + temp);
                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");

                //TOAST MESSAGE DISPLAYED HERE
                String text = "First alarm set for " + formatter.format(time);
                Toast toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();
            } else {
                alarm.setExact(AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + (FinalAct.newTime - FinalAct.timeHolder), alarmIntent);
                Time time;
                time = new Time(System.currentTimeMillis() + temp);
                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");

                //TOAST MESSAGE DISPLAYED HERE
                String text = "Next alarm set for " + formatter.format(time);
                Toast toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();
            }
        }else{ //AFTER FIRST ALARM
            if (newTime < 1) {
//System.currentTimeMillis() + temp
                alarm.setExact(AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + temp, alarmIntent);
                //FinalAct.timeHolder = (int) (System.currentTimeMillis() + temp );
                FinalAct.timeHolder = (int) (System.currentTimeMillis() + temp);
                Time time;
                time = new Time(System.currentTimeMillis() + temp);
                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");

                //TOAST MESSAGE DISPLAYED HERE
                String text = "Next alarm set for " + formatter.format(time);
                Toast toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();
            } else {
                alarm.setExact(AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + (FinalAct.newTime - FinalAct.timeHolder), alarmIntent);
                Time time;
                time = new Time(System.currentTimeMillis() + temp);
                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");

                //TOAST MESSAGE DISPLAYED HERE
                String text = "Next alarm set for " + formatter.format(time);
                Toast toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(FinalAct.ServiceStopper == false){
          //  FinalAct.newTime = (int) (System.currentTimeMillis() + AlarmReciever.temp );
            FinalAct.newTime = (int) (System.currentTimeMillis() + temp );

            //add currentalarm if funct. to not restart after fourth
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction("restartservice");
            broadcastIntent.setClass(this, Restarter.class);
            this.sendBroadcast(broadcastIntent);
        }
    }

}