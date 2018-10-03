package com.example.admin.flickrclient_clean.domain.interactors.base;


import com.example.admin.flickrclient_clean.domain.executor.Executor;
import com.example.admin.flickrclient_clean.domain.executor.MainThread;


/**
 * created by: PhanPC.
 * Lớp abstract chứa code khung chung cho một interactor
 */
public abstract class AbstractInteractor implements Interactor {

    protected Executor mThreadExecutor;
    protected MainThread mMainThread;

    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;

    public AbstractInteractor(Executor threadExecutor, MainThread mainThread) {
        mThreadExecutor = threadExecutor;
        mMainThread = mainThread;
    }

    /**
     * Hàm này chứa logic nghiệp vụ chính của một interactor (use case). Hàm này sẽ được gọi trong executor.execute() đảm bảo
     * các code logic phức tạp - tốn thời gian xử lý sẽ được thực hiện ở background thread
     *
     * Chúng ta cũng có thể gọi hàm này khi thực hiện các unit test, selftest - đảm bảo tiêu chí testable độc lập của mô hình Clean
     */
    public abstract void run();

    public void cancel() {
        mIsCanceled = true;
        mIsRunning = false;
    }

    public boolean isRunning() {
        return mIsRunning;
    }

    public void onFinished() {
        mIsRunning = false;
        mIsCanceled = false;
    }

    public void execute() {

        // mark this interactor as running
        this.mIsRunning = true;

        // start running this interactor in a background thread
        mThreadExecutor.execute(this);
    }

}
