package com.example.dreamwork.activity.superscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by co-mall on 2016/4/6.
 */
public class CustomerScrollViews extends ScrollView {
    /**
     * 屏幕高度
     */
    private int mScreenHeight;

    private UpScrollView upScrollView;
    private MyWebView myWebView;
    private boolean init = false;

    private float fator = 0.2f;
    private int factorHeight;

    private boolean upShow = true;


    public CustomerScrollViews(Context context) {
        super(context);
        init(context);
    }

    public CustomerScrollViews(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomerScrollViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
        factorHeight = (int) (mScreenHeight * fator);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!init) {

            LinearLayout parentView = (LinearLayout) getChildAt(0);
            //获得内部的两个子view
            upScrollView = (UpScrollView) parentView.getChildAt(0);
            myWebView = (MyWebView) parentView.getChildAt(2);
//            //并设定其高度为屏幕高度
            upScrollView.getLayoutParams().height = mScreenHeight;
            myWebView.getLayoutParams().height = mScreenHeight;
            init = true;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            scrollTo(0, 0);
        }
    }

    @Override
    public void fling(int velocityY) {
        int speed=velocityY/1000000;
        super.fling(speed);
        Log.e("The Speed", "speed is "+speed);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                int scrollY = getScrollY();
                if (upShow) {
                    if (scrollY <= factorHeight) {
                        smoothScrollTo(0, 0);
                    } else {
                        smoothScrollTo(0, mScreenHeight);
                        upShow = false;

                    }
                } else {
                    int scrollpadding = mScreenHeight - scrollY;
                    if (scrollpadding >= factorHeight) {
                        this.smoothScrollTo(0, 0);
                        upShow = true;

                    } else {
                        this.smoothScrollTo(0, mScreenHeight);
                    }

                }


                return true;

        }
        return super.onTouchEvent(ev);
    }


}
