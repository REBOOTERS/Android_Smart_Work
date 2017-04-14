package com.example.dreamwork.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.dreamwork.R;
import com.example.dreamwork.util.PayHelper;
import com.example.dreamwork.util.V;

import butterknife.ButterKnife;

/**
 * Created by co-mall on 2016/6/14.
 */
public class PayActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout aliPayWay;
    RelativeLayout wxPayWay;


    /**
     * 订单id
     */
    private String Id = "8430948930";
    /**
     * 微信支付方式
     */
    private final String payTypeWx = "1";
    /**
     * 支付宝支付方式
     */
    private final String payTypeAli = "2";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        aliPayWay = V.f(this, R.id.aliPayWay);
        aliPayWay.setOnClickListener(this);
        wxPayWay = V.f(this, R.id.wxPayWay);
        wxPayWay.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aliPayWay:
                PayHelper.aliPay(PayActivity.this, Id, payTypeAli);
                break;
            case R.id.wxPayWay:
                PayHelper.wxPay(PayActivity.this, Id, payTypeWx);
                break;
        }
    }
}
