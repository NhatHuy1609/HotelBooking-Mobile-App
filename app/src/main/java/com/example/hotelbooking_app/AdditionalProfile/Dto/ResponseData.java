package com.example.hotelbooking_app.AdditionalProfile.Dto;


import com.google.gson.annotations.SerializedName;

public class ResponseData {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Object data;
    @SerializedName("status")
    private String status;
}
