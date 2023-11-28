package com.example.hotelbooking_app.Homescreen.HotelApiService;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HotelEndpoint {
    @GET("/api/v1/hotel")
    Call<HotelsApiResponse> getHotels(@Header("Authorization") String authorization);
    @GET("/api/v1/hotel/popular")
    Call<HotelsApiResponse> getPpHotels(@Header("Authorization") String authorization);
    @GET("/api/v1/favourite-hotel")
    Call<HotelsApiResponse> getFavoriteHotels(@Header("Authorization") String authorization);
    @GET("/api/v1/booking")
    Call<BookedApiResponse> getBooked(@Header("Authorization") String authorization);
    @GET("/api/v1/hotel/{id}")
    Call<HotelApiResponse> getHotel(@Path("id") int hotelId, @Header("Authorization") String authorization);
    @POST("/api/v1/favourite-hotel/{hotelId}")
    Call<HotelsApiResponse> postFavoriteHotels(@Path("hotelId") int hotelId, @Header("Authorization") String authorization);
    @DELETE("/api/v1/favourite-hotel/{hotelId}")
    Call<HotelsApiResponse> deleteFavoriteHotels(@Path("hotelId") int hotelId, @Header("Authorization") String authorization);

}
