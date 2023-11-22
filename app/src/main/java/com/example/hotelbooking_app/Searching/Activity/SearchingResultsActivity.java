package com.example.hotelbooking_app.Searching.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Adapter.ResultFilterAdapter;
import com.example.hotelbooking_app.Searching.Adapter.ResultItemAdapter;
import com.example.hotelbooking_app.Searching.Domain.ResultFilterDomain;
import com.example.hotelbooking_app.Searching.Domain.ResultItemDomain;

import java.util.ArrayList;

public class SearchingResultsActivity extends AppCompatActivity {
    ResultFilterAdapter resultFilterAdapter;
    ResultItemAdapter resultItemAdapter;
    RecyclerView rvResultFilter, rvResultItem;
    androidx.appcompat.widget.SearchView searchView;
    ArrayList<ResultItemDomain> arrResultItemData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searching_results_layout);

        rvResultFilter = findViewById(R.id.searching_rv_search_result_filter);
        innitResultFilterRecyclerView();

        rvResultItem = findViewById(R.id.searching_rv_search_result_items);
        innitResultItemRecyclerView();

        searchView = findViewById(R.id.searching_ed_result_search_view);




        //Receiving data in Searching activity
        Intent intent = getIntent();
        if (intent != null) {
            String searchQuery = intent.getStringExtra("SEARCH_QUERY");
            searchView.setQuery(searchQuery, false);

            ArrayList<ResultItemDomain> allResults = arrResultItemData;
            ArrayList<ResultItemDomain> filteredResults = filterResults(allResults, searchQuery);

            resultItemAdapter = new ResultItemAdapter(filteredResults);
            rvResultItem.setAdapter(resultItemAdapter);
        }

        resultItemAdapter.setOnItemClickListener(new ResultItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(SearchingResultsActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        ImageButton resultBackBtn = (ImageButton) findViewById(R.id.searching_result_back_button);
        resultBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchingResultsActivity.this, SearchingActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<ResultItemDomain> filterResults(ArrayList<ResultItemDomain> allResults, String searchQuery) {
        ArrayList<ResultItemDomain> filteredResults = new ArrayList<>();

        for (ResultItemDomain result : allResults) {
            if (result.getName().toLowerCase().contains(searchQuery.toLowerCase())) {
                filteredResults.add(result);
            }
        }

        return filteredResults;
    }

    private void innitResultItemRecyclerView() {
        arrResultItemData = new ArrayList<>();
        arrResultItemData.add(new ResultItemDomain("Muong Thanh Luxury Hotel", "270 Vo Nguyen Giap, Da Nang", "$49.00", 4.5, 25, R.drawable.searching_image_muongthanh));
        arrResultItemData.add(new ResultItemDomain("HAI AN HOTEL", "270 Vo Nguyen Giap, Da Nang", "$49.00", 4.5, 25, R.drawable.searching_image_muongthanh));
        arrResultItemData.add(new ResultItemDomain("Muong Thanh Luxury Hotel", "270 Vo Nguyen Giap, Da Nang", "$49.00", 4.5, 25, R.drawable.searching_image_muongthanh));
        arrResultItemData.add(new ResultItemDomain("Muong Thanh Luxury Hotel", "270 Vo Nguyen Giap, Da Nang", "$49.00", 4.5, 25, R.drawable.searching_image_muongthanh));
        arrResultItemData.add(new ResultItemDomain("Muong Thanh Luxury Hotel", "270 Vo Nguyen Giap, Da Nang", "$49.00", 4.5, 25, R.drawable.searching_image_muongthanh));
        arrResultItemData.add(new ResultItemDomain("Muong Thanh Luxury Hotel", "270 Vo Nguyen Giap, Da Nang", "$49.00", 4.5, 25, R.drawable.searching_image_muongthanh));
        arrResultItemData.add(new ResultItemDomain("Muong Thanh Luxury Hotel", "270 Vo Nguyen Giap, Da Nang", "$49.00", 4.5, 25, R.drawable.searching_image_muongthanh));
        arrResultItemData.add(new ResultItemDomain("Muong Thanh Luxury Hotel", "270 Vo Nguyen Giap, Da Nang", "$49.00", 4.5, 25, R.drawable.searching_image_muongthanh));
        arrResultItemData.add(new ResultItemDomain("Muong Thanh Luxury Hotel", "270 Vo Nguyen Giap, Da Nang", "$49.00", 4.5, 25, R.drawable.searching_image_muongthanh));
        arrResultItemData.add(new ResultItemDomain("Muong Thanh Luxury Hotel", "270 Vo Nguyen Giap, Da Nang", "$49.00", 4.5, 25, R.drawable.searching_image_muongthanh));

        resultItemAdapter = new ResultItemAdapter(arrResultItemData);

        rvResultItem.setLayoutManager(new GridLayoutManager(this, 2));
        rvResultItem.setAdapter(resultItemAdapter);
    }

    private void innitResultFilterRecyclerView() {
        ArrayList<ResultFilterDomain> arrResultFilterData = new ArrayList<>();
        arrResultFilterData.add(new ResultFilterDomain("Latest"));
        arrResultFilterData.add(new ResultFilterDomain("Most Popular"));
        arrResultFilterData.add(new ResultFilterDomain("Cheapest"));
        arrResultFilterData.add(new ResultFilterDomain("High Rating"));

        resultFilterAdapter = new ResultFilterAdapter(arrResultFilterData);

        rvResultFilter.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rvResultFilter.setAdapter(resultFilterAdapter);


    }
}