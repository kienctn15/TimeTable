package com.example.falcon.timetable.DanhSachCongViec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.falcon.timetable.DanhSachCongViec.LichTrinh.KhongLapLaiFragment;
import com.example.falcon.timetable.DanhSachCongViec.LichTrinh.LapLaiHangNgayFragment;
import com.example.falcon.timetable.DanhSachCongViec.LichTrinh.LapLaiTheoThangFragment;
import com.example.falcon.timetable.DanhSachCongViec.LichTrinh.LapLaiTheoTuanFragment;
import com.example.falcon.timetable.R;


public class ThemCongViecFragment extends Fragment {
    View myView;
    EditText title, address, description;
    RadioGroup radioGroup;
    RadioButton rd_kll, rd_llttuan, rd_lltthang, rd_llhangngay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_them_cong_viec, container, false);

        title = (EditText) myView.findViewById(R.id.edt_themcongviec_ten);
        address = (EditText) myView.findViewById(R.id.edt_themcongviec_diadiem);
        description = (EditText) myView.findViewById(R.id.edt_themcongviec_mota);
        radioGroup = (RadioGroup) myView.findViewById(R.id.rbg_themcongviec);
        rd_kll = (RadioButton) myView.findViewById(R.id.rbg_rd_khongll);
        rd_llhangngay = (RadioButton) myView.findViewById(R.id.rbg_rd_llhangngay);
        rd_lltthang = (RadioButton) myView.findViewById(R.id.rbg_rd_lltheothang);
        rd_llttuan = (RadioButton) myView.findViewById(R.id.rbg_rd_lltheotuan);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                // Add logic here
                FragmentManager fragmentManager = getFragmentManager();
                switch (index) {
                    case 0: // first button
                        fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new KhongLapLaiFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 1: // secondbutton
                        fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new LapLaiHangNgayFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 2: // secondbutton
                        fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new LapLaiTheoTuanFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 3: // secondbutton
                        fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new LapLaiTheoThangFragment())
                                .addToBackStack(null)
                                .commit();
                        break;
                }
            }
        });
        /*if (rd_kll.isChecked()) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new KhongLapLaiFragment())
                    .addToBackStack(null)
                    .commit();
        }

        if (rd_llhangngay.isChecked()) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new LapLaiHangNgayFragment())
                    .addToBackStack(null)
                    .commit();
        }

        if(rd_llttuan.isChecked()){
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new LapLaiTheoTuanFragment())
                        .addToBackStack(null)
                        .commit();

            }
        if(rd_lltthang.isChecked()){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new LapLaiTheoThangFragment())
                    .addToBackStack(null)
                    .commit();

        }*/

        return myView;
    }
}
