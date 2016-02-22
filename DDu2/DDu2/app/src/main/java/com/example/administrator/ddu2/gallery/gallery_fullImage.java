package com.example.administrator.ddu2.gallery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.example.administrator.ddu2.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2016-01-22.
 */
public class gallery_fullImage extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_fullimage);

        // get intent data
        Intent i = getIntent();

        // Selected image id
        String path = i.getExtras().getString("path");

        Bitmap pictureBmp = null;

        File file = new File(path);
        try {
            pictureBmp = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));
        }catch(IOException e){
            e.printStackTrace();
        }
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageBitmap(pictureBmp);
        imageView.setRotation(90);

    }
}