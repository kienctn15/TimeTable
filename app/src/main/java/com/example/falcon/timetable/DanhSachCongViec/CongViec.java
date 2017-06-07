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
    private Date date;
    private Time time_start;
    private Time time_end;
    private String note;

    public CongViec() {
    }

    public CongViec(int id, String title, String address, Date date, Time time_start, Time time_end, String note) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime_start() {
        return time_start;
    }

    public void setTime_start(Time time_start) {
        this.time_start = time_start;
    }

    public Time getTime_end() {
        return time_end;
    }

    public void setTime_end(Time time_end) {
        this.time_end = time_end;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
