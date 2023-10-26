package com.example.hotelbooking_app.Booking.Data;

import com.example.hotelbooking_app.Booking.Item.BookingRoomType;

import java.util.ArrayList;
import java.util.Date;

public class BookingFormDetailData {
    private int selectedRoomValue;
    private int selectedAdultValue;
    private int selectedChildValue;
    private Date startDate;
    private Date endDate;
    private String phoneNumber;
    private ArrayList<BookingRoomType> roomTypeList;

    public ArrayList<BookingRoomType> getRoomTypeList() {
        return roomTypeList;
    }

    public void setRoomTypeList(ArrayList<BookingRoomType> roomTypeList) {
        this.roomTypeList = roomTypeList;
    }

    public BookingFormDetailData() {
        this.selectedRoomValue = 0;
        this.selectedAdultValue = 0;
        this.selectedChildValue = 0;
    }

    public int getSelectedRoomValue() {
        return selectedRoomValue;
    }

    public void setSelectedRoomValue(int selectedRoomValue) {
        this.selectedRoomValue = selectedRoomValue;
    }

    public int getSelectedAdultValue() {
        return selectedAdultValue;
    }

    public void setSelectedAdultValue(int selectedAdultValue) {
        this.selectedAdultValue = selectedAdultValue;
    }

    public int getSelectedChildValue() {
        return selectedChildValue;
    }

    public void setSelectedChildValue(int selectedChildValue) {
        this.selectedChildValue = selectedChildValue;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
