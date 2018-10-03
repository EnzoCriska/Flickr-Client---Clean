package com.example.admin.flickrclient_clean.presentation.presenter.Bases;

public interface BasePresenter {
    void resume();
    void stop();
    void pause();
    void destroy();
    void onError();
}
