package com.example.hotelbooking_app.Searching.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Domain.LastSearchDomain;

import java.util.ArrayList;
import java.util.List;

public class LastSearchAdapter extends RecyclerView.Adapter<LastSearchAdapter.lastSearchHolder> {
    ArrayList<LastSearchDomain> arrLastSearchData;
    public LastSearchAdapter(ArrayList<LastSearchDomain> arrLastSearchData) {
        this.arrLastSearchData = arrLastSearchData;
    }

    public ArrayList<LastSearchDomain> getDataList() {
        return arrLastSearchData;
    }

    @NonNull
    @Override
    public lastSearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searching_item_last_search, parent, false);
        return new lastSearchHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull lastSearchHolder holder, int position) {
        holder.tvName.setText(arrLastSearchData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return arrLastSearchData.size();
    }

    public void updateData(ArrayList<LastSearchDomain> newData) {
        this.arrLastSearchData = newData;
    }

    public void addLastSearch(LastSearchDomain lastSearch) {
        arrLastSearchData.add(lastSearch);
        notifyItemInserted(arrLastSearchData.size() - 1);
    }

    public class lastSearchHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public lastSearchHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.item_tv_last_search);
        }
    }
}
