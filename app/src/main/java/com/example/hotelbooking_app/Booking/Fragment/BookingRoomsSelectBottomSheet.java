package com.example.hotelbooking_app.Booking.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotelbooking_app.Booking.Activity.BookingActivity;
import com.example.hotelbooking_app.Booking.Adapter.BookingRoomTypeAdapter;
import com.example.hotelbooking_app.Booking.Item.BookingRoomType;
import com.example.hotelbooking_app.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class BookingRoomsSelectBottomSheet extends BottomSheetDialogFragment {
    public BookingRoomsSelectBottomSheet() {
        // Required empty public constructor
    }



    private View contentView;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    private int initialHeight = 1200; // Replace with your desired initial height

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);

        contentView = LayoutInflater.from(getContext()).inflate(R.layout.booking_rooms_select_bottom_sheet, null);
        bottomSheetDialog.setContentView(contentView);

        // Bottom sheet behavior
        bottomSheetBehavior = BottomSheetBehavior.from((View) contentView.getParent());
        bottomSheetBehavior.setPeekHeight(1400); // Set the initial peek height

        // Create Room Type RecyclerView

        return bottomSheetDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.booking_rooms_select_bottom_sheet, container, false);

        // Initialize your RecyclerView and data here
        RecyclerView recyclerView = rootView.findViewById(R.id.booking_room_type_recycler_view);
        ArrayList<BookingRoomType> roomTypeList = new ArrayList<>();
        roomTypeList.add(new BookingRoomType("King Room", "This is king room type", false));
        roomTypeList.add(new BookingRoomType("Queen Room", "This is queen room type", false));
        roomTypeList.add(new BookingRoomType("Queen Room", "This is queen room type", false));
        roomTypeList.add(new BookingRoomType("Queen Room", "This is queen room type", false));
        roomTypeList.add(new BookingRoomType("Queen Room", "This is queen room type", false));
        roomTypeList.add(new BookingRoomType("Queen Room", "This is queen room type", false));
        roomTypeList.add(new BookingRoomType("Queen Room", "This is queen room type", false));
        roomTypeList.add(new BookingRoomType("Queen Room", "This is queen room type", false));
        roomTypeList.add(new BookingRoomType("Queen Room", "This is queen room type", false));
        roomTypeList.add(new BookingRoomType("Queen Room", "This is queen room type", false));
        roomTypeList.add(new BookingRoomType("Queen Room", "This is queen room type", false));
        roomTypeList.add(new BookingRoomType("Queen Room", "This is queen room type", false));
        roomTypeList.add(new BookingRoomType("Queen Room", "This is queen room type", false));

        // Set up the RecyclerView
        BookingRoomTypeAdapter bookingRoomTypeAdapter = new BookingRoomTypeAdapter((BookingActivity) requireContext(), roomTypeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(bookingRoomTypeAdapter);

        return rootView;
    }
}