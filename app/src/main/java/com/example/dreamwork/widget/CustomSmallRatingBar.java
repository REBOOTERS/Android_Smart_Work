package com.example.dreamwork.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dreamwork.R;
import com.example.dreamwork.util.V;

/**
 * Created by engineer on 2016/4/17.
 */
public class CustomSmallRatingBar extends LinearLayout {

    private Context mContext;
    private ImageView star1, star2, star3, star4, star5;
    private int rating;
    private Drawable on, off;
    public CustomSmallRatingBar(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        InitView(context);
    }
    public CustomSmallRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        InitView(context);
    }
    private void InitView(Context context) {
        // TODO Auto-generated method stub
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_rating_bar, this);
        star1 = V.f(view, R.id.star1);
        star2 = V.f(view, R.id.star2);
        star3 = V.f(view, R.id.star3);
        star4 = V.f(view, R.id.star4);
        star5 = V.f(view, R.id.star5);
        on = getResources().getDrawable(R.drawable.rating_on);
        off = getResources().getDrawable(R.drawable.rating_off);
    }
    public void setRating(int rating) {
        this.rating = rating;
        switch (rating) {
            case 1:
                star1.setImageDrawable(on);
                star2.setImageDrawable(off);
                star3.setImageDrawable(off);
                star4.setImageDrawable(off);
                star5.setImageDrawable(off);
                break;
            case 2:
                star1.setImageDrawable(on);
                star2.setImageDrawable(on);
                star3.setImageDrawable(off);
                star4.setImageDrawable(off);
                star5.setImageDrawable(off);
                break;
            case 3:
                star1.setImageDrawable(on);
                star2.setImageDrawable(on);
                star3.setImageDrawable(on);
                star4.setImageDrawable(off);
                star5.setImageDrawable(off);
                break;
            case 4:
                star1.setImageDrawable(on);
                star2.setImageDrawable(on);
                star3.setImageDrawable(on);
                star4.setImageDrawable(on);
                star5.setImageDrawable(off);
                break;
            case 5:
                star1.setImageDrawable(on);
                star2.setImageDrawable(on);
                star3.setImageDrawable(on);
                star4.setImageDrawable(on);
                star5.setImageDrawable(on);
                break;
            default:
                star1.setImageDrawable(off);
                star2.setImageDrawable(off);
                star3.setImageDrawable(off);
                star4.setImageDrawable(off);
                star5.setImageDrawable(off);
                break;
        }
    }

}
