package com.example.hotelbooking_app.Searching.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.hotelbooking_app.Homescreen.HomescreenActivity;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Adapter.LastSearchAdapter;
import com.example.hotelbooking_app.Searching.Adapter.PopularHotelAdapter;
import com.example.hotelbooking_app.Searching.Adapter.PopularSearchAdapter;
import com.example.hotelbooking_app.Searching.AsyncTask.PopularHotelApiCallAsyncTask;
import com.example.hotelbooking_app.Searching.Domain.Hotel;
import com.example.hotelbooking_app.Searching.Domain.LastSearchDomain;
import com.example.hotelbooking_app.Searching.Domain.PopularHotel;
import com.example.hotelbooking_app.Searching.Domain.PopularSearchDomain;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchingActivity extends AppCompatActivity implements PopularHotelApiCallAsyncTask.ApiCallListener {
    androidx.appcompat.widget.SearchView searchView;
    ListView lvPopularSearch;
    RecyclerView rvLastSearch, rvRecentlyViewed;
    LastSearchAdapter lastSearchAdapter;
    PopularHotelAdapter mPopularHotelAdapter;
    PopularSearchAdapter popularSearchAdapter;
    ArrayList<LastSearchDomain> lastSearchList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searching_layout);

        lastSearchList = new ArrayList<>();

        searchView = findViewById(R.id.searching_ed_search_box);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(SearchingActivity.this, SearchingResultsActivity.class);
                intent.putExtra("SEARCH_QUERY", query);

                ArrayList<LastSearchDomain> existingSearchData = loadLastSearchData();

                // Add the new query to the existing list
                existingSearchData.add(new LastSearchDomain(query));

                saveLastSearchData(existingSearchData);

                // Update your RecyclerView and adapter
                lastSearchAdapter.updateData(existingSearchData);

                lastSearchAdapter.notifyDataSetChanged();

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

    private ArrayList<LastSearchDomain> loadLastSearchData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("lastSearchData", null);

        Type type = new TypeToken<ArrayList<LastSearchDomain>>() {}.getType();
        if (json != null) {
            return gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }

    private void saveLastSearchData(ArrayList<LastSearchDomain> lastSearchList) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(lastSearchList);
        editor.putString("lastSearchData", json);
        editor.apply();
    }

    private void initLastSearchRecyclerView() {
        ArrayList<LastSearchDomain> arrLastSearchData = loadLastSearchData();;

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
    public void onApiCallSuccess(List<Hotel> popularHotels) {
        if (popularHotels != null) {
            mPopularHotelAdapter = new PopularHotelAdapter(this, popularHotels);
            mPopularHotelAdapter.notifyDataSetChanged();
            rvRecentlyViewed.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
            rvRecentlyViewed.setAdapter(mPopularHotelAdapter);
        }
    }

    @Override
    public void onApiCallFailure(String errorMessage) {
        Log.e("API Error", errorMessage);
    }
}