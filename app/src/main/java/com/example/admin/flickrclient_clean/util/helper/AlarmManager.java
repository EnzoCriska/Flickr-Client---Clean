package com.example.admin.flickrclient_clean.util.helper;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.admin.flickrclient_clean.pojo.broadcast.PostNotifyReceiver;


public class AlarmManager {
    private static final String TAG = "AlarmManager";

    public static void setAlarm(Context context){
        android.app.AlarmManager alarmMgr = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, PostNotifyReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        Log.i(TAG, "get Receiver");

        alarmMgr.setRepeating(android.app.AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000*60,
                1000 * 60, alarmIntent);
        PreferencesManager.savePre(context, "isNotify", true);
    }

    public static void cancleAlarm(Context context) {
        android.app.AlarmManager alarmManager = (android.app.AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent notifyIntent = new Intent(context, PostNotifyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (context, 0, notifyIntent, 0);

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);

            PreferencesManager.savePre(context, "isNotify", false);
            NotifyManager.cancleNotic(context);
        }
    }
}
