package com.example.hotelbooking_app.Searching.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Domain.PopularSearchDomain;
import com.example.hotelbooking_app.Searching.Domain.ReviewsItemDomain;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ReviewsItemAdapter extends RecyclerView.Adapter<ReviewsItemAdapter.reviewsItemHolder> {
    ArrayList<ReviewsItemDomain> arrReviewsItemData;

    public ReviewsItemAdapter(ArrayList<ReviewsItemDomain> arrReviewsItemData) {
        this.arrReviewsItemData = arrReviewsItemData;
    }

    @NonNull
    @Override
    public ReviewsItemAdapter.reviewsItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item_reviews, parent, false);
        return new reviewsItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsItemAdapter.reviewsItemHolder holder, int position) {
    holder.tvName.setText(arrReviewsItemData.get(position).getName());
    holder.tvContent.setText(arrReviewsItemData.get(position).getContent());
    holder.tvScore.setText("" + arrReviewsItemData.get(position).getScore());
    holder.imgAvatar.setImageResource(arrReviewsItemData.get(position).getAvatarUrl());
    }

    @Override
    public int getItemCount() {
        return arrReviewsItemData.size();
    }

    public class reviewsItemHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvContent, tvScore;
        ImageView imgAvatar;


        public reviewsItemHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.item_tv_reviews_name);
            tvContent = itemView.findViewById(R.id.item_tv_reviews_content);
            tvScore = itemView.findViewById(R.id.item_tv_reviews_score);
            imgAvatar = itemView.findViewById(R.id.item_img_reviews_avatar);
        }
    }
}
