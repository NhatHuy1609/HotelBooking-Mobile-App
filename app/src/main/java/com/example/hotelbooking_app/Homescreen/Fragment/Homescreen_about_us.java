package com.example.hotelbooking_app.Homescreen.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.hotelbooking_app.R;

public class Homescreen_about_us extends Fragment {
    ImageButton aboutus_btn_back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homescreen_fragment_about_us, container, false);
        // back home
        aboutus_btn_back = (ImageButton) view.findViewById(R.id.aboutus_btn_back);

        // Set a click listener for the button
        aboutus_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment_2 = new Homescreen_home();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.homescreen_containerr, fragment_2);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}