package com.example.dreamwork.activity.superscrollview;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dreamwork.R;
import com.example.dreamwork.util.V;

/**
 * One
 * Created by co-mall on 2016/4/6.
 */
public class DualScrollViewActivity extends Activity implements View.OnClickListener {


    private TextView sinaTv, qqTv, baiduTv;
    private View line1, line2, line3;

    private One one;
    private Two two;
    private Three three;


    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitView();
        sinaTv.performClick();
    }

    private void InitView() {
        setContentView(R.layout.dual_scrollview_activity_layout2);

        sinaTv = V.f(this, R.id.one);
        sinaTv.setOnClickListener(this);
        qqTv = V.f(this, R.id.two);
        qqTv.setOnClickListener(this);
        baiduTv = V.f(this, R.id.three);
        baiduTv.setOnClickListener(this);


        line1 = V.f(this, R.id.line1);
        line2 = V.f(this, R.id.line2);
        line3 = V.f(this, R.id.line3);

        fragmentManager=getFragmentManager();

    }


    @Override
    public void onClick(View v) {
        reset();
        switch (v.getId()) {
            case R.id.one:
                line1.setVisibility(View.VISIBLE);
                setTabSelection(0);
                break;
            case R.id.two:
                setTabSelection(1);
                line2.setVisibility(View.VISIBLE);
                break;
            case R.id.three:
                setTabSelection(2);
                line3.setVisibility(View.VISIBLE);

                break;
            default:
                break;
        }


    }


    private void setTabSelection(int item) {
        transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (item) {
            case 0:


                if (one == null) {
                    one = new One();
                    transaction.add(R.id.container, one);
                } else {
                    transaction.show(one);
                }
                break;
            case 1:

                if (two == null) {
                    two = new Two();
                    transaction.add(R.id.container, two);
                } else {
                    transaction.show(two);
                }
                break;
            case 2:


                three = new Three();
                transaction.add(R.id.container, three);
                transaction.show(three);
                break;

            default:
                break;

        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (one != null) {
            transaction.hide(one);
        }

        if (two != null) {
            transaction.hide(two);
        }

        if (three != null) {
            transaction.hide(three);
        }
    }


    private void reset() {
        line1.setVisibility(View.GONE);
        line2.setVisibility(View.GONE);
        line3.setVisibility(View.GONE);
    }
}
