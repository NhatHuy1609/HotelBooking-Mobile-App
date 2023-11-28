package com.example.hotelbooking_app.Searching.Domain;

import java.util.List;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private String price;
    private String overview;
    private List<ImageDetail> imageDetails;
    private int reviewQuantity;
    private boolean isFavourited;
    private float rate;

    public Hotel(int id, String name, String address, String price, String overview, List<ImageDetail> imageDetails, int reviewQuantity, boolean isFavourited, float rate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.price = price;
        this.overview = overview;
        this.imageDetails = imageDetails;
        this.reviewQuantity = reviewQuantity;
        this.isFavourited = isFavourited;
        this.rate = rate;
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<ImageDetail> getImageDetails() {
        return imageDetails;
    }

    public void setImageDetails(List<ImageDetail> imageDetails) {
        this.imageDetails = imageDetails;
    }

    public int getReviewQuantity() {
        return reviewQuantity;
    }

    public void setReviewQuantity(int reviewQuantity) {
        this.reviewQuantity = reviewQuantity;
    }

    public boolean isFavourited() {
        return isFavourited;
    }

    public void setFavourited(boolean favourited) {
        isFavourited = favourited;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
