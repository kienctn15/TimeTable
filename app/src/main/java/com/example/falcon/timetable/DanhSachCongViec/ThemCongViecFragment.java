package com.example.falcon.timetable.DanhSachCongViec;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
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

import com.example.falcon.timetable.AlarmNotificationReceiver;
import com.example.falcon.timetable.DBHandler.DBHandler;
import com.example.falcon.timetable.R;
import com.example.falcon.timetable.ThoiGianBieu_Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;


public class ThemCongViecFragment extends Fragment {
    DBHandler db;
    View myView;
    int option = 0;
    EditText title, address, description;
    RadioGroup radioGroup;
    Button save;
    RadioButton rd_kll, rd_llttuan, rd_lltthang, rd_llhangngay;
    LinearLayout layoutk, layouthn, layouttt, layouttth;
    TextView kll_ngay, kll_gio_bd, kll_gio_kt; /*ll_gio_bd, ll_gio_kt, ll_ngay, lltt_ngay, lltt_gio_bd, lltt_gio_kt, lltth_ngay, lltth_gio_bd, lltth_gio_kt;*/
    AlarmManager alarmManager;
    private int day, month, year, dayf, monthf, yearf, hour, minute, hourf, minutef, hourf1, minutef1;
    private String strdate, strstart, strend;
    private PendingIntent pendingIntent;

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

        db = new DBHandler(getActivity());

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
                                                        if (dayf > 9) {
                                                            kll_ngay.setText(dayf + "/" + monthf + "/" + yearf);
                                                            strdate = dayf + "/" + monthf + "/" + yearf;
                                                        } else {
                                                            kll_ngay.setText("0" + dayf + "/" + monthf + "/" + yearf);
                                                            strdate = "0" + dayf + "/" + monthf + "/" + yearf;
                                                        }
                                                    } else {
                                                        if (dayf > 9) {
                                                            kll_ngay.setText(dayf + "/" + "0" + monthf + "/" + yearf);
                                                            strdate = "0" + dayf + "/0" + monthf + "/" + yearf;
                                                        } else {
                                                            kll_ngay.setText("0" + dayf + "/" + "0" + monthf + "/" + yearf);
                                                            strdate = "0" + dayf + "/0" + monthf + "/" + yearf;
                                                        }

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
                            if (hourf > 9) {
                                kll_gio_bd.setText(hourf + ":" + minutef);
                                strstart = hourf + ":" + minutef + ":00";
                            } else {
                                kll_gio_bd.setText("0" + hourf + ":" + minutef);
                                strstart = "0" + hourf + ":" + minutef + ":00";
                            }
                        } else {
                            if (hourf > 9) {
                                kll_gio_bd.setText(hourf + ":0" + minutef);
                                strstart = hourf + ":0" + minutef + ":00";
                            } else {
                                kll_gio_bd.setText("0" + hourf + ":0" + minutef);
                                strstart = "0" + hourf + ":0" + minutef + ":00";
                            }
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
                        hourf1 = hourOfDay;
                        minutef1 = minute;
                        if (minutef1 > 9) {
                            if (hourf1 > 9) {
                                kll_gio_kt.setText(hourf1 + ":" + minutef1);
                                strend = hourf1 + ":" + minutef1 + ":00";
                            } else {
                                kll_gio_kt.setText("0" + hourf1 + ":" + minutef1);
                                strend = "0" + hourf1 + ":" + minutef1 + ":00";
                            }
                        } else {
                            if (hourf1 > 9) {
                                kll_gio_kt.setText(hourf1 + ":0" + minutef1);
                                strend = hourf1 + ":0" + minutef1 + ":00";
                            } else {
                                kll_gio_kt.setText("0" + hourf1 + ":0" + minutef1);
                                strend = "0" + hourf1 + ":0" + minutef1 + ":00";
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
                            if (kll_ngay.getText().toString().equals("Ngày")) {
                                Toast.makeText(getActivity(), "Vui lòng chọn ngày!", Toast.LENGTH_SHORT).show();
                            } else {
                                if (kll_gio_bd.getText().toString().equals("Giờ bắt đầu")) {
                                    Toast.makeText(getActivity(), "Vui lòng chọn giờ bắt đầu!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (kll_gio_kt.getText().toString().equals("Dự kiến kết thúc")) {
                                        Toast.makeText(getActivity(), "Vui lòng chọn giờ dự kiến kết thúc!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (TimeValidator(kll_gio_bd.getText().toString(), kll_gio_kt.getText().toString()) == false) {
                                            Toast.makeText(getActivity(), "Vui lòng chọn giờ dự kiến kết thúc LỚN HƠN giờ bắt đầu!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            CongViec congViec = new CongViec();
                                            congViec.setTitle(title.getText().toString());
                                            congViec.setAddress(address.getText().toString());
                                            congViec.setDate(kll_ngay.getText().toString());
                                            congViec.setTime_start(kll_gio_bd.getText().toString());
                                            congViec.setTime_end(kll_gio_kt.getText().toString());
                                            congViec.setNote(description.getText().toString());
                                            db.insert_table_congviec_laplai(congViec);

                                            Calendar calendar = Calendar.getInstance();
                                            calendar.set(Calendar.YEAR, yearf);
                                            calendar.set(Calendar.MONTH, monthf - 1);
                                            calendar.set(Calendar.DAY_OF_MONTH, dayf);
                                            calendar.set(Calendar.HOUR_OF_DAY, hourf);
                                            calendar.set(Calendar.MINUTE, minutef);
                                            calendar.set(Calendar.SECOND, 00);
                                            Log.d("TEst Alarm", "Chay ngon cmnr");
                                            Log.d("TEst Alarm",
                                                    "---------------------------------------------\n" +
                                                            "---------------------------------------------\n" +
                                                            "---------------------------------------------\n" +
                                                            "---------------------------------------------" + calendar.getTime());
                                            Log.d("TEst Alarm",
                                                    "---------------------------------------------\n" +
                                                            "---------------------------------------------\n" +
                                                            "---------------------------------------------\n" +
                                                            "---------------------------------------------" + calendar.getTimeInMillis());
                                            Intent myIntent = new Intent(getActivity(), AlarmNotificationReceiver.class);
                                            pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, myIntent, 0);

                                            AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                                            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() - 150000, pendingIntent);
                                            Toast.makeText(getActivity(), "Lên lịch thành công!", Toast.LENGTH_SHORT).show();

                                            FragmentManager fragmentManager = getFragmentManager();
                                            fragmentManager.beginTransaction().replace(R.id.content_frame, new ThoiGianBieu_Fragment())
                                                    .addToBackStack(null)
                                                    .commit();
                                        }
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
                                if (kll_ngay.getText().toString().equals("Ngày")) {
                                    Toast.makeText(getActivity(), "Vui lòng chọn ngày!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (kll_gio_bd.getText().toString().equals("Giờ bắt đầu")) {
                                        Toast.makeText(getActivity(), "Vui lòng chọn giờ bắt đầu!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (kll_gio_kt.getText().toString().equals("Dự kiến kết thúc")) {
                                            Toast.makeText(getActivity(), "Vui lòng chọn giờ dự kiến kết thúc!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (TimeValidator(kll_gio_bd.getText().toString(), kll_gio_kt.getText().toString()) == false) {
                                                Toast.makeText(getActivity(), "Vui lòng chọn giờ dự kiến kết thúc LỚN HƠN giờ bắt đầu!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                CongViec congViec = new CongViec();
                                                congViec.setTitle(title.getText().toString());
                                                congViec.setAddress(address.getText().toString());
                                                congViec.setDate(kll_ngay.getText().toString());
                                                congViec.setTime_start(kll_gio_bd.getText().toString());
                                                congViec.setTime_end(kll_gio_kt.getText().toString());
                                                congViec.setNote(description.getText().toString());
                                                for (int i = 0; i < 30; i++) {
                                                    SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
                                                    try {
                                                        Date myDate = dateParser.parse(strdate);
                                                        Calendar c = Calendar.getInstance();
                                                        c.setTime(myDate);
                                                        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + i);
                                                        Date newDate = c.getTime();
                                                        String newFormattedDate = dateParser.format(newDate);
                                                        congViec.setDate(newFormattedDate);
                                                    } catch (ParseException e) {
                                                        e.printStackTrace();
                                                        //handle exception
                                                    }
                                                    db.insert_table_congviec_laplai(congViec);

                                                    Calendar calendar = Calendar.getInstance();
                                                    calendar.set(Calendar.YEAR, yearf);
                                                    calendar.set(Calendar.MONTH, monthf - 1);
                                                    calendar.set(Calendar.DAY_OF_MONTH, dayf + i);
                                                    calendar.set(Calendar.HOUR_OF_DAY, hourf);
                                                    calendar.set(Calendar.MINUTE, minutef);
                                                    calendar.set(Calendar.SECOND, 00);
                                                    Log.d("TEst Alarm", "Chay ngon cmnr");
                                                    Log.d("TEst Alarm", "" + calendar.getTime());
                                                    Log.d("TEst Alarm", "" + calendar.getTimeInMillis());
                                                    Intent myIntent = new Intent(getActivity(), AlarmNotificationReceiver.class);
                                                    pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, myIntent, 0);

                                                    AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                                                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() - 150000, pendingIntent);
                                                }
                                                Toast.makeText(getActivity(), "Lặp lịch hàng ngày thành công!", Toast.LENGTH_SHORT).show();
                                                FragmentManager fragmentManager = getFragmentManager();
                                                fragmentManager.beginTransaction().replace(R.id.content_frame, new ThoiGianBieu_Fragment())
                                                        .addToBackStack(null)
                                                        .commit();
                                            }
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
                                    if (kll_ngay.getText().toString().equals("Ngày")) {
                                        Toast.makeText(getActivity(), "Vui lòng chọn ngày!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (kll_gio_bd.getText().toString().equals("Giờ bắt đầu")) {
                                            Toast.makeText(getActivity(), "Vui lòng chọn giờ bắt đầu!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (kll_gio_kt.getText().toString().equals("Dự kiến kết thúc")) {
                                                Toast.makeText(getActivity(), "Vui lòng chọn giờ dự kiến kết thúc!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                if (TimeValidator(kll_gio_bd.getText().toString(), kll_gio_kt.getText().toString()) == false) {
                                                    Toast.makeText(getActivity(), "Vui lòng chọn giờ dự kiến kết thúc LỚN HƠN giờ bắt đầu!", Toast.LENGTH_SHORT).show();
                                                } else {

                                                    CongViec congViec = new CongViec();
                                                    congViec.setTitle(title.getText().toString());
                                                    congViec.setAddress(address.getText().toString());
                                                    congViec.setDate(kll_ngay.getText().toString());
                                                    congViec.setTime_start(kll_gio_bd.getText().toString());
                                                    congViec.setTime_end(kll_gio_kt.getText().toString());
                                                    congViec.setNote(description.getText().toString());

                                                    for (int i = 0; i < 24; i++) {
                                                        SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
                                                        try {
                                                            Date myDate = dateParser.parse(strdate);
                                                            Calendar c = Calendar.getInstance();
                                                            c.setTime(myDate);
                                                            c.add(Calendar.DAY_OF_YEAR, (i * 7));
                                                            Date newDate = c.getTime();
                                                            String newFormattedDate = dateParser.format(newDate);
                                                            congViec.setDate(newFormattedDate);

                                                        } catch (ParseException e) {
                                                            e.printStackTrace();
                                                            //handle exception
                                                        }
                                                        db.insert_table_congviec_laplai(congViec);
                                                        Calendar calendar = Calendar.getInstance();
                                                        calendar.set(Calendar.YEAR, yearf);
                                                        calendar.set(Calendar.MONTH, monthf - 1);
                                                        calendar.set(Calendar.DAY_OF_MONTH, dayf + (i*7));
                                                        calendar.set(Calendar.HOUR_OF_DAY, hourf);
                                                        calendar.set(Calendar.MINUTE, minutef);
                                                        calendar.set(Calendar.SECOND, 00);
                                                        Log.d("TEst Alarm", "Chay ngon cmnr");
                                                        Log.d("TEst Alarm", "" + calendar.getTime());
                                                        Log.d("TEst Alarm", "" + calendar.getTimeInMillis());
                                                        Intent myIntent = new Intent(getActivity(), AlarmNotificationReceiver.class);
                                                        pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, myIntent, 0);

                                                        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                                                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() - 150000, pendingIntent);
                                                    }
                                                    Toast.makeText(getActivity(), "Lặp lịch hàng tuần thành công!", Toast.LENGTH_SHORT).show();
                                                    FragmentManager fragmentManager = getFragmentManager();
                                                    fragmentManager.beginTransaction().replace(R.id.content_frame, new ThoiGianBieu_Fragment())
                                                            .addToBackStack(null)
                                                            .commit();
                                                }
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
                                        if (kll_ngay.getText().toString().equals("Ngày")) {
                                            Toast.makeText(getActivity(), "Vui lòng chọn ngày!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (kll_gio_bd.getText().toString().equals("Giờ bắt đầu")) {
                                                Toast.makeText(getActivity(), "Vui lòng chọn giờ bắt đầu!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                if (kll_gio_kt.getText().toString().equals("Dự kiến kết thúc")) {
                                                    Toast.makeText(getActivity(), "Vui lòng chọn giờ dự kiến kết thúc!", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    if (TimeValidator(kll_gio_bd.getText().toString(), kll_gio_kt.getText().toString()) == false) {
                                                        Toast.makeText(getActivity(), "Vui lòng chọn giờ dự kiến kết thúc LỚN HƠN giờ bắt đầu!", Toast.LENGTH_SHORT).show();
                                                    } else {

                                                        CongViec congViec = new CongViec();
                                                        congViec.setTitle(title.getText().toString());
                                                        congViec.setAddress(address.getText().toString());
                                                        congViec.setDate(kll_ngay.getText().toString());
                                                        congViec.setTime_start(kll_gio_bd.getText().toString());
                                                        congViec.setTime_end(kll_gio_kt.getText().toString());
                                                        congViec.setNote(description.getText().toString());

                                                        for (int i = 0; i < 6; i++) {
                                                            SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
                                                            try {
                                                                Date myDate = dateParser.parse(strdate);
                                                                Calendar c = Calendar.getInstance();
                                                                c.setTime(myDate);
                                                                c.add(Calendar.MONTH, i);
                                                                Date newDate = c.getTime();
                                                                String newFormattedDate = dateParser.format(newDate);
                                                                congViec.setDate(newFormattedDate);
                                                            } catch (ParseException e) {
                                                                e.printStackTrace();
                                                                //handle exception
                                                            }
                                                            db.insert_table_congviec_laplai(congViec);
                                                            Calendar calendar = Calendar.getInstance();
                                                            calendar.set(Calendar.YEAR, yearf);
                                                            calendar.set(Calendar.MONTH, monthf - 1 + i);
                                                            calendar.set(Calendar.DAY_OF_MONTH, dayf);
                                                            calendar.set(Calendar.HOUR_OF_DAY, hourf);
                                                            calendar.set(Calendar.MINUTE, minutef);
                                                            calendar.set(Calendar.SECOND, 00);
                                                            Log.d("TEst Alarm", "Chay ngon cmnr");
                                                            Log.d("TEst Alarm", "" + calendar.getTime());
                                                            Log.d("TEst Alarm", "" + calendar.getTimeInMillis());
                                                            Intent myIntent = new Intent(getActivity(), AlarmNotificationReceiver.class);
                                                            pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, myIntent, 0);

                                                            AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                                                            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() - 150000, pendingIntent);
                                                        }
                                                        Toast.makeText(getActivity(), "Lặp lịch hàng tháng thành công!", Toast.LENGTH_SHORT).show();
                                                        FragmentManager fragmentManager = getFragmentManager();
                                                        fragmentManager.beginTransaction().replace(R.id.content_frame, new ThoiGianBieu_Fragment())
                                                                .addToBackStack(null)
                                                                .commit();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (title.getText().toString().equals("")) {
                                    Toast.makeText(getActivity(), "Vui lòng nhập tên công việc!", Toast.LENGTH_SHORT).show();
                                } else {
                                    if (address.getText().toString().equals("")) {
                                        Toast.makeText(getActivity(), "Vui lòng nhập địa điểm!", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if (kll_ngay.getText().toString().equals("Ngày")) {
                                            Toast.makeText(getActivity(), "Vui lòng chọn ngày!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            if (kll_gio_bd.getText().toString().equals("Giờ bắt đầu")) {
                                                Toast.makeText(getActivity(), "Vui lòng chọn giờ bắt đầu!", Toast.LENGTH_SHORT).show();
                                            } else {
                                                if (kll_gio_kt.getText().toString().equals("Dự kiến kết thúc")) {
                                                    Toast.makeText(getActivity(), "Vui lòng chọn giờ dự kiến kết thúc!", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    if (TimeValidator(kll_gio_bd.getText().toString(), kll_gio_kt.getText().toString()) == false) {
                                                        Toast.makeText(getActivity(), "Vui lòng chọn giờ dự kiến kết thúc LỚN HƠN giờ bắt đầu!", Toast.LENGTH_SHORT).show();
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
                                }
                            }
                        }
                    }
                }
            }
        });
        return myView;
    }

    public boolean TimeValidator(String time1, String time2) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        boolean b = false;
        try {
            java.util.Date ArrTime = sdf.parse(time1);
            java.util.Date DepTime = sdf.parse(time2);
            // Function to check whether a time is after an another time
            b = DepTime.after(ArrTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return b;
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
