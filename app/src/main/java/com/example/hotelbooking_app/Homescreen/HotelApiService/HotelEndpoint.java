package com.example.hotelbooking_app.Homescreen.HotelApiService;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HotelEndpoint {
    @GET("/api/v1/hotel")
    Call<HotelApiResponse> getHotels(@Header("Authorization") String authorization);
    @GET("/api/v1/hotel/popular")
    Call<HotelApiResponse> getPpHotels(@Header("Authorization") String authorization);
    @GET("/api/v1/favourite-hotel")
    Call<HotelApiResponse> getFavoriteHotels(@Header("Authorization") String authorization);
    @POST("/api/v1/favourite-hotel/{hotelId}")
    Call<HotelApiResponse> postFavoriteHotels(@Path("hotelId") int hotelId, @Header("Authorization") String authorization);
    @DELETE("/api/v1/favourite-hotel/{hotelId}")
    Call<HotelApiResponse> deleteFavoriteHotels(@Path("hotelId") int hotelId, @Header("Authorization") String authorization);

}
