package com.example.administrator.ddu2.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.ddu2.R;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FrgCalendar extends Fragment {
    private int position;
    @Bind(R.id.calendarview)

    CalendarView calendarView;
    private long timeByMillis;
    private OnFragmentListener onFragmentListener;
    private View mRootView;

    public void setOnFragmentListener(OnFragmentListener onFragmentListener) {
        this.onFragmentListener = onFragmentListener;
    }

    public interface OnFragmentListener {
        public void onFragmentListener(View view);
    }

    public static FrgCalendar newInstance(int position) {
        FrgCalendar frg = new FrgCalendar();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        frg.setArguments(bundle);
        return frg;
    }

    public FrgCalendar() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("poisition");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_calendar, null);
        ButterKnife.bind(this, mRootView);
        initView();
        return mRootView;
    }

    protected void initView() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeByMillis);
        calendar.set(Calendar.DATE, 1);
        // 1일의 요일
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        //이달의 마지막 날
        int maxDateOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendarView.initCalendar(dayOfWeek, maxDateOfMonth);
        for (int i = 0; i < maxDateOfMonth + 7; i++) {
            CalendarItemView child = new CalendarItemView(getActivity().getApplicationContext());
            child.setDate(calendar.getTimeInMillis());
            if (i < 7) {
                child.setDayOfWeek(i);
            } else {
                calendar.add(Calendar.DATE, 1);
            }
            calendarView.addView(child);
        }

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && onFragmentListener != null && mRootView != null) {
            onFragmentListener.onFragmentListener(mRootView);
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        System.out.println("frgcalendar create ");
        System.out.println("MrootView :  "+mRootView);
        System.out.println("onFragmentListener :  "+onFragmentListener);

        if (getUserVisibleHint()) {

            mRootView.post(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    if(onFragmentListener != null)
                    onFragmentListener.onFragmentListener(mRootView);
                }
            });

        }
    }

    public void setTimeByMillis(long timeByMillis) {
        this.timeByMillis = timeByMillis;
    }
}
