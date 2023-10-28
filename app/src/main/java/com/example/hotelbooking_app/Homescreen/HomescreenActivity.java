package com.example.hotelbooking_app.Homescreen;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.os.Bundle;


import com.example.hotelbooking_app.Homescreen.Fragment.Homescreen_about_us;
import com.example.hotelbooking_app.Homescreen.Fragment.Homescreen_setting;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Homescreen.Fragment.Homescreen_home;
import com.example.hotelbooking_app.Homescreen.Fragment.Homescreen_mybooking;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomescreenActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    Homescreen_home homefragment = new Homescreen_home();
    Homescreen_mybooking mybookingfragment = new Homescreen_mybooking();
    Homescreen_about_us about_us = new Homescreen_about_us();
    Homescreen_setting setting = new Homescreen_setting();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        //bottom navigation
        bottomNavigationView = findViewById(R.id.homescreen_bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.homescreen_containerr, homefragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.btn_home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.homescreen_containerr, homefragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.btn_mybooking) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.homescreen_containerr, mybookingfragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.btn_about_us) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.homescreen_containerr, about_us).commit();
                    return true;
                }
                else if (item.getItemId() == R.id.btn_setting) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.homescreen_containerr, setting).commit();
                    return true;
                }

                return false;
            }
        });
    }
}
