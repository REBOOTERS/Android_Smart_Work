package com.example.dreamwork.activity.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.dreamwork.R;
import com.example.dreamwork.adapter.MyViewpagerAdapter;
import com.example.dreamwork.constants.Constant;
import com.example.dreamwork.util.V;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransformViewPagerActivity extends Activity {
    private ViewPager viewPager;
    private List<String> pics = new ArrayList<String>();
    private MyViewpagerAdapter myViewpagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform_viewpager);
        InitDatas();
        InitView();
    }

    private void InitDatas() {
        // TODO Auto-generated method stub
        pics = Arrays.asList(Constant.pics);
    }

    private void InitView() {
        // TODO Auto-generated method stub
        viewPager = V.f(this, R.id.id_viewpager);
        myViewpagerAdapter = new MyViewpagerAdapter(pics, viewPager);
        viewPager.setPageMargin(20);
        viewPager.setOffscreenPageLimit(3);
        // viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        viewPager.setAdapter(myViewpagerAdapter);
    }

}
