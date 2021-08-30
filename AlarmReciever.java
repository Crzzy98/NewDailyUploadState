package com.don.newdaily;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlarmReciever extends BroadcastReceiver{
    public static MediaPlayer mediaPlayer;
    @Override
    public void onReceive(Context context, Intent intent) {

        //Create full screen intent here
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            final NotificationCompat.Builder builder = new NotificationCompat.Builder(context,MainActivity.NOTIFICATION_CHANNEL_ID);
            Intent fullIntent = AlarmService.alarmArray[FinalAct.currentAlarm];
            PendingIntent fullScreenPendingIntent =
                    PendingIntent.getActivity(context, 234,
                            fullIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setSound(null);
            builder.setSmallIcon(R.drawable.simple_clock_icon);//format art
            builder.setOngoing(true);
            builder.setContentIntent(fullScreenPendingIntent);
            builder.setAutoCancel(true);
            builder.setContentTitle("DAILY PLAN ALARM");
            builder.setCategory(Notification.CATEGORY_ALARM);
            builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            builder.setPriority(NotificationCompat.PRIORITY_MAX);
            builder.setFullScreenIntent(fullScreenPendingIntent, true);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = builder.build();

            //play notif sound
            mediaPlayer = MediaPlayer.create(context.getApplicationContext(), R.raw.swiftly);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();

            notificationManager.notify(5, notification);

        }

        }


}

