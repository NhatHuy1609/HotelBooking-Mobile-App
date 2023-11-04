package com.example.hotelbooking_app.Homescreen.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.hotelbooking_app.Homescreen.Hotels.Homescreen_PopularHotel;
import com.example.hotelbooking_app.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Homescreen_PopularHotelAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Homescreen_PopularHotel> popularHotelList;
    private List<Boolean> isRedHeartList;

    public Homescreen_PopularHotelAdapter(Context context, int layout, List<Homescreen_PopularHotel> popularHotelList) {
        this.context = context;
        this.layout = layout;
        this.popularHotelList = popularHotelList;
        isRedHeartList = new ArrayList<>(Collections.nCopies(popularHotelList.size(), false));
    }

    @Override
    public int getCount() {
        return popularHotelList.size();
    }

    @Override
    public Object getItem(int i) {
        return popularHotelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
        }

        // Ánh xạ view
        TextView txtTen = view.findViewById(R.id.home_name_popularhotel);
        TextView txtDiaChi = view.findViewById(R.id.home_location_popularhotel);
        ImageView imgHinh = view.findViewById(R.id.home_img_popularhotel);
        TextView txtDanhGia = view.findViewById(R.id.home_rate_popularhotel);
        TextView txtSLDanhGia = view.findViewById(R.id.home_SLdanhgia_popularhotel);
        TextView txtGia = view.findViewById(R.id.home_price_popularhotel);
        ImageView heartImageView = view.findViewById(R.id.home_tym);

        // Gán giá trị
        Homescreen_PopularHotel popularHotel = popularHotelList.get(i);

        txtTen.setText(popularHotel.getTen());
        txtDiaChi.setText(popularHotel.getDiaChi());
        imgHinh.setImageResource(popularHotel.getHinh());
        txtDanhGia.setText(String.valueOf(popularHotel.getDanhGia()));
        txtSLDanhGia.setText(String.valueOf(popularHotel.getSoLuongDanhGia()));
        txtGia.setText(popularHotel.getGia());

        // Đổi màu tim
        if (isRedHeartList.get(i)) {
            heartImageView.setImageResource(R.drawable.homescreen_heart_red);
        } else {
            heartImageView.setImageResource(R.drawable.homescreen_heart_white);
        }

        // Xử lý sự kiện khi click vào hình trái tim
        heartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đảo ngược trạng thái trái tim khi được click
                isRedHeartList.set(i, !isRedHeartList.get(i));

                // Cập nhật hình trái tim dựa trên trạng thái mới
                if (isRedHeartList.get(i)) {
                    heartImageView.setImageResource(R.drawable.homescreen_heart_red);
                } else {
                    heartImageView.setImageResource(R.drawable.homescreen_heart_white);
                }
            }
        });

        return view;
    }
}
