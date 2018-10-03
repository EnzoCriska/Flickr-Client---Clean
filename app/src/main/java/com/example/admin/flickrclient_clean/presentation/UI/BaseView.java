package com.example.admin.flickrclient_clean.presentation.UI;

/**
 * created by: PhanPC
 * Interface này demo một giao diện View cho phép các presenter sẽ điều khiển hiển thị trên View thông qua các method này.
 * Tất cả các View (Activity, Fragment ...) trong app nên implement interface này.
 */
public interface BaseView {

    /**
     * This is a general method used for showing some kind of progress during a background task. For example, this
     * method should show a progress bar and/or disable buttons before some background work starts.
     */
    void showProgress();

    /**
     * This is a general method used for hiding progress information after a background task finishes.
     */
    void hideProgress();

    /**
     * This method is used for showing error messages on the UI.
     *
     * @param message The error message to be displayed.
     */
    void showError(String message);
}
