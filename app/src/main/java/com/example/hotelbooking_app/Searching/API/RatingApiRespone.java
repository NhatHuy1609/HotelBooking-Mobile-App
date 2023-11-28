package com.example.hotelbooking_app.Searching.API;

public class RatingApiRespone {
    private String message;
    private float data;

    public RatingApiRespone(String message, float data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public float getData() {
        return data;
    }

    public void setData(float data) {
        this.data = data;
    }
}
