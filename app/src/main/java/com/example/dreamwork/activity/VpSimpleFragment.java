package com.example.dreamwork.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dreamwork.R;
import com.example.dreamwork.bean.TimeBean;
import com.example.dreamwork.util.V;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class VpSimpleFragment extends Fragment {
    public static final String BUNDLE_DATA = "bean";
    private TimeBean bean;
    private View rootView;
    private TextView TimerH, TimerM, TimerS;
    private TextView statusTv;


    //计时器
    private int TotalCount = 360;
    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)

    {

        rootView = inflater.inflate(R.layout.fragment_vs, null);
        Bundle arguments = getArguments();
        if (arguments != null) {
            bean = (TimeBean) arguments.getSerializable(BUNDLE_DATA);
        }
        InitView();
        setData();
        return rootView;

    }

    /**
     * 这里针对不同的场次，随机设定一个计数值（实际开发中可从服务器获取这个计数值）
     */
    private void setData() {
        String temp="";
        switch (bean.getStatus()) {
            case 0:
                temp = "距离下场开始";
                TotalCount=2490;
                break;
            case 1:
                temp = "距离本次结束";
                TotalCount=1189;
                break;
            case 2:
                temp = "距离本场开始";
                TotalCount=3311;
                break;
            default:
                break;
        }
        statusTv.setText(temp);
    }

    private void InitView() {
        TimerH = V.f(rootView, R.id.TimerH);
        TimerM = V.f(rootView, R.id.TimerM);
        TimerS = V.f(rootView, R.id.TimerS);
        statusTv = V.f(rootView, R.id.statuTv);

        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 每隔一秒执行一次task任务
        scheduledExecutorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);

    }


    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    int STv = TotalCount % 60;
                    int MTv = TotalCount / 60 % 60;
                    int Htv = TotalCount / 3600;

                    if (STv < 10) {
                        TimerS.setText("0" + STv);
                    } else {
                        TimerS.setText("" + STv);
                    }

                    if (MTv < 10) {
                        TimerM.setText("0" + MTv);
                    } else {
                        TimerM.setText("" + MTv);

                    }

                    if (Htv < 10) {
                        TimerH.setText("0" + Htv);
                    } else {
                        TimerH.setText("" + Htv);

                    }


                    if (TotalCount <= 0) {
                        task.cancel();
                        scheduledExecutorService.shutdown();
                    }
            }
        }
    };

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            TotalCount--;
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };


    public static VpSimpleFragment newInstance(TimeBean bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_DATA, bean);
        VpSimpleFragment fragment = new VpSimpleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onPause() {
        super.onPause();
        //这一句很关键，确保在fragment之间进行切换是，定时器不会出现混乱
        scheduledExecutorService.shutdown();
    }
}
