package com.example.hotelbooking_app.Searching.API;

import com.example.hotelbooking_app.Searching.Domain.PopularHotel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface PopularHotelApiService {
    @GET("api/v1/hotel/popular")
    Call<PopularHotelApiRespone> getAllPopularHotels(@Header("Authorization") String token);
}
