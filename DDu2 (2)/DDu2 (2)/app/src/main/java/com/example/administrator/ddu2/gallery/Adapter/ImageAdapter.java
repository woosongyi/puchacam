package com.example.administrator.ddu2.gallery.Adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;


/**
 * Created by Administrator on 2015-12-29.
 */
public class ImageAdapter extends BaseAdapter {

   public Context mContext;
    ArrayList<String> itemList = new ArrayList<String>();

    public ImageAdapter(Context c){
        mContext = c;
    }

    //이미지의 개수
    public int getCount() {
        return itemList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    //선택된 이미지ID를 반환
    public long getItemId(int position) {
        return 0;
    }

    public String getItempath(int position){
        String path = itemList.get(position);
        return path;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(500,500));
            imageView.setRotation(90);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
        } else {
            imageView = (ImageView) convertView;
        }

        Bitmap bm = decodeSampledBitmapFromUri(itemList.get(position), 500,500);

        imageView.setImageBitmap(bm);
        return imageView;
    }
    public static Bitmap decodeSampledBitmapFromUri(String path, int reqWidth, int reqHeight) {

        Bitmap bm = null;
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, options);

        return bm;
    }
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float)height / (float)reqHeight);
            } else {
                inSampleSize = Math.round((float)width / (float)reqWidth);
            }
        }

        return inSampleSize;
    }

    public void dataClear()
    {
        itemList.clear();
    }

    public void add(String absolutePath) {
        itemList.add(absolutePath);
    }

}
