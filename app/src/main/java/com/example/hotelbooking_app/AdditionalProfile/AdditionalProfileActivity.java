package com.example.hotelbooking_app.AdditionalProfile;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.example.hotelbooking_app.R;

public class AdditionalProfileActivity extends AppCompatActivity {

    private String selectedDate="";
    private static final int PICK_IMAGE = 1;
    private ImageView imageView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openAdditionalProfile();
    }

    private void openAdditionalProfile() {
        setContentView(R.layout.additional_profile_layout);
        Button calendarButton = findViewById(R.id.profile_calendarButton);
        EditText editText=findViewById(R.id.profile_calendar_text);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendarDialog(); // Gọi hàm để hiển thị lịch
                editText.setText(selectedDate);
            }
        });

        uploadImg();


    }
    private void showCalendarDialog() {
        Dialog calendarDialog = new Dialog(this); // 'this' tham chiếu đến Activity hiện tại
        calendarDialog.setContentView(R.layout.profile_calendar_dialog); // Sử dụng layout cho Dialog
        CalendarView calendarView = calendarDialog.findViewById(R.id.profile_calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                calendarDialog.dismiss();
            }
        });

        calendarDialog.show();



    }
    private void uploadImg() {
        ImageView imageView = findViewById(R.id.profile_ImgUpload);
        Button uploadButton = findViewById(R.id.profile_uploadButton);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mở giao diện chọn hình ảnh từ bộ nhớ hoặc máy ảnh
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE);            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            try {
                // Chuyển đổi URI thành Bitmap và hiển thị nó trong ImageView
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}