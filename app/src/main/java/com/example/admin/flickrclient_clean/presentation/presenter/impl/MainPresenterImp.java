package com.example.admin.flickrclient_clean.presentation.presenter.impl;


import android.content.Context;


import com.example.admin.flickrclient_clean.R;
import com.example.admin.flickrclient_clean.domain.executor.Executor;
import com.example.admin.flickrclient_clean.domain.executor.MainThread;
import com.example.admin.flickrclient_clean.domain.interactors.GetLocationInteractor;
import com.example.admin.flickrclient_clean.domain.interactors.LoadPhotoInteractor;
import com.example.admin.flickrclient_clean.domain.interactors.SetNotifyInteractor;
import com.example.admin.flickrclient_clean.domain.interactors.impl.GetLocationInteractorImpl;
import com.example.admin.flickrclient_clean.domain.interactors.impl.LoadPhotoInteractorImpl;
import com.example.admin.flickrclient_clean.domain.interactors.impl.SetNotifyInteractorImpl;
import com.example.admin.flickrclient_clean.domain.model.Photo;
import com.example.admin.flickrclient_clean.domain.repository.DataRepository;
import com.example.admin.flickrclient_clean.domain.repository.LocationRepository;
import com.example.admin.flickrclient_clean.domain.repository.NotifyRepository;
import com.example.admin.flickrclient_clean.domain.repository.impl.NotifyRepositoryImpl;
import com.example.admin.flickrclient_clean.pojo.repository.DataRepositoryImpl;
import com.example.admin.flickrclient_clean.presentation.presenter.Bases.AbstractPresenter;
import com.example.admin.flickrclient_clean.presentation.presenter.MainPresenter;
import com.example.admin.flickrclient_clean.util.Constant;
import com.example.admin.flickrclient_clean.util.Injection;
import com.example.admin.flickrclient_clean.util.helper.AlarmManager;
import com.example.admin.flickrclient_clean.util.helper.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

public class MainPresenterImp extends AbstractPresenter implements MainPresenter,
        LoadPhotoInteractor.CallBack, SetNotifyInteractor.Callback, GetLocationInteractor.CallBack {
    private MainPresenter.View mView;

    public MainPresenterImp(Executor executor, MainThread mainThread, MainPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError() {

    }


    @Override
    public void getPhotoFromApi(String url) {
        mView.showProgress();
        DataRepository mDataRepository = DataRepositoryImpl.getInstance(url);
        LoadPhotoInteractor interactor = new LoadPhotoInteractorImpl(
                mExecutor, mMainThread, this, mDataRepository);
        interactor.execute();
    }
    @Override
    public void clickNotify(Context context) {
        NotifyRepository notifyRepository;
        if (PreferencesManager.loadPre(context, "isNotify")) {
            AlarmManager.cancleAlarm(context);
            notifyRepository = NotifyRepositoryImpl.getInstance("cancle");
        } else {
            AlarmManager.setAlarm(context);
            notifyRepository = NotifyRepositoryImpl.getInstance("receiver");
        }
        SetNotifyInteractor interactor = new SetNotifyInteractorImpl(
                mExecutor, mMainThread, this, notifyRepository);
        interactor.execute();
    }

    @Override
    public void getLocation(Context context) {
        LocationRepository repository = Injection.getInstance().getLocationRepository(context);
        GetLocationInteractor interactor = new GetLocationInteractorImpl(mExecutor, mMainThread, repository, this);
        interactor.execute();
    }


    @Override
    public void onReceiverNotify(String notify) {
        mView.receiveNotify(notify);
    }

    @Override
    public void onCancleNotify(String message) {
        mView.cancleNotify(message);
    }

    @Override
    public void onGetLocactionSuccess(String lat, String lon) {
        mView.showLocation(lat, lon);
    }

    @Override
    public void onGetLocationFail() {
        mView.cantGetLocation();
    }

    @Override
    public void onLoadsucces(ArrayList list) {
        mView.hideProgress();
        mView.showData(list);
    }

    @Override
    public void onLoadFail() {
        mView.hideProgress();
        mView.showError("error");
    }
}
