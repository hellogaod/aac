

package androidx.lifecycle;

import androidx.annotation.NonNull;

class SingleGeneratedAdapterObserver implements LifecycleEventObserver {

    private final GeneratedAdapter mGeneratedAdapter;

    SingleGeneratedAdapterObserver(GeneratedAdapter generatedAdapter) {
        mGeneratedAdapter = generatedAdapter;
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        mGeneratedAdapter.callMethods(source, event, false, null);
        mGeneratedAdapter.callMethods(source, event, true, null);
    }
}
