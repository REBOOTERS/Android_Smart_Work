package com.example.dreamwork.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dreamwork.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BannerItemFragment extends Fragment implements OnClickListener {

    private int position;
    private ImageView mImageView;
    private int imageRes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_banner_item,
                container, false);
        mImageView = (ImageView) inflate.findViewById(R.id.imageView1);
        mImageView.setImageResource(imageRes);
        inflate.setOnClickListener(this);
        return inflate;
    }

    public void setResId(int imageRes) {
        this.imageRes = imageRes;
    }

    @Override
    public void onClick(View v) {

    }


}
