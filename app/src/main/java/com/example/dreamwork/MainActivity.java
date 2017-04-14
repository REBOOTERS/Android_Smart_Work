package com.example.dreamwork;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.example.dreamwork.activity.CarouselView.BannerViewActivity;
import com.example.dreamwork.activity.CouponCardViewActivity;
import com.example.dreamwork.activity.NinePatchActivity;
import com.example.dreamwork.activity.PayActivity;
import com.example.dreamwork.activity.ProgressDialogActivity;
import com.example.dreamwork.activity.PulltoRefreshActivity;
import com.example.dreamwork.activity.RecyclerViewActivity;
import com.example.dreamwork.activity.SelectPic.SelectPicActivity;
import com.example.dreamwork.activity.ShopStyleActivity;
import com.example.dreamwork.activity.SwipeRefreshActivity;
import com.example.dreamwork.activity.superscrollview.DualScrollViewActivity;
import com.example.dreamwork.activity.viewpager.TransformViewPagerActivity;
import com.example.dreamwork.activity.viewpager.ViewPagerIndicatorActivity;
import com.example.dreamwork.adapter.MyBaseExpandListAdapter;
import com.example.dreamwork.network.OkHttpDemoActivity;
import com.example.dreamwork.service.notification.NotificationActivity;
import com.example.dreamwork.service.services.IntentServiceActivity;
import com.example.dreamwork.service.services.ServiceActivity;
import com.example.dreamwork.util.V;
import com.example.dreamwork.widget.circularbtn.CirculrBtnActivityDemo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private Context mContext;
    private ExpandableListView expandableListView;
    private List<String> components = new ArrayList<String>();
    private List<List<Class>> activities = new ArrayList<>();
    private MyBaseExpandListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initDatas();
        InitView();
    }

    private void initDatas() {
        // TODO Auto-generated method stub
        components.add("ViewPager");
        components.add("Service");
        components.add("Custom Widget");
        components.add("Super Custom Widget");
        components.add("Else");

        List<Class> activity1 = new ArrayList<>();
        activity1.add(TransformViewPagerActivity.class);
        activity1.add(ViewPagerIndicatorActivity.class);
        activity1.add(BannerViewActivity.class);

        List<Class> activity2 = new ArrayList<>();
        activity2.add(NotificationActivity.class);
        activity2.add(ServiceActivity.class);
        activity2.add(IntentServiceActivity.class);

        List<Class> activity3 = new ArrayList<>();
        activity3.add(SelectPicActivity.class);
        activity3.add(DualScrollViewActivity.class);
        activity3.add(PulltoRefreshActivity.class);
        activity3.add(SwipeRefreshActivity.class);
        activity3.add(RecyclerViewActivity.class);
        activity3.add(CouponCardViewActivity.class);
        activity3.add(ProgressDialogActivity.class);

        List<Class> activity4 = new ArrayList<>();
        activity4.add(CirculrBtnActivityDemo.class);


        List<Class> activity5 = new ArrayList<>();
        activity5.add(NinePatchActivity.class);
        activity5.add(ShopStyleActivity.class);
        activity5.add(PayActivity.class);
        activity5.add(OkHttpDemoActivity.class);


        activities.add(activity1);
        activities.add(activity2);
        activities.add(activity3);
        activities.add(activity4);
        activities.add(activity5);

    }

    private void InitView() {
        // TODO Auto-generated method stub
        expandableListView = V.f(this, R.id.list);
        expandableListView.setGroupIndicator(null);
        adapter = new MyBaseExpandListAdapter(mContext, components, activities);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition,
                                        long id) {
                // TODO Auto-generated method stub
                Class activty = activities.get(groupPosition).get(childPosition);
                Intent intent = new Intent(mContext, activty);
                startActivity(intent);
                return true;
            }
        });
    }

}
