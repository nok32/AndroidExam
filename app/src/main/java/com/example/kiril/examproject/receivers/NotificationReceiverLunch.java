package com.example.kiril.examproject.receivers;

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
 * Created by Kiril on 13/10/2016.
 */

public class NotificationReceiverLunch extends NoteficationReceiverBase {
    private static final String LUNCH = "lunch";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, FoodsActivity.class);

        Category c = new Category();
        c.set_name(LUNCH);
        i.putExtra("Category", c);
        i.setFlags(FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        createNotification(context, pIntent, LUNCH);
    }

}
