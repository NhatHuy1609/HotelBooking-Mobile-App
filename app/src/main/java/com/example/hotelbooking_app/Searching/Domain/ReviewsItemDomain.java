package com.example.hotelbooking_app.Searching.Domain;

public class ReviewsItemDomain {
    private String name;
    private String content;
    private double score;
    private int avatarUrl;

    public ReviewsItemDomain(String name, String content, double score, int avatarUrl) {
        this.name = name;
        this.content = content;
        this.score = score;
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(int avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
