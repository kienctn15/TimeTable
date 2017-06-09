package com.example.falcon.timetable.TatCaCongViec;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.falcon.timetable.DBHandler.DBHandler;
import com.example.falcon.timetable.DanhSachCongViec.CongViec;
import com.example.falcon.timetable.R;

import java.util.Calendar;

/**
 * Created by Admin on 6/7/2017.
 */

public class ChiTietCongViecFragment extends Fragment {
    View myView;
    TextView tv_chitiet_congviec_title, tv_chitiet_congviec_address, tv_chitiet_congviec_date, tv_chitiet_congviec_time_start, tv_chitiet_congviec_time_end, tv_chitiet_congviec_note, tv_chitiet_congviec_edit, tv_chitiet_congviec_save;
    EditText edt_chitiet_congviec_title, edt_chitiet_congviec_address, edt_chitiet_congviec_note;
    ImageView  img_chitiet_congviec_time_start, img_chitiet_congviec_time_end,img_chitiet_congviec_date;
    Bundle bundle;

    DBHandler db;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_chitet_congviec, container, false);

        tv_chitiet_congviec_title = (TextView) myView.findViewById(R.id.tv_chitiet_congviec_title);
        tv_chitiet_congviec_address = (TextView) myView.findViewById(R.id.tv_chitiet_congviec_address);
        tv_chitiet_congviec_date = (TextView) myView.findViewById(R.id.tv_chitiet_congviec_date);
        tv_chitiet_congviec_time_start = (TextView) myView.findViewById(R.id.tv_chitiet_congviec_time_start);
        tv_chitiet_congviec_time_end = (TextView) myView.findViewById(R.id.tv_chitiet_congviec_time_end);
        tv_chitiet_congviec_note = (TextView) myView.findViewById(R.id.tv_chitiet_congviec_note);

        tv_chitiet_congviec_edit = (TextView) myView.findViewById(R.id.tv_chitiet_congviec_edit);
        tv_chitiet_congviec_save = (TextView) myView.findViewById(R.id.tv_chitiet_congviec_save);
        edt_chitiet_congviec_title = (EditText) myView.findViewById(R.id.edt_chitiet_congviec_title);
        edt_chitiet_congviec_address = (EditText) myView.findViewById(R.id.edt_chitiet_congviec_address);
        edt_chitiet_congviec_note = (EditText) myView.findViewById(R.id.edt_chitiet_congviec_note);
        img_chitiet_congviec_time_start = (ImageView) myView.findViewById(R.id.img_chitiet_congviec_time_start);
        img_chitiet_congviec_time_end= (ImageView) myView.findViewById(R.id.img_chitiet_congviec_time_end);
        img_chitiet_congviec_date = (ImageView) myView.findViewById(R.id.img_chitiet_congviec_date);

        db = new DBHandler(getActivity());

        bundle = this.getArguments();
        System.out.println(bundle.getString("time_start"));
        System.out.println(bundle.getString("time_end"));
        if (bundle != null) {
            tv_chitiet_congviec_title.setText(bundle.getString("title"));
            tv_chitiet_congviec_address.setText(bundle.getString("address"));
            tv_chitiet_congviec_date.setText(bundle.getString("date"));
            tv_chitiet_congviec_time_start.setText(bundle.getString("time_start") + " - ");
            tv_chitiet_congviec_time_end.setText(bundle.getString("time_end"));
            tv_chitiet_congviec_note.setText(bundle.getString("description"));

        }

        tv_chitiet_congviec_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowEdit();

            }
        });

        tv_chitiet_congviec_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edt_chitiet_congviec_title.getText().toString().trim();
                String address = edt_chitiet_congviec_address.getText().toString().trim();
                String date = tv_chitiet_congviec_date.getText().toString().trim();
                String note = edt_chitiet_congviec_note.getText().toString().trim();
                String time_start=tv_chitiet_congviec_time_start.getText().toString();
                String time_end = tv_chitiet_congviec_time_end.getText().toString();

                System.out.println(Integer.parseInt(bundle.getString("id")));
                System.out.println(title);
                System.out.println(address);
                System.out.println(date);
                System.out.println(time_start);
                System.out.println(time_end);
                System.out.println(note);


                if(title.length() >0 && address.length()>0 && date.length()>0 && note.length()>0 && time_start.length()>0 && time_end.length()>0){

                    CongViec congViec = new CongViec();

                    congViec.setId(Integer.parseInt(bundle.getString("id")));
                    congViec.setTitle(title);
                    congViec.setAddress(address);
                    congViec.setDate(date);
                    congViec.setNote(note);
                    congViec.setTime_start(time_start);
                    congViec.setTime_end(time_end);

                    db.update_congviec(congViec);


                }else{
                    Toast.makeText(getActivity(), "Hãy nhập đầy đủ thông tin! ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        img_chitiet_congviec_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();// sử dụng cái này đê cài đặt thời gian hiện tại
                int year = c.get(Calendar.YEAR);       // cho cái timepicker
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int yearf = year;
                        int monthf = month + 1;
                        int dayf = dayOfMonth;
                        if (monthf > 9) {
                            if (dayf > 9)
                                tv_chitiet_congviec_date.setText(dayf + "/" + monthf + "/" + yearf);
                            else
                                tv_chitiet_congviec_date.setText( "0" + dayf +"/"+monthf + "/" + yearf);

                        } else {
                            if (dayf > 9)
                                tv_chitiet_congviec_date.setText(dayf +"/0" + monthf +"/" + yearf);
                            else
                                tv_chitiet_congviec_date.setText("0" + dayf + "/0" + monthf + "/" + yearf);
                        }
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        img_chitiet_congviec_time_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        int hourf = hourOfDay;
                        int minutef = minute;
                        if (minutef > 9) {
                            if (hourf > 9)
                                tv_chitiet_congviec_time_start.setText(hourf + ":" + minutef +":00");
                            else
                                tv_chitiet_congviec_time_start.setText("0" + hourf + ":" + minutef+":00");
                        } else {
                            if (hourf > 9)
                                tv_chitiet_congviec_time_start.setText(hourf + ":0" + minutef+":00");
                            else
                                tv_chitiet_congviec_time_start.setText("0" + hourf + ":0" + minutef+":00");
                        }
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });

        img_chitiet_congviec_time_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        int hourf = hourOfDay;
                        int minutef = minute;
                        if (minutef > 9) {
                            if (hourf > 9) {
                                tv_chitiet_congviec_time_end.setText(hourf + ":" + minutef+":00");
                            } else {
                                tv_chitiet_congviec_time_end.setText("0" + hourf + ":" + minutef+":00");
                            }
                        } else {
                            if (hourf > 9) {
                                tv_chitiet_congviec_time_end.setText(hourf + ":0" + minutef+":00");
                            } else {
                                tv_chitiet_congviec_time_end.setText("0" + hourf + ":0" + minutef+":00");
                            }
                        }
                    }
                }, hour, minute, true);
                timePickerDialog.show();
            }
        });

        return myView;
    }

    private void ShowEdit() {
        tv_chitiet_congviec_title.setVisibility(View.GONE);
        tv_chitiet_congviec_address.setVisibility(View.GONE);
        tv_chitiet_congviec_note.setVisibility(View.GONE);

        tv_chitiet_congviec_edit.setVisibility(View.GONE);

        tv_chitiet_congviec_save.setVisibility(View.VISIBLE);
        edt_chitiet_congviec_title.setVisibility(View.VISIBLE);
        edt_chitiet_congviec_address.setVisibility(View.VISIBLE);
        edt_chitiet_congviec_note.setVisibility(View.VISIBLE);
        img_chitiet_congviec_time_start.setVisibility(View.VISIBLE);
        img_chitiet_congviec_time_end.setVisibility(View.VISIBLE);
        img_chitiet_congviec_date.setVisibility(View.VISIBLE);

        edt_chitiet_congviec_title.setText(bundle.getString("title"));
        edt_chitiet_congviec_address.setText(bundle.getString("address"));
        edt_chitiet_congviec_note.setText(bundle.getString("description"));
    }
}
