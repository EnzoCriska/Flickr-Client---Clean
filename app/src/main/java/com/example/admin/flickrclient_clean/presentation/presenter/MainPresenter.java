package com.example.admin.flickrclient_clean.presentation.presenter;

import android.content.Context;

import com.example.admin.flickrclient_clean.domain.model.Photo;
import com.example.admin.flickrclient_clean.presentation.UI.BaseView;
import com.example.admin.flickrclient_clean.presentation.presenter.Bases.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public interface MainPresenter extends BasePresenter {

    void getPhotoFromApi(String url);

    void clickNotify(Context context);

    void getLocation(Context context);

    interface View extends BaseView {
        void showData(ArrayList<Photo> photos);

        void receiveNotify(String message);

        void cancleNotify(String message);

        void showLocation(String lat, String lon);

        void cantGetLocation();
    }
}
