package com.example.hotelbooking_app.Searching.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchHotelApiService {
    @GET("api/v1/hotel/search")
    Call<SearchHotelApiRespone> getSearchHotels(
            @Header("Authorization") String token,
            @Query("keyword") String keyword
    );
}
