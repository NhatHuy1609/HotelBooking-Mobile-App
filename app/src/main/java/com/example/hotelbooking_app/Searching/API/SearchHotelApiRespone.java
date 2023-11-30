package com.example.hotelbooking_app.Searching.API;

import com.example.hotelbooking_app.Searching.Domain.Hotel;

import java.util.List;

public class SearchHotelApiRespone {
    private String message;
    private List<Hotel> data;

    public SearchHotelApiRespone(String message, List<Hotel> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Hotel> getData() {
        return data;
    }

    public void setData(List<Hotel> data) {
        this.data = data;
    }
}
