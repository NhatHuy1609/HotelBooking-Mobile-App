package com.example.hotelbooking_app.Homescreen.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.hotelbooking_app.Homescreen.Hotels.Homescreen_PopularHotel;

import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Activity.DetailActivity;
import com.example.hotelbooking_app.Searching.Adapter.RecentlyViewedAdapter;

import java.util.List;

public class Homescreen_PopularHotelAdapter extends BaseAdapter {
//    OnItemClickListener onItemClickListener;

    private Context context;
    private int layout;
    private List<Homescreen_PopularHotel> popularHotelList;

//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }

    public Homescreen_PopularHotelAdapter(Context context, int layout, List<Homescreen_PopularHotel> popularHotelList) {
        this.context = context;
        this.layout = layout;
        this.popularHotelList = popularHotelList;
    }

    @Override
    public int getCount() {
        return popularHotelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        // ánh xạ view
        TextView txtTen = (TextView) view.findViewById(R.id.home_name_popularhotel);
        TextView txtDiaChi = (TextView) view.findViewById(R.id.home_location_popularhotel);
        ImageView imgHinh = (ImageView) view.findViewById(R.id.home_img_popularhotel);
        TextView txtDanhGia = (TextView) view.findViewById(R.id.home_rate_popularhotel);
        TextView txtSLDanhGia = (TextView) view.findViewById(R.id.home_SLdanhgia_popularhotel);
        TextView txtGia = (TextView) view.findViewById(R.id.home_price_popularhotel);

//        RelativeLayout pplHotelItem = (RelativeLayout) view.findViewById(R.id.homescreen_item_popular_hotel);

        //gán giá trị
        Homescreen_PopularHotel popularHotel = popularHotelList.get(i);

        txtTen.setText(popularHotel.getTen());
        txtDiaChi.setText(popularHotel.getDiaChi());
        imgHinh.setImageResource(popularHotel.getHinh());
        txtDanhGia.setText(String.valueOf(popularHotel.getDanhGia()));
        txtSLDanhGia.setText(String.valueOf(popularHotel.getSoLuongDanhGia()));
        txtGia.setText(popularHotel.getGia());

//        pplHotelItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClick(i);
//                }
//            }
//        });

        return view;
    }

}

