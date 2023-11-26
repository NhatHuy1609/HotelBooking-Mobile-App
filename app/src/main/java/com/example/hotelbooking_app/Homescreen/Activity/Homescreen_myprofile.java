package com.example.hotelbooking_app.Homescreen.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking_app.Homescreen.HomescreenActivity;
import com.example.hotelbooking_app.R;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class Homescreen_myprofile extends AppCompatActivity {
    ImageButton btn_back;
    private static final int PICK_IMAGE = 1;
    ImageView imgProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen_myprofile);

        btn_back = (ImageButton) findViewById(R.id.myprofile_btn_back);
        imgProfile = (ImageView) findViewById(R.id.myprofile_imgacc);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            try {
                // Đọc dữ liệu hình ảnh từ Uri
                InputStream inputStream = getContentResolver().openInputStream(selectedImageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                // Hiển thị hình ảnh trong ImageView
                imgProfile.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
