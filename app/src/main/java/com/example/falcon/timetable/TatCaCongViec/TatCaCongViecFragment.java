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
      
        db = new DBHandler(getContext());

        HienThiTatCaCongViec();

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return myView;
    }

    public void HienThiTatCaCongViec(){
        list_congviec = new ArrayList<>();

        list_congviec=db.get_all_congviec();

        adapter = new TatCaCongViecAdapter(list_congviec, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

}
