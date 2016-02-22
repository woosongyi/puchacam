package com.example.administrator.ddu2.gallery.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.administrator.ddu2.R;
import com.example.administrator.ddu2.camera.Picture;
import com.example.administrator.ddu2.camera.PictureReader;
import com.example.administrator.ddu2.camera.quickSort;
import com.example.administrator.ddu2.gallery.Adapter.ImageAdapter;
import com.example.administrator.ddu2.gallery.Activity.gallery_fullImage;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-01-21.
 */
public class gallery_all extends Fragment {
    ImageAdapter myImageAdapter;
    PictureReader pr;
    ArrayList<Picture> pr_list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gallery_all, container, false);
        GridView gridview = (GridView) view.findViewById(R.id.gridView);
        myImageAdapter = new ImageAdapter(this.getContext());
        gridview.setAdapter(myImageAdapter);


        String ExternalStorageDirectoryPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String PicturePath = ExternalStorageDirectoryPath + "/ddu2/result.txt";
        pr = new PictureReader(PicturePath);
        pr_list = pr.getData();

        quickSort s = new quickSort(); //정렬클래스
        s.Qsort(pr_list, 0, pr_list.size() - 1); //정렬시작


        //가장 최근에 찍은 날짜 순서대로(역순) 40초->30초->20초
        for (Picture picture : pr_list) { //정렬후 앨범그리드
            myImageAdapter.add(picture.getPath());
        }

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = myImageAdapter.getItempath(position);
                Intent i = new Intent(getContext(),gallery_fullImage.class);
                i.putExtra("path", path);
                startActivity(i);
            }
        });
        return view;
    }
}