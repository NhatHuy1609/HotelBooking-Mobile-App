package com.example.hotelbooking_app.Login.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotelbooking_app.AdditionalProfile.AdditionalProfileActivity;
import com.example.hotelbooking_app.Homescreen.HomescreenActivity;
import com.example.hotelbooking_app.Login.Fragment.ForgotPasswordBottomSheetFragment;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText passwordText;
    private EditText emailText;
    private ImageView passwordImageView;
    private CheckBox checkBox;
    private TextView forgotPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);
        checkBox = findViewById(R.id.signin_checkbox_id);
        TextView moveRegister = findViewById(R.id.signin_move_register);
        emailText = findViewById(R.id.signin_email);
        loginButton = findViewById(R.id.signin_login_btn);
        passwordText = findViewById(R.id.signin_password_text);
        passwordImageView = findViewById(R.id.signin_password_icon);
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String savedUsername = sharedPreferences.getString("email", "");
            String savedPassword = sharedPreferences.getString("password", "001");
            if (!TextUtils.isEmpty(savedUsername) && !TextUtils.isEmpty(savedPassword)) {
                emailText.setText(savedUsername);
                passwordText.setText(savedPassword);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        moveRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if (checkBox.isChecked()) {
                            editor.putString("email", emailText.getText().toString());
                            editor.putString("password", passwordText.getText().toString());

                        } else {
                            editor.putString("email", "");
                            editor.putString("password", "");
                        }
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this, HomescreenActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
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

        forgotPasswordTextView = findViewById(R.id.signin_forgot_password);
        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                String email = emailText.getText().toString();
                bundle.putString("email", email);
                ForgotPasswordBottomSheetFragment bottomSheetFragment = new ForgotPasswordBottomSheetFragment();
                bottomSheetFragment.setArguments(bundle);
                bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
            }
        });


    }
}