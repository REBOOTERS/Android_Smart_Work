package com.example.dreamwork.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by co-mall on 2016/5/20.
 * 一个自定义优惠券效果
 */
public class CoupondsCardView extends LinearLayout {

    private Paint mPaint;
    /**
     * 圆间距
     */
    private float gap = 8;
    /**
     * 半径
     */
    private float radius = 10;
    /**
     * 圆数量
     */
    private int circleNum;

    private float remain;

    public CoupondsCardView(Context context) {
        super(context);
    }

    public CoupondsCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();

    }

    public CoupondsCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (remain == 0) {
            remain = (int) (w - gap) % (2 * radius + gap);
        }
        circleNum = (int) ((w - gap) / (2 * radius + gap));
        Log.e("onSizeChanged", "-----" + circleNum);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < circleNum; i++) {
            float x = gap + radius + remain / 2 + ((gap + radius * 2) * i);
//            float x = gap + radius + ((gap + radius * 2) * i);
            Log.e("onDraw", "the x is " + x);
            canvas.drawCircle(x, 0, radius, mPaint);
            canvas.drawCircle(x, getHeight(), radius, mPaint);
        }
    }
}
