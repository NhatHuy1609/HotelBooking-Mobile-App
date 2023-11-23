package com.example.hotelbooking_app.Login.AuthService;

import android.content.Context;
import android.content.SharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthApiService {
    private static final String BASE_URL = "https://subsequent-distance-production.up.railway.app/";

    private Retrofit retrofit;
    private AuthEnpoint apiService;

    public AuthApiService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = (AuthEnpoint) retrofit.create(AuthApiService.class);
    }


}
