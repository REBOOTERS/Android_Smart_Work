package com.example.dreamwork.activity.superscrollview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.dreamwork.R;
import com.example.dreamwork.util.V;

/**
 * Created by co-mall on 2016/4/6.
 */
public class Three extends Fragment {
    private MyWebView web;
    private final String url = "http://www.baidu.com";
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.web_view_layout, null);
        web = V.f(view, R.id.web);
        web.loadUrl(url);
        return view;
    }
}
