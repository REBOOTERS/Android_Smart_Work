package com.example.dreamwork.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by co-mall on 2016/5/6.
 */
public class CustomViewGroupDemo extends LinearLayout {
    String TAG = "CustomViewGroupDemo";

    public CustomViewGroupDemo(Context context) {
        super(context);
        LogInfo();
    }



    public CustomViewGroupDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        LogInfo();
    }

    public CustomViewGroupDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LogInfo();
    }

    private void LogInfo() {
        Log.e(TAG, "The original ViewGroup clickable is " + isClickable());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent---->" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG, "onInterceptTouchEvent---->" + ev.getAction());
        return super.onInterceptTouchEvent(ev);
//        return true;
    }
}
