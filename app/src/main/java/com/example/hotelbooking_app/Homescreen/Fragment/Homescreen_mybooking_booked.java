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

import com.example.hotelbooking_app.Homescreen.HotelApiService.Booked;
import com.example.hotelbooking_app.Homescreen.HotelApiService.BookedApiResponse;
import com.example.hotelbooking_app.Homescreen.HotelApiService.Hotel;
import com.example.hotelbooking_app.Homescreen.HotelApiService.HotelApiClient;
import com.example.hotelbooking_app.Homescreen.HotelApiService.HotelApiResponse;
import com.example.hotelbooking_app.Homescreen.HotelApiService.HotelsApiResponse;
import com.example.hotelbooking_app.Homescreen.HotelApiService.HotelEndpoint;
import com.example.hotelbooking_app.Homescreen.HotelApiService.ImageDetail;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Homescreen.Adapter.Homescreen_BookedAdapter;
import com.example.hotelbooking_app.Homescreen.Hotels.Homescreen_Booked;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class Homescreen_mybooking_booked extends Fragment {
    LinearLayout lnBookedHotel;
    ArrayList<Homescreen_Booked> arrayBookedHotel;
    Homescreen_BookedAdapter  adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homescreen_mybooking_fragment_booked, container, false);
        arrayBookedHotel = new ArrayList<>();
        new BookedsAsyncTask().execute();

        adapter = new Homescreen_BookedAdapter(getActivity(),R.layout.homescreen_item_booked, arrayBookedHotel);

        lnBookedHotel = (LinearLayout) view.findViewById(R.id.lvBookedHotel);


        return view;
    }
    private class BookedsAsyncTask extends AsyncTask<Void, Void, List<Homescreen_Booked>> {
        @Override
        protected List<Homescreen_Booked> doInBackground(Void... voids) {
            List<Homescreen_Booked> result = new ArrayList<>();

            // Retrofit network request
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String jwtToken = sharedPreferences.getString("jwtKey", null);

            HotelEndpoint hotelEndpoint = HotelApiClient.getClient().create(HotelEndpoint.class);
            Call<BookedApiResponse> call = hotelEndpoint.getBooked("Bearer " + jwtToken);

            try {
                Response<BookedApiResponse> response = call.execute();
                if (response.isSuccessful()) {
                    List<Booked> apiBookeds = response.body().getData();
                    for (Booked apiBooked : apiBookeds) {
                        Call<HotelApiResponse> hotelCall = hotelEndpoint.getHotel(apiBooked.getHotelId(),"Bearer " + jwtToken);
                        Response<HotelApiResponse> hotelResponse = hotelCall.execute();
                        if (hotelResponse.isSuccessful()) {
                            Hotel apiHotel = hotelResponse.body().getData();
                            // Convert API Hotel to Homescreen_Booked
                            double formattedRate = Math.round(apiBooked.getHotelRate() * 10.0) / 10.0;
                            double formattedPrice = Math.round(apiHotel.getPrice() / 24237);
                            Homescreen_Booked bookedHotel = new Homescreen_Booked(
                                    apiHotel.getName(),
                                    apiHotel.getAddress(),
                                    formattedRate,
                                    apiBooked.getReviewQuantity(),
                                    formattedPrice,
                                    getHinhFromImageDetails(apiHotel.getImageDetails()),
                                    apiBooked.getStartDate(),
                                    apiBooked.getEndDate()

                            );
                            // Add to result list
                            result.add(bookedHotel);

                        }



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
        protected void onPostExecute(List<Homescreen_Booked> result) {
            if (result != null) {
                arrayBookedHotel.clear();
                arrayBookedHotel.addAll(result);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < adapter.getCount(); i++) {
                    View ittem = adapter.getView(i, null, null);
                    lnBookedHotel.addView(ittem);
                }

                // Check and log the contents of arrayNearByHotel
                if (!arrayBookedHotel.isEmpty()) {
                    for (Homescreen_Booked hotel : arrayBookedHotel) {
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