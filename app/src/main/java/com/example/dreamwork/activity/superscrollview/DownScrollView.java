package com.example.dreamwork.activity.superscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ScrollView;

/**
 * Created by co-mall on 2016/4/6.
 */
public class DownScrollView extends ScrollView {
    /**
     * 屏幕高度
     */
    private int mScreenHeight;
    /**
     * 上一次的坐标
     */
    private float mLastY;
    /**
     * 当前View滑动距离
     */
    private int mScrollY;



    public DownScrollView(Context context) {
        super(context);
        init(context);
    }

    public DownScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DownScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm=new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        mScreenHeight=dm.heightPixels;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //默认设定顶层View不拦截
        getParent().getParent().requestDisallowInterceptTouchEvent(true);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY= (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float y=  ev.getY();
                float deltaY=y-mLastY;
                //向上滑动时，如果当前View滑动距离为0时，即滑动到了顶部，可由顶层view控制滑动
                if (deltaY > 0 && mScrollY == 0) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        mScrollY=t;
    }
}
