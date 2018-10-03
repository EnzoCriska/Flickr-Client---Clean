package com.example.admin.flickrclient_clean.pojo.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.admin.flickrclient_clean.util.helper.NotifyManager;

public class CreateNotifyService extends IntentService {

    public CreateNotifyService() {
        super("CreateNotifyService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        NotifyManager.showNotic(this);
    }
}
