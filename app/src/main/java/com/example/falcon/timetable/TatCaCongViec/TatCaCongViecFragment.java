package com.example.falcon.timetable.TatCaCongViec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.falcon.timetable.DBHandler.DBHandler;
import com.example.falcon.timetable.DanhSachCongViec.CongViec;
import com.example.falcon.timetable.R;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Optimus on 06/06/17.
 */

public class TatCaCongViecFragment extends Fragment {
    View myView;
    TatCaCongViecAdapter adapter;
    ArrayList<CongViec> list_congviec;
    DBHandler db;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_thoigianbieu, container, false);
        db = new DBHandler(getContext());
        get_all_congviec();
        adapter = new TatCaCongViecAdapter(list_congviec);


        return myView;
    }

    public void get_all_congviec()  {
        list_congviec = new ArrayList<>();
        try {
            list_congviec=db.get_all_congviec();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
