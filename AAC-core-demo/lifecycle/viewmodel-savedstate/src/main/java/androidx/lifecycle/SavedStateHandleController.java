package androidx.lifecycle;

import androidx.annotation.NonNull;
import androidx.savedstate.SavedStateRegistry;

final class SavedStateHandleController implements LifecycleEventObserver {
    private final String mKey;
    private boolean mIsAttached = false;
    private final SavedStateHandle mHandle;

    SavedStateHandleController(String key, SavedStateHandle handle) {
        mKey = key;
        mHandle = handle;
    }

    boolean isAttached() {
        return mIsAttached;
    }

    void attachToLifecycle(SavedStateRegistry registry, Lifecycle lifecycle) {
        if (mIsAttached) {
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }
        mIsAttached = true;
        lifecycle.addObserver(this);
        registry.registerSavedStateProvider(mKey, mHandle.savedStateProvider());
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            mIsAttached = false;
            source.getLifecycle().removeObserver(this);
        }
    }

    SavedStateHandle getHandle() {
        return mHandle;
    }
}
