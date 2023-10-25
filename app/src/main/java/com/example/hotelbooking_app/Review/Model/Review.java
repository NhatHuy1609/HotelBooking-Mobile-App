package com.example.hotelbooking_app.Review.Model;

public class Review {
    private String reviewerName;
    private float rating;
    private String reviewText;

    public Review(String reviewerName, float rating, String reviewText) {
        this.reviewerName = reviewerName;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public float getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }
}

