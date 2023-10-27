package com.example.hotelbooking_app.AdditionalProfile;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;


import androidx.annotation.Nullable;
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


import com.example.hotelbooking_app.Homescreen.HomescreenActivity;
import com.example.hotelbooking_app.R;

public class AdditionalProfileActivity extends AppCompatActivity {

    private String selectedDate="";
    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView imageView;
    private Button skipBtn;
    private  Button cpmBtn;
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
        imageView=findViewById(R.id.profile_ImgUpload);
        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendarDialog(); // Gọi hàm để hiển thị lịch
                editText.setText(selectedDate);
            }
        });
        skipBtn=findViewById(R.id.profile_skip_btn);
        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdditionalProfileActivity.this, HomescreenActivity.class);
                startActivity(intent);
            }
        });
        cpmBtn=findViewById(R.id.profile_complete_btn);
        cpmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdditionalProfileActivity.this, HomescreenActivity.class);
                startActivity(intent);
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
                openImageChooser();      }
        });



    }
    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}