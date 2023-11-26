package com.example.hotelbooking_app.Homescreen.HotelApiService;

import com.google.gson.annotations.SerializedName;

public class ImageDetail {
    @SerializedName("img")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

