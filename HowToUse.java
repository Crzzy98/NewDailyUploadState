package com.don.newdaily;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HowToUse extends Fragment {
    androidx.appcompat.widget.Toolbar howToUseHeader;

    public HowToUse() {
        // Required empty public constructor
    }

    public static HowToUse newInstance(String param1, String param2) {
        HowToUse fragment = new HowToUse();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_how_to_use, container, false);

        howToUseHeader = view.findViewById(R.id.howToUseHeader);
        ((AppCompatActivity)getActivity()).setSupportActionBar(howToUseHeader);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("How To Use");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        howToUseHeader.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack(getActivity().getSupportFragmentManager());            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    public static void goBack(FragmentManager fragmentManager){
        FragmentTransaction transaction2 = fragmentManager.beginTransaction();
        transaction2.setReorderingAllowed(true);
        transaction2.setCustomAnimations(R.anim.slide_up, R.anim.slide_up);
        transaction2.replace(R.id.fragment_container_view, SettingsFragment.class, null);
        transaction2.commit();

    }
}