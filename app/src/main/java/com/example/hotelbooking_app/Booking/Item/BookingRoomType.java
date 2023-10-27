package com.example.hotelbooking_app.Booking.Item;

import java.io.Serializable;

public class BookingRoomType implements Serializable {
    private int id;
    private String name;
    private String description;
    private boolean isSelected;

    public BookingRoomType(int id, String name, String description, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isSelected = isSelected;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
