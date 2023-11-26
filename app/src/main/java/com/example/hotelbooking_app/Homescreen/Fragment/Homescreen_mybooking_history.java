package com.example.hotelbooking_app.Homescreen.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.hotelbooking_app.Homescreen.Adapter.Homescreen_NearbyhotelAdapter;
import com.example.hotelbooking_app.Homescreen.Hotels.Homescreen_Nearbyhotel;
import com.example.hotelbooking_app.R;

import java.util.ArrayList;

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
//        AnhXa();

        adapter = new Homescreen_NearbyhotelAdapter(getActivity(),R.layout.homescreen_item_nearbyhotel, arrayHistory);
        lnHistory = (LinearLayout) view.findViewById(R.id.lvHistoryHotel);

        for (int i=0; i< adapter.getCount(); i++) {
            View item = adapter.getView(i,null,null);
            lnHistory.addView(item);
        }
        return view;
    }
//    private void AnhXa() {
//        arrayHistory.add(new Homescreen_Nearbyhotel("MUONG THANH","My An Beach",4.9,1000,"$100/Day",R.drawable.homescreen_muongthanh));
//        arrayHistory.add(new Homescreen_Nearbyhotel("MERODA","My An Beach",4.9,500,"$100/Day",R.drawable.homescreen_meroda));
//        arrayHistory.add(new Homescreen_Nearbyhotel("MUONG THANH","My An Beach",4.9,1000,"$100/Day",R.drawable.homescreen_muongthanh));
//        arrayHistory.add(new Homescreen_Nearbyhotel("MERODAa","My An Beach",4.9,500,"$100/Day",R.drawable.homescreen_meroda));
//        arrayHistory.add(new Homescreen_Nearbyhotel("MUONG THANH","My An Beach",4.9,1000,"$100/Day",R.drawable.homescreen_muongthanh));
//        arrayHistory.add(new Homescreen_Nearbyhotel("MERODA","My An Beach",4.9,500,"$100/Day",R.drawable.homescreen_meroda));
//
//    }
}