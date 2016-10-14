package com.example.kiril.examproject.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.example.kiril.examproject.FoodsActivity;
import com.example.kiril.examproject.models.Category;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Kiril on 12/10/2016.
 */

public class NotificationReceiverBreakfast extends NoteficationReceiverBase {
    private static final String BREAKFAST = "breakfast";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, FoodsActivity.class);

        Category c = new Category();
        c.set_name(BREAKFAST);
        i.putExtra("Category", c);
        i.setFlags(FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        createNotification(context, pIntent, BREAKFAST);
        //context.startActivity(i);
    }
}