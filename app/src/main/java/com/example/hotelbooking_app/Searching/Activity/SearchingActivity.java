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
import android.widget.TextView;
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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SearchingActivity extends AppCompatActivity implements PopularHotelApiCallAsyncTask.ApiCallListener {
    private static final int MAX_LAST_SEARCH_ITEMS = 5;

    androidx.appcompat.widget.SearchView searchView;
    ListView lvPopularSearch;
    RecyclerView rvLastSearch, rvRecentlyViewed;
    LastSearchAdapter lastSearchAdapter;
    PopularHotelAdapter mPopularHotelAdapter;
    PopularSearchAdapter popularSearchAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searching_layout);

        searchView = findViewById(R.id.searching_ed_search_box);

        rvLastSearch = findViewById(R.id.searching_rv_last_search);
        initLastSearchRecyclerView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public boolean onQueryTextSubmit(String query) {
                //  Store keywords to LastSearchAdapter
                ArrayList<LastSearchDomain> existingSearchData = loadLastSearchData();
                existingSearchData.add(new LastSearchDomain(query));
                saveLastSearchData(existingSearchData);
                lastSearchAdapter.updateData(existingSearchData);


                //  Intent to SearchingResultsActivity with hotelId extra
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



        rvRecentlyViewed = findViewById(R.id.searching_rv_recently_viewed);
        getAllPopularHotels();

        lvPopularSearch = findViewById(R.id.searching_rv_popular_search);
        initPopularSearchListView();

        TextView clearAllBtn = findViewById(R.id.searching_tv_clear_all);
        clearAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearLastSearchData();
                updateLastSearchRecyclerView();
            }
        });

        //  Intent to HomescreenActivity
        ImageButton returnHomeBtn = findViewById(R.id.searching_back_button);
        returnHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchingActivity.this, HomescreenActivity.class);
                startActivity(intent);
            }
        });
        // End onCreate()
    }

    @SuppressLint("NotifyDataSetChanged")
    private void updateLastSearchRecyclerView() {
        ArrayList<LastSearchDomain> updatedList = loadLastSearchData();
        lastSearchAdapter.setData(updatedList);
        lastSearchAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Clear Successfully", Toast.LENGTH_SHORT).show();
    }

    private void clearLastSearchData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("lastSearchData");
        editor.apply();
    }

    private ArrayList<LastSearchDomain> loadLastSearchData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("lastSearchData", null);

        Type type = new TypeToken<ArrayList<LastSearchDomain>>() {}.getType();
        if (json != null) {
            ArrayList<LastSearchDomain> lastSearchList = gson.fromJson(json, type);
            return lastSearchList;
        } else {
            return new ArrayList<>();
        }
    }

    private void saveLastSearchData(ArrayList<LastSearchDomain> lastSearchList) {
        if (lastSearchList.size() > MAX_LAST_SEARCH_ITEMS) {
            lastSearchList = new ArrayList<>(lastSearchList.subList(1, MAX_LAST_SEARCH_ITEMS + 1));
        }
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(lastSearchList);
        editor.putString("lastSearchData", json);
        editor.apply();
    }



    private void initLastSearchRecyclerView() {
        ArrayList<LastSearchDomain> arrLastSearchData = loadLastSearchData();;
        Collections.reverse(arrLastSearchData);
        lastSearchAdapter = new LastSearchAdapter(this, arrLastSearchData);
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