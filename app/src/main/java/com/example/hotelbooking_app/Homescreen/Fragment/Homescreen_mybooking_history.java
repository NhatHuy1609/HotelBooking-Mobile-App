package com.example.hotelbooking_app.Homescreen.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hotelbooking_app.Homescreen.Adapter.Homescreen_NearbyhotelAdapter;
import com.example.hotelbooking_app.Homescreen.HotelApiService.Hotel;
import com.example.hotelbooking_app.Homescreen.HotelApiService.HotelApiClient;
import com.example.hotelbooking_app.Homescreen.HotelApiService.HotelsApiResponse;
import com.example.hotelbooking_app.Homescreen.HotelApiService.HotelEndpoint;
import com.example.hotelbooking_app.Homescreen.HotelApiService.ImageDetail;
import com.example.hotelbooking_app.Homescreen.Hotels.Homescreen_Nearbyhotel;
import com.example.hotelbooking_app.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class Homescreen_mybooking_history extends Fragment {
    LinearLayout lnHistory;
    ArrayList<Homescreen_Nearbyhotel> arrayHistory;
    Homescreen_NearbyhotelAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.homescreen_mybooking_fragment_history, container, false);
        arrayHistory = new ArrayList<>();
        new HotelsAsyncTask().execute();

        adapter = new Homescreen_NearbyhotelAdapter(getActivity(),R.layout.homescreen_item_nearbyhotel, arrayHistory);
        lnHistory = (LinearLayout) view.findViewById(R.id.lvHistoryHotel);

        for (int i=0; i< adapter.getCount(); i++) {
            View item = adapter.getView(i,null,null);
            lnHistory.addView(item);
        }
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
        Call<HotelsApiResponse> call = hotelEndpoint.getFavoriteHotels("Bearer " + jwtToken);

        try {
            Response<HotelsApiResponse> response = call.execute();
            if (response.isSuccessful()) {
                List<Hotel> apiHotels = response.body().getData();
                for (Hotel apiHotel : apiHotels) {

                    // Convert API Hotel to Homescreen_Nearbyhotel
                    double formattedRate = Math.round(apiHotel.getRate() * 10.0) / 10.0;
                    double formattedPrice = Math.round(apiHotel.getPrice() / 24237);
                    Homescreen_Nearbyhotel nearbyHotel = new Homescreen_Nearbyhotel(
                            apiHotel.getId(),
                            apiHotel.getName(),
                            apiHotel.getAddress(),
                            formattedRate,
                            apiHotel.getReviewQuantity(),
                            formattedPrice,
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
            arrayHistory.clear();
            arrayHistory.addAll(result);
            adapter.notifyDataSetChanged();
            for (int i = 0; i < adapter.getCount(); i++) {
                View ittem = adapter.getView(i, null, null);
                lnHistory.addView(ittem);
            }

            // Check and log the contents of arrayNearByHotel
            if (!arrayHistory.isEmpty()) {
                for (Homescreen_Nearbyhotel hotel : arrayHistory) {
                    Log.d("Hotel Info", "Hotel Name: " + hotel.getTen());
                    Log.d("Hotel Info", "Hotel rate: " + hotel.getDanhGia());
                    Log.d("Hotel Info", "Hotel rate: " + hotel.getSoLuongDanhGia());
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


}