package com.example.hotelbooking_app.Homescreen.HotelApiService;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hotel {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("price")
    private double price;

    @SerializedName("imageDetails")
    private List<ImageDetail> imageDetails;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<ImageDetail> getImageDetails() {
        return imageDetails;
    }

    public void setImageDetails(List<ImageDetail> imageDetails) {
        this.imageDetails = imageDetails;
    }

}

