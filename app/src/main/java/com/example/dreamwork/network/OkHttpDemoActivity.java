package com.example.dreamwork.network;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dreamwork.R;
import com.example.dreamwork.util.V;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by co-mall on 2016/7/4.
 */
public class OkHttpDemoActivity extends AppCompatActivity implements View.OnClickListener {
    private final String url = "http://www.baidu.com";
    TextView result;
    Button getBtn;
    MyHandler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_layout);
        getBtn = V.f(this, R.id.get);
        result = V.f(this, R.id.result);
        getBtn.setOnClickListener(this);
        handler = new MyHandler();
    }

    @Override
    public void onClick(View v) {
        result.setText("");
        switch (v.getId()) {
            case R.id.get:
                okHttpGetMethod();
                break;
            default:
                break;
        }
    }

    /**
     * okHttp get方式
     */
    private void okHttpGetMethod() {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Message msg = new Message();
                msg.what = 0x02;
                msg.obj = e;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.what = 0x01;
                msg.obj = response.body().toString();
                handler.sendMessage(msg);
            }
        });
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x01:
                    result.setText(msg.obj.toString());
                    break;
                case 0x02:
                    Log.e(OkHttpDemoActivity.class.getSimpleName(), msg.obj.toString());
                default:
                    break;
            }
        }
    }
}
