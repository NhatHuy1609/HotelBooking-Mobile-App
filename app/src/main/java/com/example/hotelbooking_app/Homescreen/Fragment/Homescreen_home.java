package com.example.hotelbooking_app.Homescreen.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.graphics.Color;
import android.view.ViewTreeObserver;

import com.example.hotelbooking_app.Homescreen.Adapter.Homescreen_NearbyhotelAdapter;
import com.example.hotelbooking_app.Homescreen.Adapter.Homescreen_PopularHotelAdapter;
import com.example.hotelbooking_app.Homescreen.Hotels.Homescreen_Nearbyhotel;
import com.example.hotelbooking_app.Homescreen.Hotels.Homescreen_PopularHotel;
import com.example.hotelbooking_app.R;


import java.util.ArrayList;


public class Homescreen_home extends Fragment {
    LinearLayout lnNearbyHotel,lnPopularHotel;
    TextView nearbyHotels;
    ScrollView scrollview;
    ArrayList<Homescreen_Nearbyhotel> arrayNearByHotel;
    ArrayList<Homescreen_PopularHotel> arrayPopularHotel;
    Homescreen_NearbyhotelAdapter adapter;
    Homescreen_PopularHotelAdapter adapter_1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homescreen_fragment_home, container, false);


        arrayNearByHotel = new ArrayList<>();
        arrayPopularHotel = new ArrayList<>();

        AnhXa();

        adapter = new Homescreen_NearbyhotelAdapter(getActivity(), R.layout.homescreen_item_nearbyhotel, arrayNearByHotel);
        adapter_1 = new Homescreen_PopularHotelAdapter(getActivity(), R.layout.homescreen_item_popularhotel, arrayPopularHotel);

        lnNearbyHotel = (LinearLayout) view.findViewById(R.id.home_lvNearbyHotel);
        lnPopularHotel = (LinearLayout) view.findViewById(R.id.home_lvpopularhotel);

        for (Homescreen_Nearbyhotel hotel : arrayNearByHotel) {
            View hotelView = inflater.inflate(R.layout.homescreen_item_nearbyhotel, null);
            lnNearbyHotel.addView(hotelView);
        }

        for (Homescreen_PopularHotel hotel : arrayPopularHotel) {
            View hotelView = inflater.inflate(R.layout.homescreen_item_popularhotel, null);
            lnPopularHotel.addView(hotelView);
        }

        //đổi màu textnearbyhotel
        nearbyHotels = view.findViewById(R.id.home_nearbyhotels);
        scrollview = view.findViewById(R.id.home_contentt);

        scrollview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollview.getScrollY();
                if (scrollY > scrollview.getHeight()/8) {
                    nearbyHotels.setTextColor(Color.WHITE);
                } else {
                    nearbyHotels.setTextColor(Color.BLACK);
                }
            }
        });
        return view;
    }


    private void AnhXa() {
        arrayPopularHotel.add(new Homescreen_PopularHotel("HAIAN","My An Beach",4.9,500,"$100/Day",R.drawable.homescreen_haian));
        arrayPopularHotel.add(new Homescreen_PopularHotel("NOVOTEL","My An Beach",5,1000,"$100/Day",R.drawable.homescreen_novotel));
        arrayPopularHotel.add(new Homescreen_PopularHotel("HAIAN","My An Beach",4.9,500,"$100/Day",R.drawable.homescreen_haian));
        arrayPopularHotel.add(new Homescreen_PopularHotel("NOVOTEL","My An Beach",5,1000,"$100/Day",R.drawable.homescreen_novotel));
        arrayPopularHotel.add(new Homescreen_PopularHotel("HAIAN","My An Beach",4.9,500,"$100/Day",R.drawable.homescreen_haian));
        arrayPopularHotel.add(new Homescreen_PopularHotel("NOVOTEL","My An Beach",5,1000,"$100/Day",R.drawable.homescreen_novotel));

        arrayNearByHotel.add(new Homescreen_Nearbyhotel("MUONG THANH","My An Beach",4.9,1000,"$100/Day",R.drawable.homescreen_muongthanh));
        arrayNearByHotel.add(new Homescreen_Nearbyhotel("MERODA","My An Beach",4.9,500,"$100/Day",R.drawable.homescreen_meroda));
        arrayNearByHotel.add(new Homescreen_Nearbyhotel("MUONG THANH","My An Beach",4.9,1000,"$100/Day",R.drawable.homescreen_muongthanh));
        arrayNearByHotel.add(new Homescreen_Nearbyhotel("MERODA","My An Beach",4.9,500,"$100/Day",R.drawable.homescreen_meroda));
        arrayNearByHotel.add(new Homescreen_Nearbyhotel("MUONG THANH","My An Beach",4.9,1000,"$100/Day",R.drawable.homescreen_muongthanh));
        arrayNearByHotel.add(new Homescreen_Nearbyhotel("MERODA","My An Beach",4.9,500,"$100/Day",R.drawable.homescreen_meroda));

    }
}