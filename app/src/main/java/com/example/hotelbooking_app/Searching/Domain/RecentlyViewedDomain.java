package com.example.hotelbooking_app.Searching.Domain;

import java.io.Serializable;

public class RecentlyViewedDomain implements Serializable {
    private String name;
    private String address;
    private double score;
    private int count;
    private int picUrl;

    public RecentlyViewedDomain(String name, String address, double score, int count, int picUrl) {
        this.name = name;
        this.address = address;
        this.score = score;
        this.count = count;
        this.picUrl = picUrl;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(int picUrl) {
        this.picUrl = picUrl;
    }
}
