package com.example.admin.flickrclient_clean.domain.interactors;

import com.example.admin.flickrclient_clean.domain.interactors.base.Interactor;

public interface GetLocationInteractor extends Interactor {
    interface CallBack{
        void onGetLocactionSuccess(String lat, String lon);
        void onGetLocationFail();
    }
}
