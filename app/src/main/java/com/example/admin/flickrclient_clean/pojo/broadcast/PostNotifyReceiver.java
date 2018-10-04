package com.example.admin.flickrclient_clean.pojo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.admin.flickrclient_clean.pojo.service.CreateNotifyService;


public class PostNotifyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, CreateNotifyService.class));
    }
}
