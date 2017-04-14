package com.example.dreamwork.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dreamwork.R;
import com.example.dreamwork.util.V;
import com.example.dreamwork.widget.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by co-mall on 2016/2/25.
 */
public class PulltoRefreshActivity extends Activity implements PullToRefreshView.OnHeaderRefreshListener,
        PullToRefreshView.OnFooterRefreshListener {
    private PullToRefreshView refreshView;
    private ListView listView;
    private List<String> mdatas = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.pull_to_refresh_acitivity_layout);
        InitDats();
        InitView();
    }


    private void InitView() {
        refreshView = V.f(this, R.id.refresh);
        refreshView.setOnHeaderRefreshListener(this);
        refreshView.setOnFooterRefreshListener(this);
        listView = V.f(this, R.id.list);
        listView.setFocusable(false);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,
                mdatas);
        listView.setAdapter(adapter);
    }

    private void InitDats() {
        for (int i = 0; i < 10; i++) {
            mdatas.add("This is Item " + i);
        }
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int m = 0; m < 10; m++) {
                    mdatas.add("This is new Item " + m);
                }
                adapter.notifyDataSetChanged();
                refreshView.onFooterRefreshComplete();
            }
        }, 1000);
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {

        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mdatas.clear();
                InitDats();
                refreshView.onHeaderRefreshComplete();
            }
        }, 1000);
    }
}
