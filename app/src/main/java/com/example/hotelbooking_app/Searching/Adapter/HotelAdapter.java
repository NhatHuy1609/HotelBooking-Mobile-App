package com.example.hotelbooking_app.Searching.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Domain.Hotel;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.myViewHolder> {
    List<Hotel> mHotelList;

    public HotelAdapter(List<Hotel> mHotelList) {
        this.mHotelList = mHotelList;
    }

    @NonNull
    @Override
    public HotelAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searching_item_search_result_items, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelAdapter.myViewHolder holder, int position) {
        holder.tvName.setText(mHotelList.get(position).getName());
        holder.tvAddress.setText(mHotelList.get(position).getAddress());
        holder.tvPrice.setText(mHotelList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return mHotelList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAddress, tvScore, tvCount, tvPrice;
        ImageView imgHotel;
        CardView cvHotel;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.item_tv_search_result_name);
            tvAddress = itemView.findViewById(R.id.item_tv_search_result_address);
            tvScore = itemView.findViewById(R.id.item_tv_search_result_score);
            tvCount = itemView.findViewById(R.id.item_tv_result_item_count);
            tvPrice = itemView.findViewById(R.id.item_tv_search_result_price);
            imgHotel = itemView.findViewById(R.id.item_img_search_result);
            cvHotel = itemView.findViewById(R.id.item_cv_search_result);
        }
    }
}
