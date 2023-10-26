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
import com.example.hotelbooking_app.Searching.Domain.ResultItemDomain;
import com.example.hotelbooking_app.Searching.Domain.ReviewsItemDomain;

import java.util.ArrayList;

public class ResultItemAdapter extends RecyclerView.Adapter<ResultItemAdapter.resultItemHolder> {
    OnItemClickListener onItemClickListener;
    ArrayList<ResultItemDomain> arrResultItemData;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ResultItemAdapter(ArrayList<ResultItemDomain> arrResultItemData) {
        this.arrResultItemData = arrResultItemData;
    }

    @NonNull
    @Override
    public ResultItemAdapter.resultItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searching_item_search_result_items, parent, false);
        return new resultItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultItemAdapter.resultItemHolder holder, int position) {
    holder.tvName.setText(arrResultItemData.get(position).getName());
    holder.tvAddress.setText(arrResultItemData.get(position).getAddress());
    holder.tvScore.setText("" + arrResultItemData.get(position).getScore());
    holder.tvCount.setText("(" + arrResultItemData.get(position).getCount() + ")");
    holder.tvPrice.setText(arrResultItemData.get(position).getPrice());
    holder.imgHotel.setImageResource(arrResultItemData.get(position).getPicUrl());
    holder.cvHotel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(position);
        }
    });
    }

    @Override
    public int getItemCount() {
        return arrResultItemData.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class resultItemHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAddress, tvScore, tvCount, tvPrice;
        ImageView imgHotel;
        CardView cvHotel;
        public resultItemHolder(@NonNull View itemView) {
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
