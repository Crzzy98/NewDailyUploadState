package com.don.newdaily;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class StartButtonPage extends Fragment {

    TextView startPageBanner;
    ImageView settingsBtn;

    public StartButtonPage() {
        // Required empty public constructor
    }

    public static StartButtonPage newInstance(String param1, String param2) {
        StartButtonPage fragment = new StartButtonPage();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //postponeEnterTransition(5000L, TimeUnit.MILLISECONDS);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_button_page, container, false);

        startPageBanner = view.findViewById(R.id.startPageBanner);
        settingsBtn = view.findViewById(R.id.settingsBtn);
        //COLORING INDIVIDUAL TEXT
        String text = "WELCOME TO YOUR DAILY PLANNER";
        SpannableString spannableString =  new SpannableString(text);
        ForegroundColorSpan spanYellow = new ForegroundColorSpan(Color.BLUE);
        ForegroundColorSpan spanBlack = new ForegroundColorSpan(Color.BLACK);

        spannableString.setSpan(spanBlack, 0,15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(spanYellow, 15,29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        startPageBanner.setText(spannableString);

        Button beginBtn = (Button) view.findViewById(R.id.startBtn);
        beginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TasksOrNormal.class));
                //getActivity().overridePendingTransition(R.anim.slide_up, R.anim.slide_up);
            }
        });
        settingsBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                 MainActivity.openSettings(getActivity().getSupportFragmentManager());
            }
        });
        return view;
    }

}