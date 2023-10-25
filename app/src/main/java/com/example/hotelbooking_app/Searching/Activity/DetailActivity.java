package com.example.hotelbooking_app.Searching.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Adapter.ReviewsItemAdapter;
import com.example.hotelbooking_app.Searching.Domain.ReviewsItemDomain;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    ListView lvReviewsItem;
    ReviewsItemAdapter reviewsItemAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        hotelImageSlider();

        lvReviewsItem = findViewById(R.id.detail_lv_reviews_item);
        initReviewsItemListView();
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

        reviewsItemAdapter = new ReviewsItemAdapter(this, R.layout.detail_item_reviews, arrReviewsItemData);
        lvReviewsItem.setAdapter(reviewsItemAdapter);
    }
}