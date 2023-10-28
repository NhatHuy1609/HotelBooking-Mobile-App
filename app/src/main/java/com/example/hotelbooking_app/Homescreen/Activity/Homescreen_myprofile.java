package com.example.hotelbooking_app.Homescreen.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking_app.Homescreen.HomescreenActivity;
import com.example.hotelbooking_app.R;
import android.content.Intent;


public class Homescreen_myprofile extends AppCompatActivity {
    ImageButton btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen_myprofile);

        btn_back = (ImageButton) findViewById(R.id.myprofile_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homescreen_myprofile.this, HomescreenActivity.class);
                startActivity(intent);
            }
        });


    }
}
