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

import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Domain.PopularSearchDomain;
import com.example.hotelbooking_app.Searching.Domain.ReviewsItemDomain;

import java.util.ArrayList;


public class ReviewsItemAdapter extends ArrayAdapter<ReviewsItemDomain> {
    Activity context;
    int IdLayout;
    ArrayList<ReviewsItemDomain> arrReviewsItemData;

    public ReviewsItemAdapter(Activity context, int idLayout, ArrayList<ReviewsItemDomain> arrReviewsItemData) {
        super(context, idLayout, arrReviewsItemData);
        this.context = context;
        IdLayout = idLayout;
        this.arrReviewsItemData = arrReviewsItemData;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.detail_item_reviews, parent, false);

        TextView tvName = convertView.findViewById(R.id.item_tv_reviews_name);
        TextView tvScore = convertView.findViewById(R.id.item_tv_reviews_score);
        TextView tvContent = convertView.findViewById(R.id.item_tv_reviews_content);
        ImageView avatarImg = convertView.findViewById(R.id.item_img_reviews_avatar);

        //Lay 1 phan tu trong mang
        ReviewsItemDomain reviewItem = arrReviewsItemData.get(position);

        tvName.setText(reviewItem.getName());
        tvScore.setText("" + reviewItem.getScore());
        tvContent.setText(reviewItem.getContent());
        avatarImg.setImageResource(reviewItem.getAvatarUrl());

        return convertView;
    }
}
