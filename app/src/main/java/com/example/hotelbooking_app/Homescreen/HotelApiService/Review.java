package com.example.hotelbooking_app.Homescreen.HotelApiService;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("id")
    private int id;

    @SerializedName("rate")
    private double rate;

    @SerializedName("content")
    private String content;

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
