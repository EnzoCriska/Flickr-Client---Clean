package com.example.admin.flickrclient_clean.domain.executor;


import com.example.admin.flickrclient_clean.domain.interactors.base.AbstractInteractor;


/**
 * Đây là lớp tiện ích cho phép thực hiện các interactors (use cases) ở các background thread (nếu cần thiết)
 * <p/>
 */
public interface Executor {

    /**
     * This method should call the interactor's run method and thus start the interactor. This should be called
     * on a background thread as interactors might do lengthy operations.
     *
     * @param interactor The interactor to run.
     */
    void execute(final AbstractInteractor interactor);
}
