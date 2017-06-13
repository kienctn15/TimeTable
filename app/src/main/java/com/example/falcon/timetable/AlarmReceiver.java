package com.example.falcon.timetable;

import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by Optimus on 06/13/17.
 */

public class AlarmReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //perform your task
        Intent service1 = new Intent(context, MyAlarmService.class);
        context.startService(service1);
    }
}