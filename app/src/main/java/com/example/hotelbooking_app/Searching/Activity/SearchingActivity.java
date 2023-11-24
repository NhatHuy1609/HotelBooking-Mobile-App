package com.example.hotelbooking_app.Searching.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ImageButton;
import android.view.View;

import com.example.hotelbooking_app.Homescreen.Fragment.Homescreen_home;
import com.example.hotelbooking_app.Homescreen.HomescreenActivity;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Adapter.LastSearchAdapter;
import com.example.hotelbooking_app.Searching.Adapter.PopularSearchAdapter;
import com.example.hotelbooking_app.Searching.Adapter.RecentlyViewedAdapter;
import com.example.hotelbooking_app.Searching.Domain.LastSearchDomain;
import com.example.hotelbooking_app.Searching.Domain.PopularSearchDomain;
import com.example.hotelbooking_app.Searching.Domain.RecentlyViewedDomain;

import java.util.ArrayList;

public class SearchingActivity extends AppCompatActivity {
    androidx.appcompat.widget.SearchView searchView;
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

        searchView = findViewById(R.id.searching_ed_search_box);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(SearchingActivity.this, SearchingResultsActivity.class);
                intent.putExtra("SEARCH_QUERY", query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        rvLastSearch = findViewById(R.id.searching_rv_last_search);
        initLastSearchRecyclerView();

        rvRecentlyViewed = findViewById(R.id.searching_rv_recently_viewed);
        initRecentlyViewedRecyclerView();
        recentlyViewedAdapter.setOnItemClickListener(new RecentlyViewedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(SearchingActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        lvPopularSearch = findViewById(R.id.searching_rv_popular_search);
        initPopularSearchListView();

        ImageButton returnHomeBtn = (ImageButton) findViewById(R.id.searching_back_button);
        returnHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchingActivity.this, HomescreenActivity.class);
                startActivity(intent);
            }
        });

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
        arrRecentlyViewedData.add(new RecentlyViewedDomain("Muong Thanh Hotel", "270 Võ Nguyên Giáp, Đà Nẵng", 4, 58, R.drawable.searching_image_muongthanh));
        arrRecentlyViewedData.add(new RecentlyViewedDomain("Hai An Hotel", "155 Võ Nguyên Giáp, Đà Nẵng", 5, 122, R.drawable.searching_image_muongthanh));
        arrRecentlyViewedData.add(new RecentlyViewedDomain("Dong Duong Hotel", "54 Nguyễn Tất Thành, Đà Nẵng", 4, 32, R.drawable.searching_image_muongthanh));

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