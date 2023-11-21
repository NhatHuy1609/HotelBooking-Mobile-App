package com.example.hotelbooking_app.Homescreen.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.content.DialogInterface;
import android.widget.AdapterView;





import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Spinner;

import android.widget.ImageView;
import android.graphics.Color;
import android.view.ViewTreeObserver;
import android.content.Intent;
import com.example.hotelbooking_app.Homescreen.Activity.Homescreen_myprofile;
import com.example.hotelbooking_app.Homescreen.Adapter.Homescreen_NearbyhotelAdapter;
import com.example.hotelbooking_app.Homescreen.Adapter.Homescreen_PopularHotelAdapter;
import com.example.hotelbooking_app.Homescreen.Hotels.Homescreen_Nearbyhotel;
import com.example.hotelbooking_app.Homescreen.Hotels.Homescreen_PopularHotel;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Activity.DetailActivity;
import com.example.hotelbooking_app.Searching.Activity.SearchingActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;


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

        AnhXa();

        adapter = new Homescreen_NearbyhotelAdapter(getActivity(), R.layout.homescreen_item_nearbyhotel, arrayNearByHotel);

        adapter_1 = new Homescreen_PopularHotelAdapter(getActivity(), R.layout.homescreen_item_popularhotel, arrayPopularHotel);

        horizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.homescreen_horizontal_scroll_view);

        lnNearbyHotel = (LinearLayout) view.findViewById(R.id.home_lvNearbyHotel);
//        lnPopularHotel = (LinearLayout) view.findViewById(R.id.home_lvpopularhotel);
        lnPopularHotel = (LinearLayout) horizontalScrollView.getChildAt(0);

        for (int i = 0; i < adapter.getCount(); i++) {
            View ittem = adapter.getView(i, null, null);
            lnNearbyHotel.addView(ittem);
        }

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

        //text location
        lnLocation = (LinearLayout) view.findViewById(R.id.home_lnlocation);
        txtLocation = (TextView) view.findViewById(R.id.home_txt_location);
        lnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialogLocation();
            }
        });

        //đổi màu textnearbyhotel
        nearbyHotels = view.findViewById(R.id.home_nearbyhotels);
        scrollview = view.findViewById(R.id.home_contentt);

        scrollview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollview.getScrollY();
                if (scrollY > (dpToPx(420)-scrollview.getHeight()/2)) {
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

    private void showAlertDialogLocation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Location");


        View alertDialogView = getLayoutInflater().inflate(R.layout.homescreen_arlert_enter_location, null);
        builder.setView(alertDialogView);


        Spinner tpSpinner = alertDialogView.findViewById(R.id.thanhpho_spinner);
        Spinner quanSpinner = alertDialogView.findViewById(R.id.quan_spinner);

        String[] tpOptions = getResources().getStringArray(R.array.thanhpho_data);
        String[] quanOptions = getResources().getStringArray(R.array.quan_data);


        ArrayAdapter<String> quanAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, tpOptions);
        tpSpinner.setAdapter(quanAdapter);

        ArrayAdapter<String> phuongAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, quanOptions);
        quanSpinner.setAdapter(phuongAdapter);

        // Xử lý khi người dùng chọn mục từ Spinner
        tpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        quanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng bấm nút OK
                String selectedQuan = tpSpinner.getSelectedItem().toString();
                String selectedPhuong = quanSpinner.getSelectedItem().toString();
                txtLocation.setText(selectedQuan + ", " + selectedPhuong);
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }
    public int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float)dp * density);
    }


}