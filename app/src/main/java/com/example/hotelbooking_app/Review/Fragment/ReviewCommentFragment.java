package com.example.hotelbooking_app.Review.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hotelbooking_app.R;

import java.util.Objects;

public class ReviewCommentFragment extends Fragment {
    public ReviewCommentFragment() {
    }

    private ImageView starImageView1;
    private ImageView starImageView2;
    private ImageView starImageView3;
    private ImageView starImageView4;
    private ImageView starImageView5;
    private  EditText editText;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = (View) inflater.inflate(R.layout.evaluating_comment_fragment, null);
        ImageView imageView = view.findViewById(R.id.send_comment_icon);
         editText = view.findViewById(R.id.comment_textBox);



        imageView.setOnClickListener(sendReview());
        starImageView1 = view.findViewById(R.id.review_star_01);
        starImageView2 = view.findViewById(R.id.review_star_02);
        starImageView3 = view.findViewById(R.id.review_star_03);
        starImageView4 = view.findViewById(R.id.review_star_04);
        starImageView5 = view.findViewById(R.id.review_star_05);
        starImageView1.setOnClickListener(evaluate());
        starImageView2.setOnClickListener(evaluate());
        starImageView3.setOnClickListener(evaluate());
        starImageView4.setOnClickListener(evaluate());
        starImageView5.setOnClickListener(evaluate());

        return view;
    }

    private View.OnClickListener sendReview() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                starImageView1.setImageResource(R.drawable.review_star_regular);
                starImageView3.setImageResource(R.drawable.review_star_regular);
                starImageView2.setImageResource(R.drawable.review_star_regular);
                starImageView4.setImageResource(R.drawable.review_star_regular);
                starImageView5.setImageResource(R.drawable.review_star_regular);
            }
        };
    }

    private View.OnClickListener evaluate() {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == starImageView1) {
                    starImageView1.setImageResource(R.drawable.review_star);
                    starImageView3.setImageResource(R.drawable.review_star_regular);
                    starImageView2.setImageResource(R.drawable.review_star_regular);
                    starImageView4.setImageResource(R.drawable.review_star_regular);
                    starImageView5.setImageResource(R.drawable.review_star_regular);
                } else if (view == starImageView2) {
                    starImageView1.setImageResource(R.drawable.review_star);
                    starImageView2.setImageResource(R.drawable.review_star);
                    starImageView3.setImageResource(R.drawable.review_star_regular);
                    starImageView4.setImageResource(R.drawable.review_star_regular);
                    starImageView5.setImageResource(R.drawable.review_star_regular);

                } else if (view == starImageView3) {
                    starImageView1.setImageResource(R.drawable.review_star);
                    starImageView3.setImageResource(R.drawable.review_star);
                    starImageView2.setImageResource(R.drawable.review_star);
                    starImageView5.setImageResource(R.drawable.review_star_regular);
                    starImageView4.setImageResource(R.drawable.review_star_regular);

                } else if (view == starImageView4) {
                    starImageView1.setImageResource(R.drawable.review_star);
                    starImageView3.setImageResource(R.drawable.review_star);
                    starImageView2.setImageResource(R.drawable.review_star);
                    starImageView4.setImageResource(R.drawable.review_star);
                    starImageView5.setImageResource(R.drawable.review_star_regular);


                } else if (view == starImageView5) {
                    starImageView1.setImageResource(R.drawable.review_star);
                    starImageView3.setImageResource(R.drawable.review_star);
                    starImageView2.setImageResource(R.drawable.review_star);
                    starImageView4.setImageResource(R.drawable.review_star);
                    starImageView5.setImageResource(R.drawable.review_star);
                }
            }
        };
        return clickListener;
    }

}
