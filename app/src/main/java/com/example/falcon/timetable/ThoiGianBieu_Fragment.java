package com.example.falcon.timetable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Admin on 6/6/2017.
 */

public class ThoiGianBieu_Fragment extends Fragment {
    View myView;
    CompactCalendarView compactCalendar;
    TextView tv_month, tv_next, tv_previous;

    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM - yyyy", Locale.getDefault());
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_thoigianbieu, container, false);

        initFindViewById();
        initEvent();
        compactCalendar = (CompactCalendarView) myView.findViewById(R.id.compactcalendar_view);
        compactCalendar.setFirstDayOfWeek(2);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        String abc = dateFormatMonth.format(compactCalendar.getFirstDayOfCurrentMonth()); // hiển thị tháng - năm bao nhiêu
        tv_month.setText(abc);

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendar.getEvents(dateClicked);

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                String abc = dateFormatMonth.format(firstDayOfNewMonth);
                tv_month.setText(abc);
                // Toast.makeText(getActivity(), "Month was scrolled to: " + abc, Toast.LENGTH_SHORT).show();

            }
        });


        return myView;
    }

    private void initFindViewById() {
        tv_next = (TextView) myView.findViewById(R.id.tv_tkb_next);
        tv_previous = (TextView) myView.findViewById(R.id.tv_tkb_previous);
        tv_month = (TextView) myView.findViewById(R.id.tv_tkb_month);
        tv_previous.setText("<");
        tv_next.setText(">");
    }
    private void initEvent() {
        tv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendar.showPreviousMonth();
                String abc = dateFormatMonth.format(compactCalendar.getFirstDayOfCurrentMonth()); // hiển thị tháng bao nhiêu
                tv_month.setText(abc);
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compactCalendar.showNextMonth();
                String abc = dateFormatMonth.format(compactCalendar.getFirstDayOfCurrentMonth()); // hiển thị tháng bao nhiêu
                tv_month.setText(abc);
            }
        });

    }
}
