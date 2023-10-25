package com.example.hotelbooking_app.Review.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Review.Model.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private List<Review> reviews;

    public ReviewAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }
    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item_layout, parent, false);
        return new ReviewViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.bind(review); // Gọi phương thức bind để ánh xạ dữ liệu vào các phần tử của mẫu.
    }
    @Override
    public int getItemCount() {
        return reviews.size();
    }
    class ReviewViewHolder extends RecyclerView.ViewHolder {
        private TextView reviewerNameTextView;
        private TextView ratingTextView;
        private ImageView evaluatedStarImageView;
        private ImageView avatarReviewer;
        private TextView reviewTextTextView;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            // Ánh xạ các phần tử trong mẫu XML vào đây
            reviewerNameTextView = itemView.findViewById(R.id.review_item_view_name);
            ratingTextView = itemView.findViewById(R.id.review_item_view_rating);
            evaluatedStarImageView = itemView.findViewById(R.id.review_item_view_img);
            reviewTextTextView = itemView.findViewById(R.id.review_item_view_reviews);
            avatarReviewer=itemView.findViewById(R.id.review_item_avatar);
        }

        public void bind(Review review) {
            // Ánh xạ dữ liệu từ đánh giá vào các phần tử của mẫu
            reviewerNameTextView.setText(review.getReviewerName());
            ratingTextView.setText(String.valueOf(review.getRating()));
            evaluatedStarImageView.setImageResource(R.drawable.review_star);
            reviewTextTextView.setText(review.getReviewText());
            avatarReviewer.setImageResource(R.drawable.review_avatar_reviewer);
        }
    }
}

