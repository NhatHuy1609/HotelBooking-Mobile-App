package com.example.hotelbooking_app.Register;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotelbooking_app.AdditionalProfile.AdditionalProfileActivity;
import com.example.hotelbooking_app.Login.Activity.LoginActivity;
import com.example.hotelbooking_app.R;

public class RegisterActivity extends AppCompatActivity {

    private ImageView passwordImageView;
    private TextView passwordText;
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


        passwordText=findViewById(R.id.signup_password_text);
        passwordImageView=findViewById(R.id.signup_password_icon);
        passwordImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable currentDrawable = passwordImageView.getDrawable();
                if (currentDrawable != null && !currentDrawable.getConstantState().equals(getResources().getDrawable(R.drawable.signup_password_hide).getConstantState())) {
                    passwordText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    passwordImageView.setImageResource(R.drawable.signup_password_hide);
                } else {
                    passwordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    passwordImageView.setImageResource(R.drawable.signup_visible_icon);
                }

            }
        });

    }
}