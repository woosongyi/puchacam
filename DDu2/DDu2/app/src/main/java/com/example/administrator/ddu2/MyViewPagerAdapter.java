package com.example.administrator.ddu2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.administrator.ddu2.gallery.gallery_all;
import com.example.administrator.ddu2.gallery.gallery_calendar;
import com.example.administrator.ddu2.gallery.gallery_event;
import com.example.administrator.ddu2.gallery.gallery_location;

/**
 * Created by Administrator on 2016-01-19.
 */
public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
    Fragment[] fragments = new Fragment[4];

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments[0] = new gallery_all();
        fragments[1] = new gallery_calendar();
        fragments[2] =  new gallery_location();
        fragments[3] = new gallery_event();


    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
