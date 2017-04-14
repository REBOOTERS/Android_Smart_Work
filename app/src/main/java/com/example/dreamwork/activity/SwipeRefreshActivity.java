package com.example.dreamwork.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dreamwork.R;
import com.example.dreamwork.adapter.RecyclerViewSimpleAdapter;
import com.example.dreamwork.util.V;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by co-mall on 2016/5/20.
 */
public class SwipeRefreshActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
    private List<String> mdatas = new ArrayList<>();
    private RecyclerViewSimpleAdapter adapter;

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiperefresh_layout);

        InitDats();
        swipeLayout = V.f(this, R.id.swipeLayout);
        swipeLayout.setColorSchemeColors(R.color.addtocar,R.color.buynow,R.color.cpb_blue,R.color.cpb_green);
        recyclerView = V.f(this, R.id.recyclerView);

        swipeLayout.setOnRefreshListener(this);
        adapter = new RecyclerViewSimpleAdapter(mdatas);
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
    }
}
