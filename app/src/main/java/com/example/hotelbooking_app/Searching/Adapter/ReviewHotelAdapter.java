package com.example.hotelbooking_app.Searching.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Domain.Hotel;
import com.example.hotelbooking_app.Searching.Domain.Review;

import java.util.List;

public class ReviewHotelAdapter extends RecyclerView.Adapter<ReviewHotelAdapter.myViewHolder> {
    Context context;
    List<Review> reviews;

    public ReviewHotelAdapter(Context context, List<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewHotelAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item_reviews, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHotelAdapter.myViewHolder holder, int position) {
        Review review = reviews.get(position);

        // Set other data to the views
        holder.tvName.setText("Unknown");
        holder.tvRaing.setText("" + review.getRate());
        holder.tvContent.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class myViewHolder  extends RecyclerView.ViewHolder {
        TextView tvName, tvContent, tvRaing;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.item_tv_reviews_name);
            tvContent = itemView.findViewById(R.id.item_tv_reviews_content);
            tvRaing = itemView.findViewById(R.id.item_tv_reviews_score);
        }
    }
}
