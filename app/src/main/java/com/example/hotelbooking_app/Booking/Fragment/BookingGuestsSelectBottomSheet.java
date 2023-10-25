package com.example.hotelbooking_app.Booking.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.hotelbooking_app.Booking.Interface.OnSaveClickListener;
import com.example.hotelbooking_app.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BookingGuestsSelectBottomSheet extends BottomSheetDialogFragment {
    private FrameLayout increaseRoomBtn, decreaseRoomBtn;
    private FrameLayout increaseAdultBtn, decreaseAdultBtn;
    private FrameLayout increaseChildBtn, decreaseChildBtn;
    private TextView txtRoomNumberVal, txtAdultNumberVal, txtChildNumberVal;
    private AppCompatButton saveSelectBtn;
    private OnSaveClickListener onSaveClickListener;


    private View contentView;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    private int initialHeight = 1200; // Replace with your desired initial height

    public BookingGuestsSelectBottomSheet() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onSaveClickListener = (OnSaveClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnSaveClickListener");
        }
    }

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

        return bottomSheetDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.booking_guests_select_bottom_sheet, container, false);

        increaseRoomBtn = rootView.findViewById(R.id.booking_increase_room_button);
        decreaseRoomBtn = rootView.findViewById(R.id.booking_decrease_room_button);
        increaseAdultBtn = rootView.findViewById(R.id.booking_increase_adult_button);
        decreaseAdultBtn = rootView.findViewById(R.id.booking_decrease_adult_button);
        increaseChildBtn = rootView.findViewById(R.id.booking_increase_child_button);
        decreaseChildBtn = rootView.findViewById(R.id.booking_decrease_child_button);
        txtRoomNumberVal = rootView.findViewById(R.id.booking_room_number_select_value);
        txtAdultNumberVal = rootView.findViewById(R.id.booking_adult_number_select_value);
        txtChildNumberVal = rootView.findViewById(R.id.booking_child_number_select_value);
        saveSelectBtn = rootView.findViewById(R.id.booking_guests_select_save_button);

        // Set up increasing, decreasing guest, room number
        setUpRoomNumberAdjust();
        setUpAdultNumberAdjust();
        setUpChildNumberAdjust();
        setUpSaveSelect();

        return rootView;
    }

    public void setUpRoomNumberAdjust() {
        increaseRoomBtn.setOnClickListener(v -> {
            int selectedRoomVal = Integer.parseInt(txtRoomNumberVal.getText().toString());
            int newSelectedRoomVal = selectedRoomVal + 1;
            txtRoomNumberVal.setText(String.valueOf(newSelectedRoomVal));
        });

        decreaseRoomBtn.setOnClickListener(v -> {
            int selectedRoomVal = Integer.parseInt(txtRoomNumberVal.getText().toString());

            if (selectedRoomVal > 0) {
                int newSelectedRoomVal = selectedRoomVal - 1;
                txtRoomNumberVal.setText(String.valueOf(newSelectedRoomVal));
            }
        });
    }

    public void setUpAdultNumberAdjust() {
        increaseAdultBtn.setOnClickListener(v -> {
            int selectedAdultVal = Integer.parseInt(txtAdultNumberVal.getText().toString());
            int newSelectedAdultVal = selectedAdultVal + 1;
            txtAdultNumberVal.setText(String.valueOf(newSelectedAdultVal));
        });

        decreaseAdultBtn.setOnClickListener(v -> {
            int selectedAdultVal = Integer.parseInt(txtAdultNumberVal.getText().toString());

            if (selectedAdultVal > 0) {
                int newSelectedAdultVal = selectedAdultVal - 1;
                txtAdultNumberVal.setText(String.valueOf(newSelectedAdultVal));
            }
        });
    }

    public void setUpChildNumberAdjust() {
        increaseChildBtn.setOnClickListener(v -> {
            int selectedChildVal = Integer.parseInt(txtChildNumberVal.getText().toString());
            int newSelectedChildVal = selectedChildVal + 1;
            txtChildNumberVal.setText(String.valueOf(newSelectedChildVal));
        });

        decreaseChildBtn.setOnClickListener(v -> {
            int selectedChildVal = Integer.parseInt(txtChildNumberVal.getText().toString());

            if (selectedChildVal > 0) {
                int newSelectedChildVal = selectedChildVal - 1;
                txtChildNumberVal.setText(String.valueOf(newSelectedChildVal));
            }
        });
    }

    public void setUpSaveSelect() {
        saveSelectBtn.setOnClickListener(v -> {
            int selectedAdultVal = Integer.parseInt(txtAdultNumberVal.getText().toString());
            int selectedChildVal = Integer.parseInt(txtChildNumberVal.getText().toString());
            int totalSelectedRoomVal = Integer.parseInt(txtRoomNumberVal.getText().toString());
            int totalSelectedGuestsNumber = selectedAdultVal + selectedChildVal;

            onSaveClickListener.onSaveClick(totalSelectedGuestsNumber, totalSelectedRoomVal);

            dismiss();
        });
    }
}
