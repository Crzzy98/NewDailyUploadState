package com.don.newdaily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    public static final String APP_TAG = "mytag:NewDaily";
    public static String NOTIFICATION_CHANNEL_ID = "newDaily.fullscreen";
    Button btn;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MOBILE ADS INITIALIZED
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //Creation of notification Channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelName = "Background Service";
            NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_HIGH);

            NotificationManager manager = (NotificationManager) getSystemService(NotificationManager.class);
            assert manager != null;
            manager.createNotificationChannel(chan);
        }
        if (savedInstanceState == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true)
            .add(R.id.fragment_container_view, BroughtToYou.class, null)
            .commit();
            //switch to second frag
            final Handler mHandler = new Handler();
            mHandler.postDelayed(() -> {
                FragmentTransaction transaction2 = fragmentManager.beginTransaction();
                transaction2.setReorderingAllowed(true);
                transaction2.replace(R.id.fragment_container_view, StartButtonPage.class, null);
                transaction2.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
                transaction2.commit();
            }, 5000L);
        }

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

    }

    public static void openSettings(FragmentManager fragmentManager){
        FragmentTransaction transaction2 = fragmentManager.beginTransaction();
        transaction2.setReorderingAllowed(true);
        transaction2.setCustomAnimations(R.anim.slide_up, R.anim.slide_up);
        transaction2.replace(R.id.fragment_container_view, SettingsFragment.class, null);
        transaction2.commit();

    }

    @Override
    public void onBackPressed(){

        FragmentTransaction transaction2 = fragmentManager.beginTransaction();
        transaction2.setReorderingAllowed(true);
        transaction2.setCustomAnimations(R.anim.slide_down, R.anim.slide_down);
        transaction2.replace(R.id.fragment_container_view, StartButtonPage.class, null);
        transaction2.commit();

    }

    }


