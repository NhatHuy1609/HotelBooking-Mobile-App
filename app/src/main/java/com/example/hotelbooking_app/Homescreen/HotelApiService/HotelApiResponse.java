package com.example.hotelbooking_app.Homescreen.HotelApiService;

import java.util.List;

public class HotelApiResponse {
    private String message;
    private List<Hotel> data;
    private String status;



    public void setMessage(String message) {
        this.message = message;
    }

    public List<Hotel> getData() {
        return data;
    }

    public void setData(List<Hotel> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}