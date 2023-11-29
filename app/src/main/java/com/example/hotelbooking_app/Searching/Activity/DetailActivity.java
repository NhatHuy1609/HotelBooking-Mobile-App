package com.example.hotelbooking_app.Searching.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hotelbooking_app.Booking.Activity.BookingActivity;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Review.ReviewsActivity;
import com.example.hotelbooking_app.Searching.Adapter.ReviewsItemAdapter;
import com.example.hotelbooking_app.Searching.AsyncTask.DetailHotelApiCallAsyncTask;
import com.example.hotelbooking_app.Searching.Domain.Hotel;
import com.example.hotelbooking_app.Searching.Domain.ReviewsItemDomain;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements DetailHotelApiCallAsyncTask.ApiCallListener {
    TextView tvName, tvAddress, tvOverview, tvPrice;
    RecyclerView rvReviewsItem;
    ImageButton detailBackBtn;
    ReviewsItemAdapter reviewsItemAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        Intent intent = getIntent();
        int hotelId = intent.getIntExtra("hotelId", 0);


        getDetailHotel(hotelId);

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

        AppCompatButton bookingBtn = findViewById(R.id.detail_booking_button);
        bookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, BookingActivity.class);
                intent.putExtra("hotelId", hotelId);
                startActivity(intent);
            }
        });


        TextView tvReviewsSeeAll = (TextView) findViewById(R.id.detail_tv_reviews_see_all);
        tvReviewsSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, ReviewsActivity.class);
                intent.putExtra("hotelId", hotelId);
                startActivity(intent);
            }
        });
    }

    private void getDetailHotel(int hotelId) {
        new DetailHotelApiCallAsyncTask(this, this).execute(hotelId);
    }

    @Override
    public void onApiCallSuccess(Hotel hotel) {
        if (hotel != null) {
            tvName = findViewById(R.id.detail_tv_hotel_name);
            tvAddress = findViewById(R.id.detail_tv_hotel_address);
            tvOverview = findViewById(R.id.detail_tv_overview_content);
            tvPrice = findViewById(R.id.detail_tv_price);


            tvName.setText(hotel.getName());
            tvAddress.setText(hotel.getAddress());
            tvOverview.setText(hotel.getOverview());
            tvPrice.setText("" + hotel.getPrice());

            // Load image using Picasso
            if (hotel.getImageDetails() != null && !hotel.getImageDetails().isEmpty()) {
                ImageSlider imageSlider = findViewById(R.id.detail_img_slider);
                ArrayList<SlideModel> slideModels = new ArrayList<>();

                String imageUrl1 = hotel.getImageDetails().get(1).getImg();
                String imageUrl2 = hotel.getImageDetails().get(2).getImg();
                String imageUrl3 = hotel.getImageDetails().get(3).getImg();

                slideModels.add(new SlideModel(imageUrl1, ScaleTypes.FIT));
                slideModels.add(new SlideModel(imageUrl2, ScaleTypes.FIT));
                slideModels.add(new SlideModel(imageUrl3, ScaleTypes.FIT));

                // Using Glide to load images
                for (SlideModel slideModel : slideModels) {
                    ImageView imageView = new ImageView(this);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(this).load(slideModel.getImageUrl()).into(imageView);
                }

                // Set the images to the ImageSlider
                imageSlider.setImageList(slideModels, ScaleTypes.FIT);
            } else {
            }
        }
    }

    @Override
    public void onApiCallFailure(String errorMessage) {
        Toast.makeText(this, "Api call failed", Toast.LENGTH_SHORT).show();
        Log.e("API Error", errorMessage);
    }


    private void hotelImageSlider() {
        ImageSlider imageSlider = findViewById(R.id.detail_img_slider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.searching_image_muongthanh, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.searching_image_muongthanh, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.searching_image_muongthanh, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }
}