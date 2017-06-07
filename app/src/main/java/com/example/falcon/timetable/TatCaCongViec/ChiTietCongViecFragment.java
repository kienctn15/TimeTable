package com.example.falcon.timetable.TatCaCongViec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.falcon.timetable.R;

/**
 * Created by Admin on 6/7/2017.
 */

public class ChiTietCongViecFragment extends Fragment {
    View myView;
    TextView tv_chitiet_congviec_title,tv_chitiet_congviec_address,tv_chitiet_congviec_date,tv_chitiet_congviec_time,tv_chitiet_congviec_note;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_chitet_congviec, container, false);

        tv_chitiet_congviec_title = (TextView) myView.findViewById(R.id.tv_chitiet_congviec_title);
        tv_chitiet_congviec_address = (TextView) myView.findViewById(R.id.tv_chitiet_congviec_address);
        tv_chitiet_congviec_date = (TextView) myView.findViewById(R.id.tv_chitiet_congviec_date);
        tv_chitiet_congviec_time = (TextView) myView.findViewById(R.id.tv_chitiet_congviec_time);
        tv_chitiet_congviec_note = (TextView) myView.findViewById(R.id.tv_chitiet_congviec_note);

        Bundle bundle = this.getArguments();
        if(bundle!=null){
            tv_chitiet_congviec_title.setText(bundle.getString("title"));
            tv_chitiet_congviec_address.setText(bundle.getString("address"));
            tv_chitiet_congviec_date.setText(bundle.getString("date"));
            tv_chitiet_congviec_time.setText(bundle.getString("time_start") + " - " + bundle.getString("time_end"));
            tv_chitiet_congviec_note.setText(bundle.getString("description"));

        }

        return myView;
    }
}
