package com.example.hotelbooking_app.Homescreen.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking_app.Homescreen.HomescreenActivity;
import com.example.hotelbooking_app.R;
import android.content.Intent;
import android.widget.ImageView;


public class Homescreen_changepassword extends AppCompatActivity {
    ImageView changepassword_btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen_changepassword);
        changepassword_btn_back = findViewById(R.id.changepassword_btn_back);
        changepassword_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}

