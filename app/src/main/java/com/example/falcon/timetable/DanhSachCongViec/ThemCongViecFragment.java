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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.falcon.timetable.R;

import java.util.Calendar;


public class ThemCongViecFragment extends Fragment {
    View myView;
    int option = 0;
    EditText title, address, description;
    RadioGroup radioGroup;
    Button save;
    RadioButton rd_kll, rd_llttuan, rd_lltthang, rd_llhangngay;
    LinearLayout layoutk, layouthn, layouttt, layouttth;
    TextView kll_ngay, kll_gio_bd, kll_gio_kt; /*ll_gio_bd, ll_gio_kt, ll_ngay, lltt_ngay, lltt_gio_bd, lltt_gio_kt, lltth_ngay, lltth_gio_bd, lltth_gio_kt;*/
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
        /*layouthn = (LinearLayout) myView.findViewById(R.id.layout_hangngay);
        layouttt = (LinearLayout) myView.findViewById(R.id.layout_theotuan);
        layouttth = (LinearLayout) myView.findViewById(R.id.layout_theothang);*/
        save = (Button) myView.findViewById(R.id.btn_themcongviec_luu);

        //khong lap lai
        kll_ngay = (TextView) myView.findViewById(R.id.tv_kll_chonngay);
        kll_gio_bd = (TextView) myView.findViewById(R.id.tv_kll_chongio_batdau);
        kll_gio_kt = (TextView) myView.findViewById(R.id.tv_kll_chongio_dukienkt);

        /*layoutk.setVisibility(View.INVISIBLE);
        layouttth.setVisibility(View.INVISIBLE);
        layouttt.setVisibility(View.INVISIBLE);
        layouthn.setVisibility(View.INVISIBLE);*/
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                // Add logic here
                FragmentManager fragmentManager = getFragmentManager();
                switch (index) {
                    case 0: // first button
                        option = 1;
                        break;
                    case 1: // secondbutton
                        option = 2;
                        break;
                    case 2: // secondbutton
                        option = 3;
                        break;
                    case 3: // secondbutton
                        option = 4;
                        break;
                }
            }
        });

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
                                                        if (dayf > 9)
                                                            kll_ngay.setText(monthf + " - " + dayf + " - " + yearf);
                                                        else
                                                            kll_ngay.setText(monthf + " - 0" + dayf + " - " + yearf);

                                                    } else {
                                                        if (dayf > 9)
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
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (option == 1) {
                    if (title.getText().toString().equals("")) {
                        Toast.makeText(getActivity(), "Vui lòng nhập tên công việc!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (address.getText().toString().equals("")) {
                            Toast.makeText(getActivity(), "Vui lòng nhập địa điểm!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (kll_ngay.getText().toString().equals("")) {
                                Toast.makeText(getActivity(), "Vui lòng chọn ngày!", Toast.LENGTH_SHORT).show();
                            } else {
                                if (kll_gio_bd.getText().toString().equals("")) {
                                    Toast.makeText(getActivity(), "Vui lòng chọn giờ bắt đầu!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (kll_gio_kt.getText().toString().equals("")) {
                                        Toast.makeText(getActivity(), "Vui lòng chọn giờ dự kiến kết thúc!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getActivity(), title.getText().toString() + "\n"
                                                + address.getText().toString() + "\n"
                                                + description.getText().toString() + "\n"
                                                + kll_ngay.getText().toString() + "\n"
                                                + kll_gio_bd.getText().toString() + "\n"
                                                + kll_gio_kt.getText().toString() + "\n"
                                                + "Không lặp lại!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (option == 2) {
                        if (title.getText().toString().equals("")) {
                            Toast.makeText(getActivity(), "Vui lòng nhập tên công việc!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (address.getText().toString().equals("")) {
                                Toast.makeText(getActivity(), "Vui lòng nhập địa điểm!", Toast.LENGTH_SHORT).show();
                            } else {
                                if (kll_ngay.getText().toString().equals("")) {
                                    Toast.makeText(getActivity(), "Vui lòng chọn ngày!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (kll_gio_bd.getText().toString().equals("")) {
                                        Toast.makeText(getActivity(), "Vui lòng chọn giờ bắt đầu!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (kll_gio_kt.getText().toString().equals("")) {
                                            Toast.makeText(getActivity(), "Vui lòng chọn giờ dự kiến kết thúc!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getActivity(), title.getText().toString() + "\n"
                                                    + address.getText().toString() + "\n"
                                                    + description.getText().toString() + "\n"
                                                    + kll_ngay.getText().toString() + "\n"
                                                    + kll_gio_bd.getText().toString() + "\n"
                                                    + kll_gio_kt.getText().toString() + "\n"
                                                    + "Lặp lại hàng ngày!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (option == 3) {
                            if (title.getText().toString().equals("")) {
                                Toast.makeText(getActivity(), "Vui lòng nhập tên công việc!", Toast.LENGTH_SHORT).show();
                            } else {
                                if (address.getText().toString().equals("")) {
                                    Toast.makeText(getActivity(), "Vui lòng nhập địa điểm!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (kll_ngay.getText().toString().equals("")) {
                                        Toast.makeText(getActivity(), "Vui lòng chọn ngày!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (kll_gio_bd.getText().toString().equals("")) {
                                            Toast.makeText(getActivity(), "Vui lòng chọn giờ bắt đầu!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (kll_gio_kt.getText().toString().equals("")) {
                                                Toast.makeText(getActivity(), "Vui lòng chọn giờ dự kiến kết thúc!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getActivity(), title.getText().toString() + "\n"
                                                        + address.getText().toString() + "\n"
                                                        + description.getText().toString() + "\n"
                                                        + kll_ngay.getText().toString() + "\n"
                                                        + kll_gio_bd.getText().toString() + "\n"
                                                        + kll_gio_kt.getText().toString() + "\n"
                                                        + "Lặp lại hàng tuần!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            if (option == 4) {
                                if (title.getText().toString().equals("")) {
                                    Toast.makeText(getActivity(), "Vui lòng nhập tên công việc!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (address.getText().toString().equals("")) {
                                        Toast.makeText(getActivity(), "Vui lòng nhập địa điểm!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (kll_ngay.getText().toString().equals("")) {
                                            Toast.makeText(getActivity(), "Vui lòng chọn ngày!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (kll_gio_bd.getText().toString().equals("")) {
                                                Toast.makeText(getActivity(), "Vui lòng chọn giờ bắt đầu!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                if (kll_gio_kt.getText().toString().equals("")) {
                                                    Toast.makeText(getActivity(), "Vui lòng chọn giờ dự kiến kết thúc!", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(getActivity(), title.getText().toString() + "\n"
                                                            + address.getText().toString() + "\n"
                                                            + description.getText().toString() + "\n"
                                                            + kll_ngay.getText().toString() + "\n"
                                                            + kll_gio_bd.getText().toString() + "\n"
                                                            + kll_gio_kt.getText().toString() + "\n"
                                                            + "Lặp lại hàng tháng!", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                Toast.makeText(getActivity(), title.getText().toString() + "\n"
                                        + address.getText().toString() + "\n"
                                        + description.getText().toString() + "\n"
                                        + "Bạn chưa chọn chế độ lặp", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        });
        return myView;
    }
}














/*
//lap lai hang ngay
        ll_ngay = (TextView) myView.findViewById(R.id.tv_ll_chonngay);
                ll_gio_bd = (TextView) myView.findViewById(R.id.tv_ll_chongio_batdau);
                ll_gio_kt = (TextView) myView.findViewById(R.id.tv_ll_chongio_dukienkt);

                //lap lai theo tuan
                lltt_ngay = (TextView) myView.findViewById(R.id.tv_lltt_chonngay);
                lltt_gio_bd = (TextView) myView.findViewById(R.id.tv_lltt_chongio_batdau);
                lltt_gio_kt = (TextView) myView.findViewById(R.id.tv_lltt_chongio_dukienkt);

                //lap lai theo thang
                lltth_ngay = (TextView) myView.findViewById(R.id.tv_lltth_chonngay);
                lltth_gio_bd = (TextView) myView.findViewById(R.id.tv_lltth_chongio_batdau);
                lltth_gio_kt = (TextView) myView.findViewById(R.id.tv_lltth_chongio_dukienkt);

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
        if (dayf > 9)
        kll_ngay.setText(monthf + " - " + dayf + " - " + yearf);
        else
        kll_ngay.setText(monthf + " - 0" + dayf + " - " + yearf);

        } else {
        if (dayf > 9)
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
                        */
/*fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new KhongLapLaiFragment())
                                .addToBackStack(null)
                                .commit();*//*

        break;
        case 1: // secondbutton
        layoutk.setVisibility(View.GONE);
        layouttth.setVisibility(View.GONE);
        layouttt.setVisibility(View.GONE);
        layouthn.setVisibility(View.VISIBLE);
        ll_ngay.setOnClickListener(new View.OnClickListener() {
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
        if (dayf > 9)
        ll_ngay.setText(monthf + " - " + dayf + " - " + yearf);
        else
        ll_ngay.setText(monthf + " - 0" + dayf + " - " + yearf);

        } else {
        if (dayf > 9)
        ll_ngay.setText("0" + monthf + " - " + dayf + " - " + yearf);
        else
        ll_ngay.setText("0" + monthf + " - 0" + dayf + " - " + yearf);
        }
        }
        }, year, month, day);
        datePickerDialog.show();
        }
        }
        );
        ll_gio_bd.setOnClickListener(new View.OnClickListener() {
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
        ll_gio_bd.setText(hourf + ":" + minutef);
        else
        ll_gio_bd.setText("0" + hourf + ":" + minutef);
        } else {
        if (hourf > 9)
        ll_gio_bd.setText(hourf + ":0" + minutef);
        else
        ll_gio_bd.setText("0" + hourf + ":0" + minutef);
        }
        }
        }, hour, minute, true);
        timePickerDialog.show();
        }
        });
        ll_gio_kt.setOnClickListener(new View.OnClickListener() {
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
        ll_gio_kt.setText(hourf + ":" + minutef);
        } else {
        ll_gio_kt.setText("0" + hourf + ":" + minutef);
        }
        } else {
        if (hourf > 9) {
        ll_gio_kt.setText(hourf + ":0" + minutef);
        } else {
        ll_gio_kt.setText("0" + hourf + ":0" + minutef);
        }
        }
        }
        }, hour, minute, true);
        timePickerDialog.show();
        }
        });
                        */
/*fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new LapLaiHangNgayFragment())
                                .addToBackStack(null)
                                .commit();*//*

        break;
        case 2: // secondbutton
        layoutk.setVisibility(View.GONE);
        layouttth.setVisibility(View.GONE);
        layouttt.setVisibility(View.VISIBLE);
        layouthn.setVisibility(View.GONE);
                        */
/*fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new LapLaiTheoTuanFragment())
                                .addToBackStack(null)
                                .commit();*//*

        break;
        case 3: // secondbutton
        layoutk.setVisibility(View.GONE);
        layouttth.setVisibility(View.VISIBLE);
        layouttt.setVisibility(View.GONE);
        layouthn.setVisibility(View.GONE);
                       */
/* fragmentManager.beginTransaction().replace(R.id.content_frame_themcongviec, new LapLaiTheoThangFragment())
                                .addToBackStack(null)
                                .commit();*//*

        break;
        }
        }
        });*/
