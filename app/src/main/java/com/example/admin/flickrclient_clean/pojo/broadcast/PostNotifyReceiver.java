package com.example.admin.flickrclient_clean.pojo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class PostNotifyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, PostNotifyReceiver.class));
    }
}
