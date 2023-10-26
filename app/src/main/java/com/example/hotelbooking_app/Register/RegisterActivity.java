package com.example.hotelbooking_app.Register;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hotelbooking_app.AdditionalProfile.AdditionalProfileActivity;
import com.example.hotelbooking_app.Login.Activity.LoginActivity;
import com.example.hotelbooking_app.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openRegister();
    }

    private void openRegister() {
        setContentView(R.layout.sinup_layout);
        Button createBtn=findViewById(R.id.signup_create_btn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, AdditionalProfileActivity.class);
                startActivity(intent);
            }
        });

        TextView textView=findViewById(R.id.signup_move_login);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}