package com.example.hotelbooking_app.Searching.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface HotelApiService {
    @GET("api/v1/hotel")
    Call<HotelApiRespone> getAllHotels(@Header("Authorization") String token);
}
