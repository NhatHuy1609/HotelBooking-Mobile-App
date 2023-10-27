package com.example.hotelbooking_app.Login.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hotelbooking_app.AdditionalProfile.AdditionalProfileActivity;
import com.example.hotelbooking_app.Homescreen.HomescreenActivity;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);
        TextView moveRegister=findViewById(R.id.signin_move_register);
        moveRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        loginButton=findViewById(R.id.signin_login_btn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(LoginActivity.this, HomescreenActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });


    }
}