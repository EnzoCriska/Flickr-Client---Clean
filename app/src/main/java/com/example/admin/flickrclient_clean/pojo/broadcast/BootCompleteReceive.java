package com.example.admin.flickrclient_clean.pojo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.admin.flickrclient_clean.util.helper.AlarmManager;
import com.example.admin.flickrclient_clean.util.helper.PreferencesManager;

public class BootCompleteReceive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            if (PreferencesManager.loadPre(context, "isNotify")) {
                AlarmManager.setAlarm(context);
            }
        }
    }
}
