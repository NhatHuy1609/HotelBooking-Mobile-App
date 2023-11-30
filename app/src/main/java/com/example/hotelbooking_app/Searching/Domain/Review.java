package com.example.hotelbooking_app.Searching.Domain;

public class Review {
    private int id;
    private float rate;
    private String content;

    public Review(int id, float rate, String content) {
        this.id = id;
        this.rate = rate;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
