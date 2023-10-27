package com.example.hotelbooking_app.Booking.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking_app.Booking.Data.BookingFormDetailData;
import com.example.hotelbooking_app.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BookingCheckoutActivity extends AppCompatActivity {
    private FrameLayout backBtn;

    private TextView dateInfor, guestInfor, phoneInfor, roomTypeInfor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_layout);

        //
        dateInfor = findViewById(R.id.checkout_booking_information_dates);
        guestInfor = findViewById(R.id.checkout_booking_information_guest);
        phoneInfor = findViewById(R.id.checkout_booking_information_phone_number);
        roomTypeInfor = findViewById(R.id.checkout_booking_information_room_type);

        //
        Intent bookingActivityIntent = getIntent();
        Bundle bookingActivityBundle = bookingActivityIntent.getExtras();
        if (bookingActivityBundle != null) {
            BookingFormDetailData bookingFormDetailData = (BookingFormDetailData) bookingActivityBundle.getSerializable("bookingFormData");
            updateCheckoutView(bookingFormDetailData);
        }

        setUpNavigateToBookingActivity();
    }

    private void setUpNavigateToBookingActivity() {
        backBtn = findViewById(R.id.checkout_back_button);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, BookingActivity.class);
            startActivity(intent);
        });
    }

    private void updateCheckoutView(BookingFormDetailData data) {
        String dateFormatted = formatDateRange(data.getStartDate(), data.getEndDate());
        String guestRoomQuantity = (data.getSelectedAdultValue() + data.getSelectedChildValue()) + " guests " + "- " + data.getSelectedRoomValue() + " rooms";
        String roomTypeQuantity = data.getRoomTypeList().size() + " room types";
        String phoneNumber = data.getPhoneNumber();

        dateInfor.setText(dateFormatted);
        guestInfor.setText(guestRoomQuantity);
        roomTypeInfor.setText(roomTypeQuantity);
        phoneInfor.setText(phoneNumber);
    }

    private String formatDateRange(Date startDate, Date endDate) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedStart = dateFormat.format(startDate);
        String formattedEnd = dateFormat.format(endDate);

        return formattedStart + " - " + formattedEnd;
    }
}
