package com.example.admin.flickrclient_clean.util;

import android.content.Context;

import com.example.admin.flickrclient_clean.domain.executor.MainThread;
import com.example.admin.flickrclient_clean.domain.executor.impl.ThreadExecutor;
import com.example.admin.flickrclient_clean.domain.repository.LocationRepository;
import com.example.admin.flickrclient_clean.pojo.Location.GPSTracker;
import com.example.admin.flickrclient_clean.pojo.repository.LocationRepositoryImpl;
import com.example.admin.flickrclient_clean.presentation.presenter.MainPresenter;
import com.example.admin.flickrclient_clean.presentation.presenter.impl.MainPresenterImp;
import com.example.admin.flickrclient_clean.threading.MainThreadImpl;

public class Injection {
    private static Injection instance;
    public static Injection getInstance(){
        if (instance == null){
            instance = new Injection();
        }
        return instance;
    }

    public MainPresenter getMainPresenter(MainPresenter.View view){
        return new MainPresenterImp(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), view);
    }

    public LocationRepository getLocationRepository(Context context){
        return LocationRepositoryImpl.getInstance(GPSTracker.getInstance(context));
    }
}
