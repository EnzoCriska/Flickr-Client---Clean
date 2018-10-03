package com.example.admin.flickrclient_clean.domain.interactors.impl;

import com.example.admin.flickrclient_clean.domain.executor.Executor;
import com.example.admin.flickrclient_clean.domain.executor.MainThread;
import com.example.admin.flickrclient_clean.domain.interactors.SetNotifyInteractor;
import com.example.admin.flickrclient_clean.domain.interactors.base.AbstractInteractor;
import com.example.admin.flickrclient_clean.domain.repository.NotifyRepository;

public class SetNotifyInteractorImpl extends AbstractInteractor implements SetNotifyInteractor {
    private SetNotifyInteractor.Callback callback;
    private NotifyRepository notifyRepository;

    public SetNotifyInteractorImpl(Executor threadExecutor, MainThread mainThread, SetNotifyInteractor.Callback callback, NotifyRepository notifyRepository) {
        super(threadExecutor, mainThread);
        this.callback = callback;
        this.notifyRepository = notifyRepository;
    }

    @Override
    public void run() {
        final String message = notifyRepository.getMessage();
        if (message.equals("receiver")){
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onReceiverNotify(message);
                }
            });
        }else{
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onCancleNotify(message);
                }
            });
        }
    }
}
