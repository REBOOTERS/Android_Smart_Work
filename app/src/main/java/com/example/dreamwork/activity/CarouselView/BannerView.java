package com.example.dreamwork.activity.CarouselView;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.dreamwork.R;

import java.util.Timer;
import java.util.TimerTask;


public class BannerView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private Context context;
    private static final int MSG = 0X100;
    /**
     * 轮播图最大数
     */
    private int totalCount = Integer.MAX_VALUE;
    /**
     * 当前banner需要显式的数量
     */
    private int showCount;
    private int currentPosition = 0;
    private ViewPager viewPager;
    private LinearLayout carouselLayout;
    private Adapter adapter;

    /**
     * 轮播切换小圆点宽度默认宽度
     */
    private static final int DOT_DEFAULT_W = 5;

    /**
     * 轮播切换小圆点宽度
     */
    private int IndicatorDotWidth = DOT_DEFAULT_W;
    /**
     * 用户是否干预
     */
    private boolean isUserTouched = false;

    /**
     * 默认的轮播时间
     */
    private static final int DEFAULT_TIME = 3000;
    /**
     * 设置轮播时间
     */
    private int switchTime = DEFAULT_TIME;
    /**
     * 轮播图定时器
     */
    private Timer mTimer = new Timer();


    public BannerView(Context context) {
        super(context);
        this.context = context;
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    private void init() {
        viewPager.setAdapter(null);
        carouselLayout.removeAllViews();
        if (adapter.isEmpty()) {
            return;
        }
        int count = adapter.getCount();
        showCount = adapter.getCount();
        //绘制切换小圆点
        for (int i = 0; i < count; i++) {
            View view = new View(context);
            if (currentPosition == i) {
                view.setPressed(true);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        IndicatorDotWidth + ConvertUtils.dip2px(context, 3),
                        IndicatorDotWidth + ConvertUtils.dip2px(context, 3));
                params.setMargins(IndicatorDotWidth, 0, 0, 0);
                view.setLayoutParams(params);
            } else {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(IndicatorDotWidth, IndicatorDotWidth);
                params.setMargins(IndicatorDotWidth, 0, 0, 0);
                view.setLayoutParams(params);
            }
            view.setBackgroundResource(R.drawable.carousel_layout_page);
            carouselLayout.addView(view);
        }
        viewPager.setAdapter(new ViewPagerAdapter());
        viewPager.setCurrentItem(0);
        this.viewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isUserTouched = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isUserTouched = false;
                        break;
                }
                return false;
            }
        });
        mTimer.schedule(mTimerTask, switchTime, switchTime);
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        if (adapter != null) {
            init();
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(context).inflate(R.layout.carousel_layout, null);
        this.viewPager = (ViewPager) view.findViewById(R.id.gallery);
        this.carouselLayout = (LinearLayout) view.findViewById(R.id.CarouselLayoutPage);
        IndicatorDotWidth = ConvertUtils.dip2px(context, IndicatorDotWidth);
        this.viewPager.addOnPageChangeListener(this);
        addView(view);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("CarouselView", "onPageScrolled was invoke()");
    }

    @Override
    public void onPageSelected(int position) {
        currentPosition = position;
        int count = carouselLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = carouselLayout.getChildAt(i);
            if (position % showCount == i) {
                view.setSelected(true);
                //当前位置的点要绘制的较大一点
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        IndicatorDotWidth + ConvertUtils.dip2px(context, 3),
                        IndicatorDotWidth + ConvertUtils.dip2px(context, 3));
                params.setMargins(IndicatorDotWidth, 0, 0, 0);
                view.setLayoutParams(params);
            } else {
                view.setSelected(false);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(IndicatorDotWidth, IndicatorDotWidth);
                params.setMargins(IndicatorDotWidth, 0, 0, 0);
                view.setLayoutParams(params);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        Log.d("CarouselView", "onPageScrollStateChanged was invoke()");
    }

    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return totalCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= showCount;
            View view = adapter.getView(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
            int position = viewPager.getCurrentItem();
            if (position == 0) {
                position = showCount;
                viewPager.setCurrentItem(position, false);
            } else if (position == totalCount - 1) {
                position = showCount - 1;
                viewPager.setCurrentItem(position, false);
            }
        }
    }


    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (!isUserTouched) {
                currentPosition = (currentPosition + 1) % totalCount;
                handler.sendEmptyMessage(MSG);
            }
        }
    };

    public void cancelTimer() {
        if (this.mTimer != null) {
            this.mTimer.cancel();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG) {
                Log.e("Pos", "the position is " + currentPosition);
                if (currentPosition == totalCount - 1) {
                    viewPager.setCurrentItem(showCount - 1, false);
                } else {
                    viewPager.setCurrentItem(currentPosition);
                }
            }


        }
    };

    /**
     * @param switchTime millseconds
     */
    public void setSwitchTime(int switchTime) {
        this.switchTime = switchTime;
    }

    /**
     * @param indicatorDotWidth
     */
    public void setIndicatorDotWidth(int indicatorDotWidth) {
        IndicatorDotWidth = indicatorDotWidth;
    }

    public interface Adapter {
        boolean isEmpty();

        View getView(int position);

        int getCount();
    }
}
