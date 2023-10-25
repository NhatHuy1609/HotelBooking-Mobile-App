package com.example.hotelbooking_app.Booking.Item;

public class BookingRoomType {
    private String name;
    private String description;
    private boolean isSelected;

    public BookingRoomType(String name, String description, boolean isSelected) {
        this.name = name;
        this.description = description;
        this.isSelected = isSelected;
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
