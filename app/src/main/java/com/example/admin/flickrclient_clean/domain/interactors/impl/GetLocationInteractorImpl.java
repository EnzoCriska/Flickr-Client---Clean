package com.example.admin.flickrclient_clean.domain.interactors.impl;

import com.example.admin.flickrclient_clean.domain.executor.Executor;
import com.example.admin.flickrclient_clean.domain.executor.MainThread;
import com.example.admin.flickrclient_clean.domain.interactors.GetLocationInteractor;
import com.example.admin.flickrclient_clean.domain.interactors.base.AbstractInteractor;
import com.example.admin.flickrclient_clean.domain.repository.LocationRepository;

public class GetLocationInteractorImpl extends AbstractInteractor implements GetLocationInteractor {
    private LocationRepository locationRepository;
    private GetLocationInteractor.CallBack callBack;

    public GetLocationInteractorImpl(Executor threadExecutor, MainThread mainThread, LocationRepository locationRepository, GetLocationInteractor.CallBack callBack) {
        super(threadExecutor, mainThread);
        this.locationRepository = locationRepository;
        this.callBack = callBack;
    }

    @Override
    public void run() {
        final String lat = locationRepository.getLat();
        final String lon = locationRepository.getLon();
        if (lat != null && lon != null) {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onGetLocactionSuccess(lat, lon);
                }
            });
        } else {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callBack.onGetLocationFail();
                }
            });
        }
    }
}
