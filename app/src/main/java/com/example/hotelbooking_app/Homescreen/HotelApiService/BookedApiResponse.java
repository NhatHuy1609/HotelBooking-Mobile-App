package com.example.hotelbooking_app.Homescreen.HotelApiService;

import java.util.List;

public class BookedApiResponse {
    private String message;
    private List<Booked> data;
    private String status;

    public BookedApiResponse(String message, List<Booked> data, String status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Booked> getData() {
        return data;
    }

    public void setData(List<Booked> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
