package com.example.administrator.ddu2.gallery.Adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.administrator.ddu2.gallery.Fragment.gallery_all;
import com.example.administrator.ddu2.gallery.Fragment.gallery_calendar;
import com.example.administrator.ddu2.gallery.Fragment.gallery_event;
import com.example.administrator.ddu2.gallery.Fragment.gallery_location;

/**
 * Created by Administrator on 2016-01-19.
 */
public class GalleryPagerAdapter extends FragmentStatePagerAdapter {
    Fragment[] fragments = new Fragment[4];

    public GalleryPagerAdapter(FragmentManager fm) {
        super(fm);


    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[0] = new gallery_all();
                    break;
                case 1:
                    fragments[1] = new gallery_calendar();
                    break;
                case 2:
                    fragments[2] = new gallery_location();
                    break;
                case 3:
                    fragments[3] = new gallery_event();
                    break;
            }
        }
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
