package com.example.hotelbooking_app.Homescreen.HotelApiService;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface HotelEndpoint {
    @GET("/api/v1/hotel")
    Call<HotelApiResponse> getHotels(@Header("Authorization") String authorization);
    @GET("/api/v1/review/{hotelId}")
    Call<ReviewApiResponse> getReviews(@Path("hotelId") int hotelId, @Header("Authorization") String authorization);
    @GET("/api/v1/review/{hotelId}/rate")
    Call<RatingApiResponse> getHotelRating(@Path("hotelId") int hotelId, @Header("Authorization") String authorization);

}
