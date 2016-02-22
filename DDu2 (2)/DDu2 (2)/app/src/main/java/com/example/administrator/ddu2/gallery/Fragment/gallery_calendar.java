package com.example.administrator.ddu2.gallery.Fragment;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.ddu2.R;
import com.example.administrator.ddu2.Util.Util;
import com.example.administrator.ddu2.calendar.AdapterFrgCalendar;
import com.example.administrator.ddu2.calendar.FrgCalendar;
import com.example.administrator.ddu2.camera.Picture;
import com.example.administrator.ddu2.camera.PictureReader;
import com.example.administrator.ddu2.camera.quickSort;
import com.example.administrator.ddu2.gallery.Adapter.ImageAdapter;
import com.example.administrator.ddu2.gallery.Activity.gallery_fullImage;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-01-19.
 */
public class gallery_calendar extends Fragment implements FrgCalendar.OnFragmentListener {


    private static final int COUNT_PAGE = 12;
    @Bind(R.id.pager)
    public ViewPager pager;
    public TextView datetitle;
    private AdapterFrgCalendar adapter;
    public View view;
    public static ImageAdapter myImageAdapter;
    public PictureReader pr;
    public ArrayList<Picture> pr_list;
    public GridView gridview;
    public static Fragment fragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.gallery_calendar, container, false);
        // 현재 프래그먼트 지정
        fragment = this;

        datetitle = (TextView) view.findViewById(R.id.datetitle);
        ButterKnife.bind(view);
        pager = (ViewPager) view.findViewById(R.id.pager);
        gridview = (GridView) view.findViewById(R.id.gridView);


        myImageAdapter = new ImageAdapter(this.getContext());
        gridview.setAdapter(myImageAdapter);

        System.out.println("on init create call");
        return view;
    }

    // 수정한 부분 생명주기 때문에 여기서 한다.
    @Override
    public void onResume() {
        super.onResume();
        initControl();
        /*
        adapter = new AdapterFrgCalendar(this.getChildFragmentManager());
        pager.setAdapter(adapter);
        adapter.setOnFragmentListener(this);
        adapter.setNumOfMonth(COUNT_PAGE);
        pager.setCurrentItem(COUNT_PAGE);
        String title = adapter.getMonthDisplayed(COUNT_PAGE);
        datetitle.setText(title);
        setgallery();//앨범그리드
*/
    }

    private void initControl() {
        // 수정한부분
        adapter = new AdapterFrgCalendar(this.getChildFragmentManager());
        pager.setAdapter(adapter);
        adapter.setOnFragmentListener(this);
        adapter.setNumOfMonth(COUNT_PAGE);
        pager.setCurrentItem(COUNT_PAGE);
        String title = adapter.getMonthDisplayed(COUNT_PAGE);
        datetitle.setText(title);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String title = adapter.getMonthDisplayed(position);
                datetitle.setText(title);
                if (position == 0) {
                    adapter.addPrev();
                    pager.setCurrentItem(COUNT_PAGE, false);
                } else if (position == adapter.getCount() - 1) {
                    adapter.addNext();
                    pager.setCurrentItem(adapter.getCount() - (COUNT_PAGE + 1), false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setgallery();//앨범그리드

    }

    public void setgallery() {
        //앨범그리드부분


        String ExternalStorageDirectoryPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String PicturePath = ExternalStorageDirectoryPath + "/ddu2/result.txt";
        pr = new PictureReader(PicturePath);
        pr_list = pr.getData();

        quickSort s = new quickSort(); //정렬
        s.Qsort(pr_list, 0, pr_list.size() - 1); //정렬시작

        //가장 최근에 찍은 날짜 순서대로(역순) 40초->30초->20초
        String today = Util.getCurrentDate();
        myImageAdapter.dataClear();
        for (Picture picture : pr_list) { //정렬후 앨범그리드
            //여기서
            //날짜가 같으면 이미지어뎁터에 추가
            if (picture.getYmd() == Integer.parseInt(today)) {
                System.out.println("같아용" + Integer.toString(picture.getYmd()) + "==" + today);
                myImageAdapter.add(picture.getPath());
            } else {
                System.out.println("달라용" + Integer.toString(picture.getYmd()) + "!=" + today);
            }
        }

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = myImageAdapter.getItempath(position);
                Intent i = new Intent(getContext(), gallery_fullImage.class);
                i.putExtra("path", path);
                startActivity(i);
            }
        });
    }

    @Override
    public void onFragmentListener(View view) {
        resizeHeight(view);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void resizeHeight(View mRootView) {

        if (mRootView.getHeight() < 1) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = pager.getLayoutParams();
        if (layoutParams.height <= 0) {
            layoutParams.height = mRootView.getHeight();
            pager.setLayoutParams(layoutParams);
            return;
        }
        ValueAnimator anim = ValueAnimator.ofInt(pager.getLayoutParams().height, mRootView.getHeight());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = pager.getLayoutParams();
                layoutParams.height = val;
                pager.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(200);
        anim.start();
    }
}
