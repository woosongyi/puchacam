package com.example.administrator.ddu2.gallery;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.administrator.ddu2.MyViewPagerAdapter;
import com.example.administrator.ddu2.R;

/**
 * Created by Administrator on 2016-01-19.
 */
public class galleryActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mPager;
    private ImageButton allview_btn, calendar_btn, location_btn, event_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_main);
        initLayout();
    }

    private void initLayout() {
        allview_btn = (ImageButton) findViewById(R.id.allview_btn);
        calendar_btn = (ImageButton) findViewById(R.id.calendar_btn);
        location_btn = (ImageButton) findViewById(R.id.location_btn);
        event_btn = (ImageButton) findViewById(R.id.event_btn);
        allview_btn.setOnClickListener(this);
        calendar_btn.setOnClickListener(this);
        location_btn.setOnClickListener(this);
        event_btn.setOnClickListener(this);
        mPager = (ViewPager) findViewById(R.id.pager);

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
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

    private void setCurrentItem(int index) {
        if (index == 0) {
            mPager.setCurrentItem(0);
        } else if (index == 1) {
            mPager.setCurrentItem(1);
        } else if (index == 2) {
            mPager.setCurrentItem(2);
        } else if (index == 3) {
            mPager.setCurrentItem(3);
        }
    }
}
