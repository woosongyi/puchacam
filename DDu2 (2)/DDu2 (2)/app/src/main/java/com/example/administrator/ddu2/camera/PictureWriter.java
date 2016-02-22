package com.example.administrator.ddu2.camera;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2016-01-03.
 */
public class PictureWriter {
    private FileWriter fos;

    String path;

    public PictureWriter(String path) {
        try {
            fos = new FileWriter(path, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addData(Picture picture) {
        try {
            fos.write(picture.toString());
            fos.write("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {

        try {
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
