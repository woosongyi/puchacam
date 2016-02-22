package com.example.administrator.ddu2.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.administrator.ddu2.R;
import com.example.administrator.ddu2.camera.Picture;
import com.example.administrator.ddu2.camera.PictureReader;
import com.example.administrator.ddu2.camera.quickSort;
import com.example.administrator.ddu2.gallery.Adapter.ImageAdapter;
import com.example.administrator.ddu2.gallery.Fragment.gallery_calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class CalendarItemView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaintBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaintBackgroundToday = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaintBackgroundEvent = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int dayOfWeek = -1;
    private boolean isStaticText = false;
    private long millis;
    private Rect rect;
    private boolean isTouchMode;
    private int dp11;
    private int dp16;
    private boolean hasEvent = false;
    private int[] mColorEvents;
    public String strDT; //선택한 날짜
    public ImageAdapter myImageAdapter;
    PictureReader pr;
    ArrayList<Picture> pr_list;
    GridView gridview;

    private View fragmentView;

    public CalendarItemView(Context context) {
        super(context);
        initialize();
    }

    public CalendarItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize() {


        dp11 = dip2px(11);
        dp16 = dip2px(16);

        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(dp11);
        mPaintBackground.setColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        mPaintBackgroundToday.setColor(getContext().getResources().getColor(R.color.basicColor));
        mPaintBackgroundEvent.setColor(getContext().getResources().getColor(R.color.basicColor));
        setClickable(true);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                Log.d("hatti.onTouchEvent", event.getAction() + "");
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
//                        setBackgroundResource(R.drawable.round_default_select);
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        isTouchMode = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        if (isTouchMode) {
                            ((CalendarView) getParent()).setCurrentSelectedView(v);
                            System.out.println("날짜가 클릭 되었어용");
                            isTouchMode = false;
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        isTouchMode = false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            isTouchMode = false;
                            return true;
                        }
                        break;
                }
                return false;
            }
        });
        setPadding(30, 0, 30, 0);
    }

    @Override
    public void setBackgroundResource(int resid) {
        super.setBackgroundResource(resid);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int xPos = (canvas.getWidth() / 2);
        int yPos = (int) ((canvas.getHeight() / 2) - ((mPaint.descent() + mPaint.ascent()) / 2));
        mPaint.setTextAlign(Paint.Align.CENTER);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        CalendarView calendarView = (CalendarView) getParent();
        if (calendarView.getParent() instanceof ViewPager) {
            ViewGroup parent = (ViewPager) calendarView.getParent();
            CalendarItemView tagView = (CalendarItemView) parent.getTag();

            if (!isStaticText && tagView != null && tagView.getTag() != null && tagView.getTag() instanceof Long) {
                long millis = (long) tagView.getTag();
                if (isSameDay(millis, this.millis)) {
                    // 수정한 부분
                    Date date = new Date(millis);
                    SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMdd");
                    strDT = dayTime.format(date);
                    System.out.println("선택된 날짜는 : " + strDT);
                    //클릭시 해당 날짜 사진 보여주기
                    gallerychange(strDT);
                    canvas.drawCircle(xPos, yPos - 15, canvas.getWidth() / 3, mPaintBackground);
                }

            }
        }

        if (!isStaticText && isToday(millis)) {
            System.out.println("click");
            canvas.drawCircle(xPos, yPos - 15, canvas.getWidth() / 3, mPaintBackgroundToday);
        }

        if (isStaticText) {
            // 요일 표시
            canvas.drawText(CalendarView.DAY_OF_WEEK[dayOfWeek], xPos, yPos, mPaint);
        } else {
            // 날짜 표시
            canvas.drawText(calendar.get(Calendar.DATE) + "", xPos, yPos, mPaint);
            if (isPicture(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE))) {
                canvas.drawRoundRect(xPos - 20, getHeight() / 2 + 40, xPos + 30, getHeight() / 2 + 60, 50f, 50f, mPaintBackground);
            }
        }

        if (hasEvent) {
            mPaintBackgroundEvent.setColor(getResources().getColor(mColorEvents[0]));
            //이벤트 날짜
            canvas.drawRoundRect(xPos - 20, getHeight() / 2 + 40, xPos + 30, getHeight() / 2 + 60, 50f, 50f, mPaintBackground);
        }

    }

    private boolean isPicture(int year, int month, int date) {
        String ExternalStorageDirectoryPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String PicturePath = ExternalStorageDirectoryPath + "/ddu2/result.txt";
        pr = new PictureReader(PicturePath);
        pr_list = pr.getData();
        for (Picture picture : pr_list) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(picture.getToday());
            int cyear = cal.get(Calendar.YEAR);
            int cmonth = cal.get(Calendar.MONTH);
            int cdate = cal.get(Calendar.DATE);
            if (cyear == year && cmonth == month && cdate == date) {
                System.out.println("사진날짜 : " + picture.getToday());
                return true;
            }
        }
        return false;
    }

    public void gallerychange(String strDT) {

        String ExternalStorageDirectoryPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String PicturePath = ExternalStorageDirectoryPath + "/ddu2/result.txt";
        pr = new PictureReader(PicturePath);
        pr_list = pr.getData();

        quickSort s = new quickSort(); //정렬
        s.Qsort(pr_list, 0, pr_list.size() - 1); //정렬시작

        //가장 최근에 찍은 날짜 순서대로(역순) 40초->30초->20초
        gallery_calendar.myImageAdapter.dataClear();
        for (Picture picture : pr_list) { //정렬후 앨범그리드
            //여기서
            //날짜가 같으면 이미지어뎁터에 추가
            if (picture.getYmd() == Integer.parseInt(strDT)) {
                System.out.println("같아용" + Integer.toString(picture.getYmd()) + "==" + strDT);
                gallery_calendar.myImageAdapter.add(picture.getPath());
            } else {
                System.out.println("달라용" + Integer.toString(picture.getYmd()) + "!=" + strDT);
            }
        }

        gallery_calendar.myImageAdapter.notifyDataSetChanged();
        System.out.println(gallery_calendar.myImageAdapter.getCount());

    }

    private boolean isToday(long millis) {
        Calendar cal1 = Calendar.getInstance();
        return isSameDay(cal1.getTimeInMillis(), millis);
    }

    public void setDate(long millis) {
        this.millis = millis;
        setTag(millis);
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        isStaticText = true;
    }

    public void setEvent(int... resid) {
        hasEvent = true;
        mColorEvents = resid;
    }

    public boolean isSameDay(long millis1, long millis2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTimeInMillis(millis1);
        cal2.setTimeInMillis(millis2);
        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE));
    }

    public boolean isStaticText() {
        return isStaticText;
    }

    public int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }

    public String getStrDT() {
        return strDT;
    }

    public void setStrDT(String strDT) {
        this.strDT = strDT;
    }
}
