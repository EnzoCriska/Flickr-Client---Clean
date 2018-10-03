package com.example.admin.flickrclient_clean.domain.interactors.base;


/**
 * Interface chung cho tất cả các interactors (implemetation) - Mỗi interactor dùng để thực hiện một use case (của hệ thống)
 */
public interface Interactor {

    /**
     * This is the main method that starts an interactor. It will make sure that the interactor operation is done on a
     * background thread.
     */
    void execute();
}
