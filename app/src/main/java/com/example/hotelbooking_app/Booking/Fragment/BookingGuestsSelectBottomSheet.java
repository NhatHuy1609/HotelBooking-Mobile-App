package com.example.hotelbooking_app.Booking.Fragment;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.example.hotelbooking_app.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class BookingGuestsSelectBottomSheet extends BottomSheetDialogFragment {

    public BookingGuestsSelectBottomSheet() {
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

        contentView = LayoutInflater.from(getContext()).inflate(R.layout.booking_guests_select_bottom_sheet, null);
        bottomSheetDialog.setContentView(contentView);

        // Bottom sheet behavior
        bottomSheetBehavior = BottomSheetBehavior.from((View) contentView.getParent());
        bottomSheetBehavior.setPeekHeight(1200); // Set the initial peek height

        AppCompatButton saveButton = contentView.findViewById(R.id.booking_guestsSelect_saveBtn);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // Handle state changes if needed

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        return bottomSheetDialog;
    }
}
