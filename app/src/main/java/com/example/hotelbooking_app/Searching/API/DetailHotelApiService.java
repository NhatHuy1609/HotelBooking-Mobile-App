package com.example.hotelbooking_app.Searching.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface DetailHotelApiService {
    @GET("api/v1/hotel/{id}")
    Call<DetailHotelApiRespone> getHotelById(
            @Header("Authorization") String token,
            @Path("id") int hotelId
    );
}
