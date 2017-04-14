package com.example.dreamwork.activity.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.example.dreamwork.R;
import com.example.dreamwork.activity.VpSimpleFragment;
import com.example.dreamwork.bean.TimeBean;
import com.example.dreamwork.widget.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by co-mall on 2016/2/22.
 */
public class ViewPagerIndicatorActivity extends FragmentActivity {
    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private List<String> mDatas = new ArrayList<>();
    private List<TimeBean> jsonbean = new ArrayList<>();

    private ViewPagerIndicator mIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.vp_indicator);

        initView();
        getDataFromServer();
        initDatas();


    }

    /**
     * 此处设定虚拟数据（实际应该从服务器获取）
     */
    private void getDataFromServer() {
        TimeBean time1=new TimeBean();
        time1.setStatus(0);
        time1.setTime("08:00");
        TimeBean time2=new TimeBean();
        time2.setStatus(0);
        time2.setTime("10:00");
        TimeBean time3=new TimeBean();
        time3.setStatus(1);
        time3.setTime("12:00");
        TimeBean time4=new TimeBean();
        time4.setStatus(2);
        time4.setTime("18:00");
        TimeBean time5=new TimeBean();
        time5.setStatus(2);
        time5.setTime("22:00");

        jsonbean.add(time1);
        jsonbean.add(time2);
        jsonbean.add(time3);
        jsonbean.add(time4);
        jsonbean.add(time5);
    }

    private void initDatas() {
        int postion=0;
        for(int i=0;i<jsonbean.size();i++){
            String str="";
            switch (jsonbean.get(i).getStatus()){
                case 0:
                    str=jsonbean.get(i).getTime()+"\n已结束";
                    break;
                case 1:
                    str=jsonbean.get(i).getTime()+"\n进行中";
                    break;
                case 2:
                    str=jsonbean.get(i).getTime()+"\n即将开始";
                    break;
                default:
                    break;
            }
            mDatas.add(str);

            //将正在进行的活动位置进行标记
            if(jsonbean.get(i).getStatus()==1){
                postion=i;
            }
        }


        for (TimeBean data : jsonbean) {
            VpSimpleFragment fragment = VpSimpleFragment.newInstance(data);
            mTabContents.add(fragment);
        }




        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mTabContents.get(position);
            }
        };

        //设置Tab上的标题
        mIndicator.setTabItemTitles(mDatas);
        mViewPager.setAdapter(mAdapter);
        //设置关联的ViewPager,并且显示为当前正在进行的fragment页面
        mIndicator.setViewPager(mViewPager, postion);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_vp);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
    }

}
