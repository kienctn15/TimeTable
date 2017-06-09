package com.example.falcon.timetable.TatCaCongViec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.falcon.timetable.DBHandler.DBHandler;
import com.example.falcon.timetable.DanhSachCongViec.CongViec;
import com.example.falcon.timetable.R;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Optimus on 06/06/17.
 */

public class TatCaCongViecFragment extends Fragment {
    View myView;
    TatCaCongViecAdapter adapter;
    List<CongViec> list_congviec;
    RecyclerView recyclerView;
    DBHandler db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_tat_ca_cong_viec, container, false);
        recyclerView = (RecyclerView) myView.findViewById(R.id.recyclerview_tatcacongviec);
        recyclerView.setHasFixedSize(true);
        db = new DBHandler(getContext());
        get_all_congviec();
        CongViec congViec = new CongViec(1,"title","address","1/1/2017","9:00:00","10:00:00","note");
        CongViec congViec1 = new CongViec(2,"title1","address1","1/1/2017","9:00:00","10:00:00","note1");
        CongViec congViec2 = new CongViec(3,"title2","address2","1/1/2017","9:00:00","10:00:00","note2");
        list_congviec.add(congViec);
        list_congviec.add(congViec1);
        list_congviec.add(congViec2);
        System.out.println("__________________" + list_congviec.size());

        adapter = new TatCaCongViecAdapter(list_congviec, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return myView;
    }



    public void get_all_congviec()  {
        list_congviec = new ArrayList<>();
            list_congviec=db.get_all_congviec();
    }
}
