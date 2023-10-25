package com.example.hotelbooking_app.Booking.Activity;

import android.content.Intent;
import android.os.Bundle;
//import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking_app.Booking.Fragment.BookingGuestsSelectBottomSheet;
import com.example.hotelbooking_app.Booking.Fragment.BookingRoomsSelectBottomSheet;

import com.example.hotelbooking_app.Booking.Item.BookingRoomType;

import com.example.hotelbooking_app.Booking.Interface.OnSaveClickListener;

import com.example.hotelbooking_app.R;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import androidx.core.util.Pair;
public class BookingActivity extends AppCompatActivity {

    private TextView guestsSelect;
    private TextView roomsSelect;
    private TextView datesSelect;
    private MaterialDatePicker<Pair<Long, Long>> datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_layout);


        // Initialize UI elements
        guestsSelect = findViewById(R.id.guests_number_select);
        roomsSelect = findViewById(R.id.room_type_select);
        datesSelect = findViewById(R.id.dates_select);


        setupGuestsSelect();
        setupRoomsSelect();
        setupDateSelect();
    }

    private void setupGuestsSelect() {
        guestsSelect.setOnClickListener(v -> showGuestsSelectBottomSheet());
    }

    private void showGuestsSelectBottomSheet() {
        BookingGuestsSelectBottomSheet guestsSelectBottomSheet = new BookingGuestsSelectBottomSheet();
        guestsSelectBottomSheet.show(getSupportFragmentManager(), guestsSelectBottomSheet.getTag());
    }

    private void setupRoomsSelect() {
        roomsSelect.setOnClickListener(v -> showRoomsSelectBottomSheet());
    }

    private void showRoomsSelectBottomSheet() {
        BookingRoomsSelectBottomSheet bookingRoomsSelectBottomSheet = new BookingRoomsSelectBottomSheet();
        bookingRoomsSelectBottomSheet.show(getSupportFragmentManager(), bookingRoomsSelectBottomSheet.getTag());
    }

    private void setupDateSelect() {
        datePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select Date")
                .setTheme(R.style.ThemeMaterialCalendar)
                .build();


        datePicker.addOnPositiveButtonClickListener(selection -> {
            Pair<Long, Long> dateRange = datePicker.getSelection();
            String formattedDateRange = formatTimestampRange(dateRange.first, dateRange.second);
            datesSelect.setText(formattedDateRange);
        });

        datesSelect.setOnClickListener(v -> showDatePicker());
    }


    private void showDatePicker() {
        datePicker.show(getSupportFragmentManager(), "datePicker_tag");
    }


    private String formatTimestampRange(long startTimestamp, long endTimestamp) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date startDate = new Date(startTimestamp);
        Date endDate = new Date(endTimestamp);
        String formattedStart = dateFormat.format(startDate);
        String formattedEnd = dateFormat.format(endDate);
        return formattedStart + " - " + formattedEnd;
    }
}
