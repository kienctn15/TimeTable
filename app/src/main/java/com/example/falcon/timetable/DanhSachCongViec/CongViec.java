package com.example.falcon.timetable.DanhSachCongViec;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Admin on 6/7/2017.
 */

public class CongViec {
    private int id;
    private String title;
    private String address;
    private String date;
    private String time_start;
    private String time_end;
    private String note;

    public CongViec() {
    }

    public CongViec(int id, String title, String address, String date, String time_start, String time_end, String note) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.date = date;
        this.time_start = time_start;
        this.time_end = time_end;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
