package com.example.falcon.timetable.DanhSachCongViec;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.falcon.timetable.DBHandler.DBHandler;
import com.example.falcon.timetable.R;

import static com.example.falcon.timetable.R.id.tv_chitiet_congviec_note;
import static com.example.falcon.timetable.R.id.tv_chitiet_congviec_time;


public class SuaCongViecFragment extends Fragment {
    View myView;
    EditText edt_suacongviec_ten, edt_suacongviec_diadiem, edt_suacongviec_mota;
    TextView tv_suacongviec_thoigian;
    Button btn_suacongviec_luu;
    DBHandler db;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_sua_cong_viec, container, false);

        initFindViewById();
        db = new DBHandler(getActivity());
        bundle = this.getArguments();
        if (bundle != null) {
            edt_suacongviec_ten.setText(bundle.getString("title"));
            edt_suacongviec_diadiem.setText(bundle.getString("address"));
            edt_suacongviec_mota.setText(bundle.getString("description"));
        }

        btn_suacongviec_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edt_suacongviec_ten.getText().toString().trim();
                String address = edt_suacongviec_diadiem.getText().toString().trim();
                String note = edt_suacongviec_mota.getText().toString().trim();

                if (title.length() <= 0 || address.length() <= 0 || note.length() <= 0) {
                    Toast.makeText(getActivity(), "Hãy điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    CongViec congViec = new CongViec();
                    congViec.setId(Integer.parseInt(bundle.getString("id")));
                    congViec.setTitle(title);
                    congViec.setAddress(address);
                    congViec.setNote(note);
                    db.update_congviec(congViec);
                }
            }
        });

        return myView;
    }

    private void initFindViewById() {
        edt_suacongviec_ten = (EditText) myView.findViewById(R.id.edt_suacongviec_ten);
        edt_suacongviec_diadiem = (EditText) myView.findViewById(R.id.edt_suacongviec_diadiem);
        edt_suacongviec_mota = (EditText) myView.findViewById(R.id.edt_suacongviec_mota);
        tv_suacongviec_thoigian = (TextView) myView.findViewById(R.id.tv_suacongviec_thoigian);
        btn_suacongviec_luu = (Button) myView.findViewById(R.id.btn_suacongviec_luu);

    }
}
