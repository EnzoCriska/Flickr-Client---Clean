package com.example.admin.flickrclient_clean.domain.interactors;

import com.example.admin.flickrclient_clean.domain.interactors.base.Interactor;

public interface SetNotifyInteractor extends Interactor {

    interface Callback{
        void onReceiverNotify(String notify);
        void onCancleNotify(String notify);
    }
}
