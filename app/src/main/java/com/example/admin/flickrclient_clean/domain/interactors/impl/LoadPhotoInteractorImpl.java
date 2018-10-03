package com.example.admin.flickrclient_clean.domain.interactors.impl;

import com.example.admin.flickrclient_clean.domain.executor.Executor;
import com.example.admin.flickrclient_clean.domain.executor.MainThread;
import com.example.admin.flickrclient_clean.domain.interactors.LoadPhotoInteractor;
import com.example.admin.flickrclient_clean.domain.interactors.base.AbstractInteractor;
import com.example.admin.flickrclient_clean.domain.model.Photo;
import com.example.admin.flickrclient_clean.domain.repository.DataRepository;

import java.util.ArrayList;

public class LoadPhotoInteractorImpl extends AbstractInteractor implements LoadPhotoInteractor {
    private LoadPhotoInteractor.CallBack callBack;
    private DataRepository dataRepository;

    public LoadPhotoInteractorImpl(Executor threadExecutor, MainThread mainThread, LoadPhotoInteractor.CallBack callBack, DataRepository dataRepository) {
        super(threadExecutor, mainThread);
        this.callBack = callBack;
        this.dataRepository = dataRepository;
    }

    @Override
    public void run() {
        final ArrayList<Photo> list = dataRepository.getData();
        if (!list.isEmpty()){
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onLoadsucces(list);
                }
            });
        }
    }
}
