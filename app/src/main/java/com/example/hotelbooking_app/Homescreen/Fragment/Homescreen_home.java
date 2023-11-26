package com.example.hotelbooking_app.Homescreen.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import android.widget.ImageView;
import android.graphics.Color;
import android.view.ViewTreeObserver;
import android.content.Intent;
import com.example.hotelbooking_app.Homescreen.Activity.Homescreen_myprofile;
import com.example.hotelbooking_app.Homescreen.Adapter.Homescreen_NearbyhotelAdapter;
import com.example.hotelbooking_app.Homescreen.Adapter.Homescreen_PopularHotelAdapter;
import com.example.hotelbooking_app.Homescreen.HotelApiService.Hotel;
import com.example.hotelbooking_app.Homescreen.HotelApiService.HotelApiClient;
import com.example.hotelbooking_app.Homescreen.HotelApiService.HotelApiResponse;
import com.example.hotelbooking_app.Homescreen.HotelApiService.HotelEndpoint;
import com.example.hotelbooking_app.Homescreen.HotelApiService.ImageDetail;
import com.example.hotelbooking_app.Homescreen.HotelApiService.RatingApiResponse;
import com.example.hotelbooking_app.Homescreen.HotelApiService.Review;
import com.example.hotelbooking_app.Homescreen.HotelApiService.ReviewApiResponse;
import com.example.hotelbooking_app.Homescreen.Hotels.Homescreen_Nearbyhotel;
import com.example.hotelbooking_app.Homescreen.Hotels.Homescreen_PopularHotel;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Activity.DetailActivity;
import com.example.hotelbooking_app.Searching.Activity.SearchingActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Response;


public class Homescreen_home extends Fragment {
    HorizontalScrollView horizontalScrollView;
    LinearLayout lnNearbyHotel,lnPopularHotel,lnLocation;
    TextView nearbyHotels,txtLocation;
    ScrollView scrollview;

    ImageView btn_seach;
    RelativeLayout btn_acc;
    BottomNavigationView bottomNavigationView;
    ArrayList<Homescreen_Nearbyhotel> arrayNearByHotel;
    ArrayList<Homescreen_PopularHotel> arrayPopularHotel;
    Homescreen_NearbyhotelAdapter adapter;
    Homescreen_PopularHotelAdapter adapter_1;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homescreen_fragment_home, container, false);


        arrayNearByHotel = new ArrayList<>();
        arrayPopularHotel = new ArrayList<>();

        new HotelsAsyncTask().execute();

        adapter = new Homescreen_NearbyhotelAdapter(getActivity(), R.layout.homescreen_item_nearbyhotel, arrayNearByHotel);





        adapter_1 = new Homescreen_PopularHotelAdapter(getActivity(), R.layout.homescreen_item_popularhotel, arrayPopularHotel);

        horizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.homescreen_horizontal_scroll_view);

        lnNearbyHotel = (LinearLayout) view.findViewById(R.id.home_lvNearbyHotel);
        Log.d("Layout Child Count", "Child count: " + lnNearbyHotel.getChildCount());

//        lnPopularHotel = (LinearLayout) view.findViewById(R.id.home_lvpopularhotel);
        lnPopularHotel = (LinearLayout) horizontalScrollView.getChildAt(0);


        for (int i = 0; i < adapter_1.getCount(); i++) {
            View ittem = adapter_1.getView(i, null, null);
            lnPopularHotel.addView(ittem);
        }

        //ImageButton accout
        btn_acc = (RelativeLayout) view.findViewById(R.id.home_btn_acc);

        // Set a click listener for the button
        btn_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Homescreen_myprofile.class);
                startActivity(intent);
            }
        });



        //đổi màu textnearbyhotel
        nearbyHotels = view.findViewById(R.id.home_nearbyhotels);
        scrollview = view.findViewById(R.id.home_contentt);

        scrollview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollview.getScrollY();
                if (scrollY > (dpToPx(390)-scrollview.getHeight()/2)) {
                    nearbyHotels.setTextColor(Color.WHITE);
                } else {
                    nearbyHotels.setTextColor(Color.BLACK);
                }
            }
        });

        /* Truong Dinh Nhat code intent from Home to Detail */
        for (int i = 0; i < lnPopularHotel.getChildCount(); i++) {

            View childView = lnPopularHotel.getChildAt(i);

            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle click
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    startActivity(intent);
                }
            });
        }

         //Intent searching
        btn_seach = (ImageView) view.findViewById(R.id.home_btn_search);
        btn_seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchingActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }

    private class HotelsAsyncTask extends AsyncTask<Void, Void, List<Homescreen_Nearbyhotel>> {
        @Override
        protected List<Homescreen_Nearbyhotel> doInBackground(Void... voids) {
            List<Homescreen_Nearbyhotel> result = new ArrayList<>();

            // Retrofit network request
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String jwtToken = sharedPreferences.getString("jwtKey", null);

            HotelEndpoint hotelEndpoint = HotelApiClient.getClient().create(HotelEndpoint.class);
            Call<HotelApiResponse> call = hotelEndpoint.getHotels("Bearer " + jwtToken);

            try {
                Response<HotelApiResponse> response = call.execute();
                if (response.isSuccessful()) {
                    List<Hotel> apiHotels = response.body().getData();
                    for (Hotel apiHotel : apiHotels) {

                        // Get hotel rating
                        Call<RatingApiResponse> ratingCall = hotelEndpoint.getHotelRating(apiHotel.getId(), "Bearer " + jwtToken);
                        Response<RatingApiResponse> ratingResponse = ratingCall.execute();
                        double rating = 0.0;
                        if (ratingResponse.isSuccessful()) {
                            rating = ratingResponse.body().getData();
                        }

                        // Get hotel reviews
                        Call<ReviewApiResponse> reviewCall = hotelEndpoint.getReviews(apiHotel.getId(), "Bearer " + jwtToken);
                        Response<ReviewApiResponse> reviewResponse = reviewCall.execute();
                        int reviewCount = 0;
                        if (reviewResponse.isSuccessful()) {
                            reviewCount = reviewResponse.body().getData().size();
                        }

                        // Convert API Hotel to Homescreen_Nearbyhotel
                        Homescreen_Nearbyhotel nearbyHotel = new Homescreen_Nearbyhotel(
                                apiHotel.getName(),
                                apiHotel.getAddress(),
                                rating,
                                reviewCount,
                                apiHotel.getPrice(),
                                getHinhFromImageDetails(apiHotel.getImageDetails())
                        );

                        // Add to result list
                        result.add(nearbyHotel);
                    }
                } else {
                    Log.e("API Error", "Error response from API: " + response.message());
                }
            } catch (IOException e) {
                Log.e("API Error", "Exception during API call: " + e.getMessage());
            }

            return result;
        }

        @Override
        protected void onPostExecute(List<Homescreen_Nearbyhotel> result) {
            if (result != null) {
                arrayNearByHotel.clear();
                arrayNearByHotel.addAll(result);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < adapter.getCount(); i++) {
                    View ittem = adapter.getView(i, null, null);
                    lnNearbyHotel.addView(ittem);
                }

                // Check and log the contents of arrayNearByHotel
                if (!arrayNearByHotel.isEmpty()) {
                    for (Homescreen_Nearbyhotel hotel : arrayNearByHotel) {
                        Log.d("Hotel Info", "Hotel Name: " + hotel.getTen());
                        Log.d("Hotel Info", "Hotel rate: " + hotel.getDanhGia());
                    }
                } else {
                    Log.e("Hotel Info", "arrayNearByHotel is empty");
                }

            } else {
                Log.e("API Error", "Null response received from API");
            }
        }


    }
    // Method to extract the image URL from ImageDetails
    private Bitmap getHinhFromImageDetails(List<ImageDetail> imageDetails) {
        if (imageDetails != null && !imageDetails.isEmpty()) {
            String imageUrl = imageDetails.get(0).getImageUrl();
            // Use Picasso to load the image asynchronously and return the loaded Bitmap
            return loadBitmapWithPicasso(imageUrl);
        }
        // Return a default Bitmap if no image details are available
        return BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground);
    }


    // In your loadBitmapWithPicasso method
    private Bitmap loadBitmapWithPicasso(String imageUrl) {
        try {
            return Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.homescreen_muongthanh)  // Set a placeholder image
                    .error(R.drawable.homescreen_meroda)  // Set an error image
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }


}