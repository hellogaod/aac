package androidx.arch.core.executor;


import java.util.concurrent.Executor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * A static class that serves as a central point to execute common tasks.
 * <p>
 *
 * @hide This API is not final.
 */
public class ArchTaskExecutor extends TaskExecutor {
    private static volatile ArchTaskExecutor sInstance;

    @NonNull
    private TaskExecutor mDelegate;

    @NonNull
    private TaskExecutor mDefaultTaskExecutor;

    @NonNull
    private static final Executor sMainThreadExecutor = new Executor() {
        @Override
        public void execute(Runnable command) {
            getInstance().postToMainThread(command);
        }
    };

    @NonNull
    private static final Executor sIOThreadExecutor = new Executor() {
        @Override
        public void execute(Runnable command) {
            getInstance().executeOnDiskIO(command);
        }
    };

    private ArchTaskExecutor() {
        mDefaultTaskExecutor = new DefaultTaskExecutor();
        mDelegate = mDefaultTaskExecutor;
    }

    /**
     * Sets a delegate to handle task execution requests.
     * <p>
     * If you have a common executor, you can set it as the delegate and App Toolkit components will
     * use your executors. You may also want to use this for your tests.
     * <p>
     * Calling this method with {@code null} sets it to the default TaskExecutor.
     *
     * @param taskExecutor The task executor to handle task requests.
     */
    public void setDelegate(@Nullable TaskExecutor taskExecutor) {
        mDelegate = taskExecutor == null ? mDefaultTaskExecutor : taskExecutor;
    }

    /**
     * Returns an instance of the task executor.
     *
     * @return The singleton ArchTaskExecutor.
     */
    @NonNull
    public static ArchTaskExecutor getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (ArchTaskExecutor.class) {
            if (sInstance == null) {
                sInstance = new ArchTaskExecutor();
            }
        }
        return sInstance;
    }

    @Override
    public void executeOnDiskIO(Runnable runnable) {
        mDelegate.executeOnDiskIO(runnable);
    }

    @Override
    public void postToMainThread(Runnable runnable) {
        mDelegate.postToMainThread(runnable);
    }

    @NonNull
    public static Executor getMainThreadExecutor() {
        return sMainThreadExecutor;
    }

    @NonNull
    public static Executor getIOThreadExecutor() {
        return sIOThreadExecutor;
    }

    @Override
    public boolean isMainThread() {
        return mDelegate.isMainThread();
    }
}
