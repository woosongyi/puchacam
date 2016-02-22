package com.example.administrator.ddu2.gallery.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.administrator.ddu2.gallery.Adapter.GalleryPagerAdapter;
import com.example.administrator.ddu2.R;

/**
 * Created by Administrator on 2016-01-19.
 */
public class GalleryActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mPager;
    private ImageButton allview_btn, calendar_btn, location_btn, event_btn;
    private GalleryPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_main);
        initLayout();
        addlistener();
    }

    private void addlistener() {
        allview_btn.setOnClickListener(this);
        calendar_btn.setOnClickListener(this);
        location_btn.setOnClickListener(this);
        event_btn.setOnClickListener(this);
    }

    private void initLayout() {
        allview_btn = (ImageButton) findViewById(R.id.allview_btn);
        calendar_btn = (ImageButton) findViewById(R.id.calendar_btn);
        location_btn = (ImageButton) findViewById(R.id.location_btn);
        event_btn = (ImageButton) findViewById(R.id.event_btn);
        mPager = (ViewPager) findViewById(R.id.pager);
        adapter = new GalleryPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.allview_btn:
                mPager.setCurrentItem(0);
                break;
            case R.id.calendar_btn:
                mPager.setCurrentItem(1);
                break;
            case R.id.location_btn:
                mPager.setCurrentItem(2);
                break;
            case R.id.event_btn:
                mPager.setCurrentItem(3);
                break;
        }
    }


}
