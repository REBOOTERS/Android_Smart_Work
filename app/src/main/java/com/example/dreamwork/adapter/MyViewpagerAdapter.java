package com.example.dreamwork.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyViewpagerAdapter extends PagerAdapter {
	private final int maxcount = 100;
	private List<String> pics;
	private Context mContext;
	private ViewPager pager;

	public MyViewpagerAdapter(List<String> pics, ViewPager pager) {
		super();
		this.pics = pics;
		this.pager = pager;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView((ImageView) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		mContext = container.getContext();
		ImageView image = new ImageView(mContext);
		image.setScaleType(ScaleType.FIT_XY);
		position %= pics.size();
		Glide.with(mContext).load(pics.get(position)).into(image);
		container.addView(image, 0);
		return image;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return maxcount;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	@Override
	public void finishUpdate(ViewGroup container) {
		// TODO Auto-generated method stub
		int position = pager.getCurrentItem();
		Log.e("ll", "finish update before, position=" + position);
		if (position == 0) {
			position = pics.size();
			pager.setCurrentItem(position, false);
		} else if (position == maxcount - 1) {
			position = pics.size() - 1;
			pager.setCurrentItem(position, false);
		}
		Log.e("ll", "finish update after, position=" + position);
	}

}
