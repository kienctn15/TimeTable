package com.example.falcon.timetable.DanhSachCongViec;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.falcon.timetable.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DanhSachCongViecFragment extends Fragment {
    View myView;
    ArrayList<String> data;
    List<CongViec> listCv;
    DanhSachCongViecAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_danh_sach_cong_viec, container, false);
        recyclerView = (RecyclerView) myView.findViewById(R.id.recyclerview_danhsachcongviec);
        recyclerView.setHasFixedSize(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            data = bundle.getStringArrayList("data");
        }
        listCv = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            try {
                JSONObject obj = new JSONObject(data.get(i));
                CongViec congViec = new CongViec();
                congViec.setId(Integer.parseInt(obj.getString("id")));
                congViec.setTitle(obj.getString("title"));
                congViec.setAddress(obj.getString("address"));
                congViec.setDate(obj.getString("date"));
                congViec.setTime_start(obj.getString("start"));
                congViec.setTime_end(obj.getString("end"));
                congViec.setNote(obj.getString("note"));

                listCv.add(congViec);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println(data.size()+"----------------------------------------");
        adapter = new DanhSachCongViecAdapter(listCv, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        return myView;
    }
}
