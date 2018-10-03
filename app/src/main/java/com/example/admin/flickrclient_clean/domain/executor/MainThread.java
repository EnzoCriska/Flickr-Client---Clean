package com.example.admin.flickrclient_clean.domain.executor;

/**
 * Interface cho MainThreadImpl, mục đích cho phép các interactor có thể thực hiện các hành động cụ thể trên UI Thread. Ví dụ, sau khi thực hiện một
 * số tính toán/nghiệp vụ tại background thread, nếu một interactor muốn hiển thị một thông tin j đó lên UI, thì thông qua MainThread để hiển thị
 * thông tin lên UI.
 * <p/>
 */
public interface MainThread {

    /**
     * Make runnable operation run in the main thread.
     *
     * @param runnable The runnable to run.
     */
    void post(final Runnable runnable);
}
