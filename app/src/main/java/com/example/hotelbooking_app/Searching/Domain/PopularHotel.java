package com.example.hotelbooking_app.Searching.Domain;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking_app.Booking.Adapter.BookingRoomTypeAdapter;
import com.example.hotelbooking_app.Searching.Domain.ImageDetail;

import java.util.List;

public class PopularHotel {
    private int id;
    private String name;
    private String address;
    private String price;
    private String overview;
    private List<ImageDetail> imageDetails;
    private int reviewQuantity;
    private boolean isFavourited;

    public PopularHotel(int id, String name, String address, String price, String overview, List<ImageDetail> imageDetails, int reviewQuantity, boolean isFavourited) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.price = price;
        this.overview = overview;
        this.imageDetails = imageDetails;
        this.reviewQuantity = reviewQuantity;
        this.isFavourited = isFavourited;
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
}
