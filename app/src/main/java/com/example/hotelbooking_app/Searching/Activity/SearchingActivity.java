package com.example.hotelbooking_app.Searching.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ImageButton;
import android.view.View;
import android.widget.Toast;

import com.example.hotelbooking_app.Homescreen.Fragment.Homescreen_home;
import com.example.hotelbooking_app.Homescreen.HomescreenActivity;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.API.PopularHotelApiService;
import com.example.hotelbooking_app.Searching.Adapter.LastSearchAdapter;
import com.example.hotelbooking_app.Searching.Adapter.PopularHotelAdapter;
import com.example.hotelbooking_app.Searching.Adapter.PopularSearchAdapter;
import com.example.hotelbooking_app.Searching.Adapter.RecentlyViewedAdapter;
import com.example.hotelbooking_app.Searching.AsyncTask.PopularHotelApiCallAsyncTask;
import com.example.hotelbooking_app.Searching.Domain.LastSearchDomain;
import com.example.hotelbooking_app.Searching.Domain.PopularHotel;
import com.example.hotelbooking_app.Searching.Domain.PopularSearchDomain;
import com.example.hotelbooking_app.Searching.Domain.RecentlyViewedDomain;

import java.util.ArrayList;
import java.util.List;

public class SearchingActivity extends AppCompatActivity implements PopularHotelApiCallAsyncTask.ApiCallListener {
    androidx.appcompat.widget.SearchView searchView;
    ListView lvPopularSearch;
    RecyclerView rvLastSearch, rvRecentlyViewed;
    LastSearchAdapter lastSearchAdapter;
    RecentlyViewedAdapter recentlyViewedAdapter;
    PopularHotelAdapter mPopularHotelAdapter;
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


        getAllPopularHotels();
//        initRecentlyViewedRecyclerView();
//        recentlyViewedAdapter.setOnItemClickListener(new RecentlyViewedAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Intent intent = new Intent(SearchingActivity.this, DetailActivity.class);
//                startActivity(intent);
//            }
//        });

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

    private void initPopularSearchListView() {
        ArrayList<PopularSearchDomain> arrPopularSearchData = new ArrayList<>();

        //Setting the popular search data source
        arrPopularSearchData.add(new PopularSearchDomain("Muong Thanh", 1244, R.drawable.searching_image_muongthanh));
        arrPopularSearchData.add(new PopularSearchDomain("Muong Thanh", 1244, R.drawable.searching_image_muongthanh));
        arrPopularSearchData.add(new PopularSearchDomain("Muong Thanh", 1244, R.drawable.searching_image_muongthanh));

        popularSearchAdapter = new PopularSearchAdapter(this, R.layout.searching_item_popular_search, arrPopularSearchData);
        lvPopularSearch.setAdapter(popularSearchAdapter);
    }

    private void getAllPopularHotels() {
        new PopularHotelApiCallAsyncTask(this, this).execute();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onApiCallSuccess(List<PopularHotel> popularHotels) {
        if (popularHotels != null) {
            mPopularHotelAdapter = new PopularHotelAdapter(popularHotels);
            mPopularHotelAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Api call successfully", Toast.LENGTH_SHORT).show();
            rvRecentlyViewed.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
            rvRecentlyViewed.setAdapter(mPopularHotelAdapter);
        }
    }

    @Override
    public void onApiCallFailure(String errorMessage) {
        Log.e("API Error", errorMessage);
    }
}