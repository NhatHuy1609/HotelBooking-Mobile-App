package com.example.hotelbooking_app.Searching.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Domain.PopularSearchDomain;

import java.util.ArrayList;

public class PopularSearchAdapter extends ArrayAdapter<PopularSearchDomain> {
    Activity context;
    int IdLayout;
    ArrayList<PopularSearchDomain> data;

    public PopularSearchAdapter(Activity context, int IdLayout, ArrayList<PopularSearchDomain> data) {
        super(context, IdLayout, data);
        this.context = context;
        this.data = data;
    }

    @NonNull
    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.searching_item_high_rating_hotel, parent, false);

        //Lay 1 phan tu trong mang


        TextView tvName = convertView.findViewById(R.id.item_tv_high_rating_hotel_name);
        TextView tvCount = convertView.findViewById(R.id.item_tv_high_rating_hotel_rate);
        ImageView hotelImg = convertView.findViewById(R.id.item_img_high_rating_hotel);

        PopularSearchDomain pplSearch = data.get(position);

        tvName.setText(pplSearch.getName());
        tvCount.setText(pplSearch.getCount() + " Search today");
        hotelImg.setImageResource(pplSearch.getPicUrl());

        return convertView;
    }
}
