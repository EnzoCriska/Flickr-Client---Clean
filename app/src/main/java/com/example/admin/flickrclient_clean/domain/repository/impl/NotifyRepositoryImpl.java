package com.example.admin.flickrclient_clean.domain.repository.impl;

import com.example.admin.flickrclient_clean.domain.repository.NotifyRepository;

public class NotifyRepositoryImpl implements NotifyRepository {
    private String mMessage;

    public static NotifyRepositoryImpl getInstance(String message){
        return new NotifyRepositoryImpl(message);
    }
    public NotifyRepositoryImpl(String mMessage) {
        this.mMessage = mMessage;
    }

    @Override
    public String getMessage() {
        return mMessage;
    }
}
