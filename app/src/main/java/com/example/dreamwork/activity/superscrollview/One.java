package com.example.dreamwork.activity.superscrollview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dreamwork.R;
import com.example.dreamwork.util.V;

/**
 * Created by co-mall on 2016/4/6.
 */
public class One extends Fragment {
    private MyWebView1 web;
    private final String url = "http://www.sina.com.cn";
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.web_view_layout, null);
        web = V.f(view, R.id.web);




        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        web.loadUrl(url);
        return view;
    }
}
