package com.example.dreamwork.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.dreamwork.R;
import com.example.dreamwork.activity.CarouselView.BannerView;
import com.example.dreamwork.activity.CarouselView.ConvertUtils;
import com.example.dreamwork.adapter.RecyclerViewWithHeaderAdapter;
import com.example.dreamwork.constants.Constant;
import com.example.dreamwork.util.T;
import com.example.dreamwork.util.V;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by co-mall on 2016/5/20.
 */
public class RecyclerViewActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
    private Context mContext;
    private List<String> mdatas = new ArrayList<>();
    private RecyclerViewWithHeaderAdapter adapter;

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeLayout;
    private View headerView;
    private List<String> banners = new ArrayList<>();
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mInflater = LayoutInflater.from(this);
        setContentView(R.layout.activity_recyclerview_layout);

        InitDats();
        swipeLayout = V.f(this, R.id.swipeLayout);
        swipeLayout.setColorSchemeColors(R.color.addtocar, R.color.buynow, R.color.cpb_blue, R.color.cpb_green);
        recyclerView = V.f(this, R.id.recyclerView);

        swipeLayout.setOnRefreshListener(this);
        adapter = new RecyclerViewWithHeaderAdapter(mdatas);
        adapter.setmHeaderView(headerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            public void run() {
                swipeLayout.setRefreshing(false);
                mdatas.clear();
                InitDats();
                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }

    private void InitDats() {
        for (int i = 0; i < 30; i++) {
            mdatas.add("This is Item " + i);
        }

        banners = Arrays.asList(Constant.pics);

        headerView = mInflater.inflate(R.layout.carouse_layout_header, null);
        BannerView carouselView = (BannerView) headerView.findViewById(R.id.CarouselView);
        //这里考虑到不同手机分辨率下的情况
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, ConvertUtils.dip2px(this, 200));
        carouselView.setLayoutParams(params);
        carouselView.setSwitchTime(3000);
        carouselView.setAdapter(new MyAdapter());
    }


    private class MyAdapter implements BannerView.Adapter {

        @Override
        public boolean isEmpty() {
//            return banners.size() > 0 ? false : true;
            return banners.size() < 0;
        }

        @Override
        public View getView(final int position) {
            View view = mInflater.inflate(R.layout.item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image);
            Picasso.with(mContext).load(banners.get(position)).into(imageView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    T.showShort(mContext, "Now is " + position);
                }
            });
            return view;
        }

        @Override
        public int getCount() {
            return banners.size();
        }
    }
}
