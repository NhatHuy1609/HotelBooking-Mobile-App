package com.example.hotelbooking_app.Booking.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking_app.Booking.Adapter.BookingRoomTypeAdapter;
import com.example.hotelbooking_app.Booking.Fragment.BookingGuestsSelectBottomSheet;
import com.example.hotelbooking_app.Booking.Item.BookingRoomType;
import com.example.hotelbooking_app.Booking.Fragment.BookingRoomsSelectBottomSheet;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Register.RegisterActivity;

import java.util.ArrayList;

public class BookingActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_layout);

        Button openRegistrationButton = findViewById(R.id.continue_button);
        openRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              // Mở RegistrationActivity khi người dùng nhấn nút
              TextView guestsSelect = findViewById(R.id.guests_number_select);
              TextView roomsSelect = findViewById(R.id.room_type_select);
        guestsSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingGuestsSelectBottomSheet guestsSelectBottomSheet = new BookingGuestsSelectBottomSheet();
                guestsSelectBottomSheet.show(getSupportFragmentManager(), guestsSelectBottomSheet.getTag());
            }
        });

        roomsSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingRoomsSelectBottomSheet bookingRoomsSelectBottomSheet = new BookingRoomsSelectBottomSheet();
                bookingRoomsSelectBottomSheet.show(getSupportFragmentManager(), bookingRoomsSelectBottomSheet.getTag());
            }
        });
    }


    public void createRoomTypeRecyclerView() {
        RecyclerView recyclerView;
        ArrayList<BookingRoomType> roomTypeList = new ArrayList<>();
        roomTypeList.add(new BookingRoomType("King Room", "This is king room type", false));
        roomTypeList.add(new BookingRoomType("Queen Room", "This is queen room type", false));

        recyclerView = findViewById(R.id.booking_room_type_recycler_view);
        BookingRoomTypeAdapter bookingRoomTypeAdapter = new BookingRoomTypeAdapter(this, roomTypeList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(bookingRoomTypeAdapter);
    }

}
