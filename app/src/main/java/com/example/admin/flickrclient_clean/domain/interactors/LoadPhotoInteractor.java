package com.example.admin.flickrclient_clean.domain.interactors;

import com.example.admin.flickrclient_clean.domain.interactors.base.Interactor;

import java.util.ArrayList;

public interface LoadPhotoInteractor extends Interactor {

    interface CallBack{
        void onLoadsucces(ArrayList list);
        void onLoadFail();
    }
}
