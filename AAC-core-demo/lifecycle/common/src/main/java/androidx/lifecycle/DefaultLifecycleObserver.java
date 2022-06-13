package androidx.lifecycle;

import androidx.annotation.NonNull;

/**
 * Callback interface for listening to {@link LifecycleOwner} state changes.
 * If a class implements both this interface and {@link LifecycleEventObserver}, then
 * methods of {@code DefaultLifecycleObserver} will be called first, and then followed by the call
 * of {@link LifecycleEventObserver#onStateChanged(LifecycleOwner, Lifecycle.Event)}
 * <p>
 * If a class implements this interface and in the same time uses {@link OnLifecycleEvent}, then
 * annotations will be ignored.
 */
@SuppressWarnings("unused")
public interface DefaultLifecycleObserver extends FullLifecycleObserver {

    /**
     * Notifies that {@code ON_CREATE} event occurred.
     * <p>
     * This method will be called after the {@link LifecycleOwner}'s {@code onCreate}
     * method returns.
     *
     * @param owner the component, whose state was changed
     */
    @Override
    default void onCreate(@NonNull LifecycleOwner owner) {
    }

    /**
     * Notifies that {@code ON_START} event occurred.
     * <p>
     * This method will be called after the {@link LifecycleOwner}'s {@code onStart} method returns.
     *
     * @param owner the component, whose state was changed
     */
    @Override
    default void onStart(@NonNull LifecycleOwner owner) {
    }

    /**
     * Notifies that {@code ON_RESUME} event occurred.
     * <p>
     * This method will be called after the {@link LifecycleOwner}'s {@code onResume}
     * method returns.
     *
     * @param owner the component, whose state was changed
     */
    @Override
    default void onResume(@NonNull LifecycleOwner owner) {
    }

    /**
     * Notifies that {@code ON_PAUSE} event occurred.
     * <p>
     * This method will be called before the {@link LifecycleOwner}'s {@code onPause} method
     * is called.
     *
     * @param owner the component, whose state was changed
     */
    @Override
    default void onPause(@NonNull LifecycleOwner owner) {
    }

    /**
     * Notifies that {@code ON_STOP} event occurred.
     * <p>
     * This method will be called before the {@link LifecycleOwner}'s {@code onStop} method
     * is called.
     *
     * @param owner the component, whose state was changed
     */
    @Override
    default void onStop(@NonNull LifecycleOwner owner) {
    }

    /**
     * Notifies that {@code ON_DESTROY} event occurred.
     * <p>
     * This method will be called before the {@link LifecycleOwner}'s {@code onDestroy} method
     * is called.
     *
     * @param owner the component, whose state was changed
     */
    @Override
    default void onDestroy(@NonNull LifecycleOwner owner) {
    }
}
