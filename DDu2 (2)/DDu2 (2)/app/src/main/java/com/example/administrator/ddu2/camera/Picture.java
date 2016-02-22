package com.example.administrator.ddu2.camera;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2015-12-28.
 */
public class Picture {
    String name; //날짜
    Date today;
    double lat; //위도
    double lon; //경도
    String address; //주소
    String path; //경로
    int ymd;//년월일


    public Picture(String name, Date today, double lat, double lon, String address, String path, int ymd) {
        this.name = name;
        this.today = today;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.path = path;
        this.ymd = ymd;
    }

    public Picture(double lat, double lon, String address, String path) {
        create_date();
        this.address = address;
        this.lat = lat;
        this.lon = lon;
        //path.getPath() + "/" + today + ".jpg"
        this.path = path + this.name + ".jpg";
    }

    private void create_date() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA); //년원일초 포메터
        SimpleDateFormat ymd_formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);//년월일 포메터
        this.today = new Date();
        this.ymd = Integer.parseInt(ymd_formatter.format(this.today));
        this.name = formatter.format(this.today);
    }

    @Override
    public String toString() {
        String s = name + "&" + today + "&" + lat + "&" + lon + "&" + address + "&" + path + "&" + ymd; //파일이름,날짜,위도,경도,주소,경로,년,월,일
        return s;
    }


    public Picture(String today, double lat, double lon, String address) {
        this.name = today;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(String today) {
        this.name = today;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public int getYmd() {
        return ymd;
    }

    public void setYmd(int ymd) {
        this.ymd = ymd;
    }
}
