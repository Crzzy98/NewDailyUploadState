package com.don.newdaily;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SettingsFragment extends Fragment {

    androidx.appcompat.widget.Toolbar settingsHeader;
    TextView Howto;
    TextView presets;
    TextView themes;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Howto = view.findViewById(R.id.howtoUse);
        presets = view.findViewById(R.id.customPresets);
        themes = view.findViewById(R.id.chooseTheme);

        settingsHeader = view.findViewById(R.id.settingsHeader);
        ((AppCompatActivity)getActivity()).setSupportActionBar(settingsHeader);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Settings");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        settingsHeader.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHome(getActivity().getSupportFragmentManager());            }
        });

        Howto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHow(getActivity().getSupportFragmentManager());
            }
        });
        return view;
    }
    public static void returnHome(FragmentManager fragmentManager){
        FragmentTransaction transaction2 = fragmentManager.beginTransaction();
        transaction2.setReorderingAllowed(true);
        transaction2.setCustomAnimations(R.anim.slide_up, R.anim.slide_up);
        transaction2.replace(R.id.fragment_container_view, StartButtonPage.class, null);
        transaction2.commit();

    }
    public static void goHow(FragmentManager fragmentManager){
        FragmentTransaction transaction2 = fragmentManager.beginTransaction();
        transaction2.setReorderingAllowed(true);
        transaction2.setCustomAnimations(R.anim.slide_up, R.anim.slide_up);
        transaction2.replace(R.id.fragment_container_view, HowToUse.class, null);
        transaction2.commit();

    }
}