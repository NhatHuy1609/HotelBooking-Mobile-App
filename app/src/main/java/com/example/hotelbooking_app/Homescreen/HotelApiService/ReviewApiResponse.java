package com.example.hotelbooking_app.Homescreen.HotelApiService;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewApiResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private List<Review> data;

    @SerializedName("status")
    private String status;

    // Getters and setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Review> getData() {
        return data;
    }

    public void setData(List<Review> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
