package com.example.falcon.timetable.DanhSachCongViec;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.falcon.timetable.R;

import java.util.Calendar;


public class ThemCongViecFragment extends Fragment {
    View myView;
    EditText title, address, description;
    RadioGroup radioGroup;
    RadioButton rd_kll, rd_llttuan, rd_lltthang, rd_llhangngay;
    LinearLayout layoutk, layouthn, layouttt, layouttth;
    TextView kll_ngay, kll_gio_bd, kll_gio_kt;
    int day, month, year, dayf, monthf, yearf, hour, minute, hourf, minutef;

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
        layoutk = (LinearLayout) myView.findViewById(R.id.layout_khonglaplai);
        layouthn = (LinearLayout) myView.findViewById(R.id.layout_hangngay);
        layouttt = (LinearLayout) myView.findViewById(R.id.layout_theotuan);
        layouttth = (LinearLayout) myView.findViewById(R.id.layout_theothang);

        //khong lap lai
        kll_ngay = (TextView) myView.findViewById(R.id.tv_kll_chonngay);
        kll_gio_bd = (TextView) myView.findViewById(R.id.tv_kll_chongio_batdau);
        kll_gio_kt = (TextView) myView.findViewById(R.id.tv_kll_chongio_dukienkt);

        layoutk.setVisibility(View.INVISIBLE);
        layouttth.setVisibility(View.INVISIBLE);
        layouttt.setVisibility(View.INVISIBLE);
        layouthn.setVisibility(View.INVISIBLE);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                // Add logic here
                FragmentManager fragmentManager = getFragmentManager();
                switch (index) {
                    case 0: // first button
                        layoutk.setVisibility(View.VISIBLE);
                        layouttth.setVisibility(View.GONE);
                        layouttt.setVisibility(View.GONE);
                        layouthn.setVisibility(View.GONE);
                        kll_ngay.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            Calendar c = Calendar.getInstance();// sử dụng cái này đê cài đặt thời gian hiện tại
                                                            year = c.get(Calendar.YEAR);       // cho cái timepicker
                                                            month = c.get(Calendar.MONTH);
                                                            day = c.get(Calendar.DAY_OF_MONTH);
                                                            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                                                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                                                    yearf = year;
                                                                    monthf = month + 1;
                                                                    dayf = dayOfMonth;
                                                                    if (monthf > 9) {
                                                                        if (day > 9)
                                                                            kll_ngay.setText(monthf + " - " + dayf + " - " + yearf);
                                                                        else
                                                                            kll_ngay.setText(monthf + " - 0" + dayf + " - " + yearf);

                                                                    } else {
                                                                        if (day > 9)
                                                                            kll_ngay.setText("0" + monthf + " - " + dayf + " - " + yearf);
                                                                        else
                                                                            kll_ngay.setText("0" + monthf + " - 0" + dayf + " - " + yearf);
                                                                    }
                                                                }
                                                            }, year, month, day);
                                                            datePickerDialog.show();
                                                        }
                                                    }
                        );
                        kll_gio_bd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Calendar c = Calendar.getInstance();
                                hour = c.get(Calendar.HOUR_OF_DAY);
                                minute = c.get(Calendar.MINUTE);
                                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        hourf = hourOfDay;
                                        minutef = minute;
                                        if (minutef > 9) {
                                            if (hourf > 9)
                                                kll_gio_bd.setText(hourf + ":" + minutef);
                                            else
                                                kll_gio_bd.setText("0" + hourf + ":" + minutef);
                                        } else {
                                            if (hourf > 9)
                                                kll_gio_bd.setText(hourf + ":0" + minutef);
                                            else
                                                kll_gio_bd.setText("0" + hourf + ":0" + minutef);
                                        }
                                    }
                                }, hour, minute, true);
                                timePickerDialog.show();
                            }
                        });
                        kll_gio_kt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Calendar c = Calendar.getInstance();
                                hour = c.get(Calendar.HOUR_OF_DAY);
                                minute = c.get(Calendar.MINUTE);
                                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        hourf = hourOfDay;
                                        minutef = minute;
                                        if (minutef > 9) {
                                            if (hourf > 9) {
                                                kll_gio_kt.setText(hourf + ":" + minutef);
                                            } else {
                                                kll_gio_kt.setText("0" + hourf + ":" + minutef);
                                            }
                                        } else {
                                            if (hourf > 9) {
                                                kll_gio_kt.setText(hourf + ":0" + minutef);
                                            } else {
                                                kll_gio_kt.setText("0" + hourf + ":0" + minutef);
                                            }
                                        }
                                    }
                                }, hour, minute, true);
                                timePickerDialog.show();
                            }
                        });
                        /*fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new KhongLapLaiFragment())
                                .addToBackStack(null)
                                .commit();*/
                        break;
                    case 1: // secondbutton
                        layoutk.setVisibility(View.GONE);
                        layouttth.setVisibility(View.GONE);
                        layouttt.setVisibility(View.GONE);
                        layouthn.setVisibility(View.VISIBLE);
                        /*fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new LapLaiHangNgayFragment())
                                .addToBackStack(null)
                                .commit();*/
                        break;
                    case 2: // secondbutton
                        layoutk.setVisibility(View.GONE);
                        layouttth.setVisibility(View.GONE);
                        layouttt.setVisibility(View.VISIBLE);
                        layouthn.setVisibility(View.GONE);
                        /*fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new LapLaiTheoTuanFragment())
                                .addToBackStack(null)
                                .commit();*/
                        break;
                    case 3: // secondbutton
                        layoutk.setVisibility(View.GONE);
                        layouttth.setVisibility(View.VISIBLE);
                        layouttt.setVisibility(View.GONE);
                        layouthn.setVisibility(View.GONE);
                       /* fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new LapLaiTheoThangFragment())
                                .addToBackStack(null)
                                .commit();*/
                        break;
                }
            }
        });
        return myView;
    }
}
