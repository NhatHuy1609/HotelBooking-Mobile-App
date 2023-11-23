package com.example.hotelbooking_app.Login.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hotelbooking_app.Homescreen.HomescreenActivity;
import com.example.hotelbooking_app.Login.AuthService.AccessTokenJson;
import com.example.hotelbooking_app.Login.AuthService.AuthApiService;
import com.example.hotelbooking_app.Login.AuthService.AuthEnpoint;
import com.example.hotelbooking_app.Login.AuthService.AuthenticationRequest;
import com.example.hotelbooking_app.Login.Fragment.ForgotPasswordBottomSheetFragment;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Register.RegisterActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private EditText passwordText;
    private EditText emailText;
    private ImageView passwordImageView;
    private CheckBox checkBox;
    private TextView forgotPasswordTextView;

    private static final String BASE_URL = "https://subsequent-distance-production.up.railway.app/";

    private Retrofit retrofit;
    private AuthEnpoint apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = (AuthEnpoint) retrofit.create(AuthEnpoint.class);
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

                        String jwtToken = sharedPreferences.getString("jwtKey", null);

                        if (jwtToken != null) {
                            Intent intent = new Intent(LoginActivity.this, HomescreenActivity.class);
                            startActivity(intent);
                        } else {
                            showFailAuthentication();

                            Log.d("JWT Token", "Không có JWT Token được lưu trữ");
                        }

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

    private void showFailAuthentication() {
        Toast.makeText(this, "Email or password are incorrect.", Toast.LENGTH_SHORT).show();
    }

    public void authentication(String email, String password) {
        // Gọi API authenticate
        Call<AccessTokenJson> call = apiService.authenticate(new AuthenticationRequest(password,email));
        final boolean[] isAuthentication = {};
        // Thực hiện yêu cầu bất đồng bộ
        call.enqueue(new Callback<AccessTokenJson>() {
            @Override
            public void onResponse(Call<AccessTokenJson> call, Response<AccessTokenJson> response) {
                if (response.isSuccessful()) {
                    AccessTokenJson authenticationResponse = response.body();
                    // Lấy ra đối tượng SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Log.i("JWT", ""+authenticationResponse.getAccessToken());
                    editor.putString("jwtKey", authenticationResponse.getAccessToken());

                    editor.apply();
                } else {
                    Log.i("JWT", "Khong duoc");
                }
            }

            @Override
            public void onFailure(Call<AccessTokenJson> call, Throwable t) {
            }
        });
    }
}