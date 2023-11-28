package com.example.hotelbooking_app.Searching.API;

import com.example.hotelbooking_app.Searching.Domain.PopularHotel;

import java.util.List;

public class PopularHotelApiRespone {
    private String message;
    private List<PopularHotel> data;

    public PopularHotelApiRespone(String message, List<PopularHotel> data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PopularHotel> getData() {
        return data;
    }

    public void setData(List<PopularHotel> data) {
        this.data = data;
    }
}
