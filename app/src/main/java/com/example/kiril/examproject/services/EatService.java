package com.example.kiril.examproject.services;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.example.kiril.examproject.receivers.NotificationReceiverBreakfast;
import com.example.kiril.examproject.receivers.NotificationReceiverLunch;
import com.example.kiril.examproject.receivers.NotificationReceiverSupper;

import java.util.TimeZone;

/**
 * Created by Kiril on 12/10/2016.
 */

public class EatService extends Service {
    private static final String BREAKFAST = "breakfast";
    private static final String LUNCH = "lunch";
    private static final String SUPPER = "supper";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO: 13/10/2016 clean code from comentars
        Toast.makeText(this, "Lunch time notification is on", Toast.LENGTH_SHORT).show();

        Intent iBreakfast = new Intent(getApplicationContext(), NotificationReceiverBreakfast.class);
        iBreakfast.setAction("BREAKFAST");

        Intent iLunch = new Intent(getApplicationContext(), NotificationReceiverLunch.class);
        iLunch.setAction("LUNCH");

        Intent iSupper = new Intent(getApplicationContext(), NotificationReceiverSupper.class);
        iSupper.setAction("SUPPER");

        setLunchType(8, iBreakfast);
        setLunchType(12, iLunch);
        setLunchType(19, iSupper);

        return  START_NOT_STICKY;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setLunchType(int hour, Intent i) {
        i.setFlags(Intent.FLAG_RECEIVER_FOREGROUND);

        PendingIntent pIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, i, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Calendar firingCal= Calendar.getInstance();
        Calendar currentCal = Calendar.getInstance();

        long currentTimeTimezone = System.currentTimeMillis();
        int edtOffset = TimeZone.getTimeZone("EEST").getOffset(currentTimeTimezone);
        int gmtOffset = TimeZone.getTimeZone("GMT").getOffset(currentTimeTimezone);
        int hourDifference = (gmtOffset - edtOffset) / (1000 * 60 * 60);

        firingCal.set(Calendar.HOUR_OF_DAY, hour - hourDifference); // At the hour you wanna fire
        firingCal.set(Calendar.MINUTE, 0); // Particular minute
        firingCal.set(Calendar.SECOND, 0); // particular second

        long intendedTime = firingCal.getTimeInMillis();
        long currentTime = currentCal.getTimeInMillis();

        if(intendedTime >= currentTime){
            // you can add buffer time too here to ignore some small differences in milliseconds
            // set from today
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, intendedTime, AlarmManager.INTERVAL_DAY, pIntent);
        } else{
            // set from next day
            // you might consider using calendar.add() for adding one day to the current day
            firingCal.add(Calendar.DAY_OF_MONTH, 1);
            intendedTime = firingCal.getTimeInMillis();

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, intendedTime, AlarmManager.INTERVAL_DAY, pIntent);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Lunch time notification is off", Toast.LENGTH_LONG).show();
    }
}
