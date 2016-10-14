package com.example.kiril.examproject.receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.kiril.examproject.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Kiril on 13/10/2016.
 */

public class NoteficationReceiverBase extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void createNotification(Context con, PendingIntent pIntent, String lunchType) {
        Notification n  = new Notification.Builder(con)
                .setContentTitle(Character.toUpperCase(lunchType.charAt(0)) + lunchType.substring(1)+ " Time")
                .setContentText("Always make the best choice")
                .setSmallIcon(R.drawable.com_facebook_tooltip_blue_topnub)
                .setContentIntent(pIntent)
                .setAutoCancel(true).build();

        NotificationManager notificationManager =
                (NotificationManager) con.getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);
    }
}
