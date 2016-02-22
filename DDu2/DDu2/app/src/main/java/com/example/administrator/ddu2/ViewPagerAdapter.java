package com.example.administrator.ddu2;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016-01-19.
 */
public class ViewPagerAdapter extends PagerAdapter {

    public LayoutInflater mLayoutInflater;
    public Intent intent;

    public ViewPagerAdapter(Context context){
        super();
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup pager, int position) {
        View view = null;
        if(position==0) {
            view = mLayoutInflater.inflate(R.layout.gallery_calendar,null);
        }
        else if(position==1){
            view = mLayoutInflater.inflate(R.layout.gallery_calendar,null);
        }
        else if(position==2){
            view = mLayoutInflater.inflate(R.layout.gallery_event,null);
        }
        ((ViewPager)pager).addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup pager, int position, Object view) {
        ((ViewPager)pager).removeView((View)view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==object;
    }
}
