package com.example.dreamwork.widget;


import android.os.CountDownTimer;
import android.widget.Button;

import com.example.dreamwork.R;

public class TimerCount extends CountDownTimer {

    private Button bnt;

    public TimerCount(long millisInFuture, long countDownInterval, Button bnt) {
        super(millisInFuture*1000, countDownInterval);
        this.bnt = bnt;
    }


    @Override
    public void onFinish() {
        // TODO Auto-generated method stub
        bnt.setClickable(true);
        bnt.setBackgroundResource(R.color.addtocar);
        bnt.setText("获取短信校验码");
    }

    @Override
    public void onTick(long arg0) {
        // TODO Auto-generated method stub
        bnt.setClickable(false);
        bnt.setBackgroundResource(R.color.yahui);
        bnt.setText("("+arg0 / 1000 + ")秒后重新获取");
    }

}
