package com.example.hotelbooking_app.Searching.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hotelbooking_app.Booking.Activity.BookingActivity;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Adapter.ReviewsItemAdapter;
import com.example.hotelbooking_app.Searching.Domain.ReviewsItemDomain;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    RecyclerView rvReviewsItem;
    ImageButton detailBackBtn;
    ReviewsItemAdapter reviewsItemAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        detailBackBtn = findViewById(R.id.detail_back_button);
        detailBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, SearchingActivity.class);
                startActivity(intent);
            }
        });

        hotelImageSlider();

        rvReviewsItem = findViewById(R.id.detail_rv_reviews_item);
        initReviewsItemListView();

        AppCompatButton bookingBtn = (AppCompatButton) findViewById(R.id.detail_booking_button);
        bookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, BookingActivity.class);
                startActivity(intent);
            }
        });
    }



    private void hotelImageSlider() {
        ImageSlider imageSlider = findViewById(R.id.detail_img_slider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.searching_image_muongthanh, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.searching_image_muongthanh, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.searching_image_muongthanh, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }

    private void initReviewsItemListView() {
        ArrayList<ReviewsItemDomain> arrReviewsItemData = new ArrayList<>();

        arrReviewsItemData.add(new ReviewsItemDomain("Truong Dinh Nhat", "Amazing! The room is good than the picture. Thanks for amazing experience!", 4.5, R.drawable.detail_avatar_icon));
        arrReviewsItemData.add(new ReviewsItemDomain("Truong Dinh Nhat", "Amazing! The room is good than the picture. Thanks for amazing experience!", 4.5, R.drawable.detail_avatar_icon));
        arrReviewsItemData.add(new ReviewsItemDomain("Truong Dinh Nhat", "Amazing! The room is good than the picture. Thanks for amazing experience!", 4.5, R.drawable.detail_avatar_icon));

        reviewsItemAdapter = new ReviewsItemAdapter(arrReviewsItemData);
        rvReviewsItem.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvReviewsItem.setAdapter(reviewsItemAdapter);
    }
}