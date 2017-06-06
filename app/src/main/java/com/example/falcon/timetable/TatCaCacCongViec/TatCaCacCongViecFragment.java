package com.example.falcon.timetable.TatCaCacCongViec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.falcon.timetable.R;

/**
 * Created by Admin on 6/6/2017.
 */

public class TatCaCacCongViecFragment extends Fragment {
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_tatca_cac_congviec, container, false);



        return myView;
    }
}
