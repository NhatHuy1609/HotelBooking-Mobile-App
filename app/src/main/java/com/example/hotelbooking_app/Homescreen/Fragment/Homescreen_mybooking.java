package com.example.hotelbooking_app.Homescreen.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ImageButton;

import com.example.hotelbooking_app.R;
import com.google.android.material.tabs.TabLayout;


public class Homescreen_mybooking extends Fragment {
    ImageButton mybooking_btn_back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.homescreen_fragment_mybooking, container, false);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.mybooking_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Booked"));
        tabLayout.addTab(tabLayout.newTab().setText("History"));

        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.mybooking_container);

        View bookedView = inflater.inflate(R.layout.homescreen_mybooking_fragment_booked, container, false);
        linearLayout.addView(bookedView);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                linearLayout.removeAllViews();
                if (tab.getPosition() == 0) {
                    View bookedView = inflater.inflate(R.layout.homescreen_mybooking_fragment_booked, container, false);
                    linearLayout.addView(bookedView);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        // back home
        mybooking_btn_back = (ImageButton) view.findViewById(R.id.mybooking_btn_back);

        // Set a click listener for the button
        mybooking_btn_back.setOnClickListener(new View.OnClickListener() {
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
