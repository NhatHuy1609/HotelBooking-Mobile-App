package com.example.hotelbooking_app.Review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Review.Adapter.ReviewAdapter;
import com.example.hotelbooking_app.Review.Fragment.ReviewCommentFragment;
import com.example.hotelbooking_app.Review.Model.Review;
import com.example.hotelbooking_app.Searching.Activity.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ReviewsActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openReview();
        imageView=findViewById(R.id.review_back_icon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ReviewsActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        openFragment();
    }

    private void openFragment() {
        ReviewCommentFragment fragment= new ReviewCommentFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    private void openReview() {
        setContentView(R.layout.evaluatting_layout);
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review("Mộng Di đại sư 01", 3.5f, "Amazing. The room is good. I am Stephen Chow's close friend. Can you give me a discount?"));
        reviews.add(new Review("Mộng Di đại sư 02", 3.5f, "Amazing. The room is good. I am Stephen Chow's close friend. Can you give me a discount?"));
        reviews.add(new Review("Mộng Di đại sư 03", 3.5f, "Amazing. The room is good. I am Stephen Chow's close friend. Can you give me a discount?"));
        reviews.add(new Review("Mộng Di đại sư 04", 3.5f, "Amazing. The room is good. I am Stephen Chow's close friend. Can you give me a discount?"));        reviews.add(new Review("Mộng Di đại sư", 3.5f, "Amazing. The room is good. I am Stephen Chow's close friend. Can you give me a discount?"));
        reviews.add(new Review("Mộng Di đại sư 05", 3.5f, "Amazing. The room is good. I am Stephen Chow's close friend. Can you give me a discount?"));        reviews.add(new Review("Mộng Di đại sư", 3.5f, "Amazing. The room is good. I am Stephen Chow's close friend. Can you give me a discount?"));
        reviews.add(new Review("Mộng Di đại sư 06", 3.5f, "Amazing. The room is good. I am Stephen Chow's close friend. Can you give me a discount?"));        reviews.add(new Review("Mộng Di đại sư", 3.5f, "Amazing. The room is good. I am Stephen Chow's close friend. Can you give me a discount?"));

        RecyclerView recyclerView = findViewById(R.id.review_recycleView);
        ReviewAdapter adapter = new ReviewAdapter(reviews);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Quy định cách dữ liệu sẽ được hiển thị (theo danh sách, lưới, vv).
        Float rate=computeRating(reviews);
        setStarRating(rate);
    }

    private Float computeRating(List<Review> reviews) {
        float totalRating=reviews.stream().map(Review::getRating).reduce(0.f, (a,b) -> a+b);
        return  totalRating/((float)reviews.size());
    }

    private void setStarRating(float userRating) {
        // Đánh giá từ người dùng, ví dụ: 3.4

        // Số ngôi sao cần tô màu (ví dụ: 3.4 sẽ tô màu 4 ngôi sao)
        TextView textView=findViewById(R.id.review_StarNumber);
        textView.setText(userRating+"");
        int numberOfStarsToColor = (int) Math.ceil(userRating);

        // Tô màu các ngôi sao tương ứng
        for (int i = 1; i <= 5; i++) {
            ImageView starImageView = findViewById(getResources().getIdentifier("review_star_0" + i, "id", getPackageName()));

            if (i <= numberOfStarsToColor) {
                // Tô màu ngôi sao
                starImageView.setImageResource(R.drawable.review_star);
            } else {
                // Để lại ngôi sao không được tô màu
                starImageView.setImageResource(R.drawable.review_star_regular);
            }
        }
    }
}