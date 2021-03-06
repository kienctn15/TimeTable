package com.example.falcon.timetable.DanhSachCongViec;

/**
 * Created by Optimus on 06/06/17.
 */

public class ItemCongViecInfo {
    String time;
    String date;
    String address;
    String description;

    public ItemCongViecInfo(String time, String date, String address, String description) {
        this.time = time;
        this.date = date;
        this.address = address;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
