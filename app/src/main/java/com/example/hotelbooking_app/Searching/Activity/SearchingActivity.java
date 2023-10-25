package com.example.hotelbooking_app.Searching.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Adapter.LastSearchAdapter;
import com.example.hotelbooking_app.Searching.Adapter.PopularSearchAdapter;
import com.example.hotelbooking_app.Searching.Adapter.RecentlyViewedAdapter;
import com.example.hotelbooking_app.Searching.Domain.LastSearchDomain;
import com.example.hotelbooking_app.Searching.Domain.PopularSearchDomain;
import com.example.hotelbooking_app.Searching.Domain.RecentlyViewedDomain;

import java.util.ArrayList;

public class SearchingActivity extends AppCompatActivity {
    ListView lvPopularSearch;
    RecyclerView rvLastSearch, rvRecentlyViewed;
    LastSearchAdapter lastSearchAdapter;
    RecentlyViewedAdapter recentlyViewedAdapter;
    PopularSearchAdapter popularSearchAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searching_layout);

        rvLastSearch = findViewById(R.id.searching_rv_last_search);
        initLastSearchRecyclerView();

        rvRecentlyViewed = findViewById(R.id.searching_rv_recently_viewed);
        initRecentlyViewedRecyclerView();

        lvPopularSearch = findViewById(R.id.searching_lv_popular_search);
        initPopularSearchListView();


    }



    private void initLastSearchRecyclerView() {
        ArrayList<LastSearchDomain> arrLastSearchData;

        //Setting the last search data source
        arrLastSearchData = new ArrayList<>();
        arrLastSearchData.add(new LastSearchDomain("hotel"));
        arrLastSearchData.add(new LastSearchDomain("muong thanh"));
        arrLastSearchData.add(new LastSearchDomain("da nang"));
        arrLastSearchData.add(new LastSearchDomain("khach san 5 sao"));

        lastSearchAdapter = new LastSearchAdapter(arrLastSearchData);
        rvLastSearch.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rvLastSearch.setAdapter(lastSearchAdapter);
    }

    private void initRecentlyViewedRecyclerView() {
        ArrayList<RecentlyViewedDomain> arrRecentlyViewedData;

        //Setting the recently viewed data source
        arrRecentlyViewedData = new ArrayList<>();
        arrRecentlyViewedData.add(new RecentlyViewedDomain("Muong Thanh Hotel", "270 Võ Nguyên Giáp, Đà Nẵng", 4, 58));
        arrRecentlyViewedData.add(new RecentlyViewedDomain("Hai An Hotel", "155 Võ Nguyên Giáp, Đà Nẵng", 5, 122));
        arrRecentlyViewedData.add(new RecentlyViewedDomain("Dong Duong Hotel", "54 Nguyễn Tất Thành, Đà Nẵng", 4, 32));

        recentlyViewedAdapter = new RecentlyViewedAdapter(arrRecentlyViewedData);
        rvRecentlyViewed.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rvRecentlyViewed.setAdapter(recentlyViewedAdapter);
    }

    private void initPopularSearchListView() {
        ArrayList<PopularSearchDomain> arrPopularSearchData = new ArrayList<>();

        //Setting the popular search data source
        arrPopularSearchData.add(new PopularSearchDomain("Muong Thanh", 1244, R.drawable.searching_image_muongthanh));
        arrPopularSearchData.add(new PopularSearchDomain("Muong Thanh", 1244, R.drawable.searching_image_muongthanh));
        arrPopularSearchData.add(new PopularSearchDomain("Muong Thanh", 1244, R.drawable.searching_image_muongthanh));

        popularSearchAdapter = new PopularSearchAdapter(this, R.layout.searching_item_popular_search, arrPopularSearchData);
        lvPopularSearch.setAdapter(popularSearchAdapter);
    }
}