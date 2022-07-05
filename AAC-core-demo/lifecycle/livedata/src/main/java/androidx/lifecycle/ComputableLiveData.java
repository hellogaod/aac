package androidx.lifecycle;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.arch.core.executor.ArchTaskExecutor;


/**
 * A LiveData class that can be invalidated & computed when there are active observers.
 * <p>
 * It can be invalidated via {@link #invalidate()}, which will result in a call to
 * {@link #compute()} if there are active observers (or when they start observing)
 * <p>
 * This is an internal class for now, might be public if we see the necessity.
 *
 * @param <T> The type of the live data
 * @hide internal
 */
public abstract class ComputableLiveData<T> {
    @SuppressWarnings("WeakerAccess") /* synthetic access */
    final Executor mExecutor;
    @SuppressWarnings("WeakerAccess") /* synthetic access */
    final LiveData<T> mLiveData;
    @SuppressWarnings("WeakerAccess") /* synthetic access */
    final AtomicBoolean mInvalid = new AtomicBoolean(true);
    @SuppressWarnings("WeakerAccess") /* synthetic access */
    final AtomicBoolean mComputing = new AtomicBoolean(false);

    /**
     * Creates a computable live data that computes values on the arch IO thread executor.
     */
    @SuppressWarnings("WeakerAccess")
    public ComputableLiveData() {
        this(ArchTaskExecutor.getIOThreadExecutor());
    }

    /**
     * Creates a computable live data that computes values on the specified executor.
     *
     * @param executor Executor that is used to compute new LiveData values.
     */
    @SuppressWarnings("WeakerAccess")
    public ComputableLiveData(@NonNull Executor executor) {
        mExecutor = executor;
        mLiveData = new LiveData<T>() {
            @Override
            protected void onActive() {
                mExecutor.execute(mRefreshRunnable);
            }
        };
    }

    /**
     * Returns the LiveData managed by this class.
     *
     * @return A LiveData that is controlled by ComputableLiveData.
     */
    @SuppressWarnings("WeakerAccess")
    @NonNull
    public LiveData<T> getLiveData() {
        return mLiveData;
    }

    @VisibleForTesting
    final Runnable mRefreshRunnable = new Runnable() {
        @WorkerThread
        @Override
        public void run() {
            boolean computed;
            do {
                computed = false;
                // compute can happen only in 1 thread but no reason to lock others.
                if (mComputing.compareAndSet(false, true)) {
                    // as long as it is invalid, keep computing.
                    try {
                        T value = null;
                        while (mInvalid.compareAndSet(true, false)) {
                            computed = true;
                            value = compute();
                        }
                        if (computed) {
                            mLiveData.postValue(value);
                        }
                    } finally {
                        // release compute lock
                        mComputing.set(false);
                    }
                }

                // check invalid after releasing compute lock to avoid the following scenario.
                // Thread A runs compute()
                // Thread A checks invalid, it is false
                // Main thread sets invalid to true
                // Thread B runs, fails to acquire compute lock and skips
                // Thread A releases compute lock
                // We've left invalid in set state. The check below recovers.
            } while (computed && mInvalid.get());
        }
    };

    // invalidation check always happens on the main thread
    @VisibleForTesting
    final Runnable mInvalidationRunnable = new Runnable() {
        @MainThread
        @Override
        public void run() {
            boolean isActive = mLiveData.hasActiveObservers();
            if (mInvalid.compareAndSet(false, true)) {
                if (isActive) {
                    mExecutor.execute(mRefreshRunnable);
                }
            }
        }
    };

    /**
     * Invalidates the LiveData.
     * <p>
     * When there are active observers, this will trigger a call to {@link #compute()}.
     */
    public void invalidate() {
        ArchTaskExecutor.getInstance().executeOnMainThread(mInvalidationRunnable);
    }

    // TODO https://issuetracker.google.com/issues/112197238
    @SuppressWarnings({"WeakerAccess", "UnknownNullness"})
    @WorkerThread
    protected abstract T compute();
}
