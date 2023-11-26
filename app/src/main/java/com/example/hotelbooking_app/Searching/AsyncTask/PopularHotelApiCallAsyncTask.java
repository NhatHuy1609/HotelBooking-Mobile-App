package com.example.hotelbooking_app.Searching.AsyncTask;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.example.hotelbooking_app.Searching.API.PopularHotelApiRespone;
import com.example.hotelbooking_app.Searching.API.PopularHotelApiService;
import com.example.hotelbooking_app.Searching.API.PopularHotelRetrofitClient;
import com.example.hotelbooking_app.Searching.Domain.PopularHotel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class PopularHotelApiCallAsyncTask extends AsyncTask<Void, Void, List<PopularHotel>> {
    private Context context;
    private ApiCallListener listener;

    public interface ApiCallListener {
        void onApiCallSuccess(List<PopularHotel> popularHotels);
        void onApiCallFailure(String errorMessage);
    }

    public PopularHotelApiCallAsyncTask(Context context, ApiCallListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected List<PopularHotel> doInBackground(Void... voids) {
        SharedPreferences preferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String authToken = preferences.getString("jwtKey", null);
        if (authToken != null) {
            try {
                PopularHotelApiService apiService = PopularHotelRetrofitClient.getRetrofitInstance().create(PopularHotelApiService.class);
                Call<PopularHotelApiRespone> call = apiService.getAllPopularHotels("Bearer " + authToken);

                Response<PopularHotelApiRespone> response = call.execute();
                if (response.isSuccessful() && response.body() != null) {
                    return response.body().getData();
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<PopularHotel> popularHotels) {
        super.onPostExecute(popularHotels);

        if (popularHotels != null) {
            listener.onApiCallSuccess(popularHotels);
        } else {
            listener.onApiCallFailure("API call failed");
        }
    }
}
