package androidx.lifecycle;

import android.annotation.SuppressLint;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.FastSafeIterableMap;

import static androidx.lifecycle.Lifecycle.State.DESTROYED;
import static androidx.lifecycle.Lifecycle.State.INITIALIZED;


/**
 * An implementation of {@link Lifecycle} that can handle multiple observers.
 * <p>
 * It is used by Fragments and Support Library Activities. You can also directly use it if you have
 * a custom LifecycleOwner.
 */
public class LifecycleRegistry extends Lifecycle {

    /**
     * Custom list that keeps observers and can handle removals / additions during traversal.
     * <p>
     * Invariant: at any moment of time for observer1 & observer2:
     * if addition_order(observer1) < addition_order(observer2), then
     * state(observer1) >= state(observer2),
     */
    private FastSafeIterableMap<LifecycleObserver, ObserverWithState> mObserverMap =
            new FastSafeIterableMap<>();
    /**
     * Current state
     */
    private State mState;

    /**
     * The provider that owns this Lifecycle.
     * Only WeakReference on LifecycleOwner is kept, so if somebody leaks Lifecycle, they won't leak
     * the whole Fragment / Activity. However, to leak Lifecycle object isn't great idea neither,
     * because it keeps strong references on all other listeners, so you'll leak all of them as
     * well.
     */
    private final WeakReference<LifecycleOwner> mLifecycleOwner;

    //当前有多少个观察者
    private int mAddingObserverCounter = 0;

    //是否正在处理事件
    private boolean mHandlingEvent = false;
    //新的事件是否发生
    private boolean mNewEventOccurred = false;
    // we have to keep it for cases:
    // void onStart() {
    //     mRegistry.removeObserver(this);
    //     mRegistry.add(newObserver);
    // }
    // newObserver should be brought only to CREATED state during the execution of
    // this onStart method. our invariant with mObserverMap doesn't help, because parent observer
    // is no longer in the map.
    private ArrayList<State> mParentStates = new ArrayList<>();
    private final boolean mEnforceMainThread;

    /**
     * Creates a new LifecycleRegistry for the given provider.
     * <p>
     * You should usually create this inside your LifecycleOwner class's constructor and hold
     * onto the same instance.
     *
     * @param provider The owner LifecycleOwner
     */
    public LifecycleRegistry(@NonNull LifecycleOwner provider) {
        this(provider, true);
    }

    private LifecycleRegistry(@NonNull LifecycleOwner provider, boolean enforceMainThread) {
        mLifecycleOwner = new WeakReference<>(provider);
        mState = INITIALIZED;
        mEnforceMainThread = enforceMainThread;
    }

    /**
     * Moves the Lifecycle to the given state and dispatches necessary events to the observers.
     *
     * @param state new state
     * @deprecated Use {@link #setCurrentState(State)}.
     */
    @Deprecated
    @MainThread
    public void markState(@NonNull State state) {
        enforceMainThreadIfNeeded("markState");
        setCurrentState(state);
    }

    /**
     * Moves the Lifecycle to the given state and dispatches necessary events to the observers.
     *
     * @param state new state
     */
    @MainThread
    public void setCurrentState(@NonNull State state) {
        enforceMainThreadIfNeeded("setCurrentState");
        moveToState(state);
    }

    /**
     * Sets the current state and notifies the observers.
     * <p>
     * Note that if the {@code currentState} is the same state as the last call to this method,
     * calling this method has no effect.
     *
     * @param event The event that was received
     */
    public void handleLifecycleEvent(@NonNull Lifecycle.Event event) {
        enforceMainThreadIfNeeded("handleLifecycleEvent");
        moveToState(event.getTargetState());
    }

    private void moveToState(State next) {
        if (mState == next) {
            return;
        }

        if (mState == INITIALIZED && next == DESTROYED) {
            throw new IllegalStateException("no event down from " + mState);
        }

        mState = next;

        //如果事件正在处理中，并且存在观察者，那么表明有事件发生
        if (mHandlingEvent || mAddingObserverCounter != 0) {
            mNewEventOccurred = true;
            // we will figure out what to do on upper level.
            return;
        }
        mHandlingEvent = true;
        sync();
        mHandlingEvent = false;
        if (mState == DESTROYED) {
            mObserverMap = new FastSafeIterableMap<>();
        }
    }

    private boolean isSynced() {
        if (mObserverMap.size() == 0) {
            return true;
        }
        State eldestObserverState = mObserverMap.eldest().getValue().mState;
        State newestObserverState = mObserverMap.newest().getValue().mState;
        return eldestObserverState == newestObserverState && mState == newestObserverState;
    }

    private State calculateTargetState(LifecycleObserver observer) {
        Map.Entry<LifecycleObserver, ObserverWithState> previous = mObserverMap.ceil(observer);

        State siblingState = previous != null ? previous.getValue().mState : null;
        State parentState = !mParentStates.isEmpty() ? mParentStates.get(mParentStates.size() - 1)
                : null;
        return min(min(mState, siblingState), parentState);
    }

    @Override
    public void addObserver(@NonNull LifecycleObserver observer) {
        enforceMainThreadIfNeeded("addObserver");
        State initialState = mState == DESTROYED ? DESTROYED : INITIALIZED;
        ObserverWithState statefulObserver = new ObserverWithState(observer, initialState);
        ObserverWithState previous = mObserverMap.putIfAbsent(observer, statefulObserver);

        if (previous != null) {
            return;
        }

        LifecycleOwner lifecycleOwner = mLifecycleOwner.get();
        if (lifecycleOwner == null) {
            // it is null we should be destroyed. Fallback quickly
            return;
        }

        boolean isReentrance = mAddingObserverCounter != 0 || mHandlingEvent;
        State targetState = calculateTargetState(observer);
        mAddingObserverCounter++;
        while (statefulObserver.mState.compareTo(targetState) < 0
                && mObserverMap.contains(observer)) {
            pushParentState(statefulObserver.mState);
            final Event event = Event.upFrom(statefulObserver.mState);
            if (event == null) {
                throw new IllegalStateException("no event up from " + statefulObserver.mState);
            }
            statefulObserver.dispatchEvent(lifecycleOwner, event);
            popParentState();
            // mState / subling may have been changed recalculate
            targetState = calculateTargetState(observer);
        }

        if (!isReentrance) {
            // we do sync only on the top level.
            sync();
        }
        mAddingObserverCounter--;
    }

    private void popParentState() {
        mParentStates.remove(mParentStates.size() - 1);
    }

    private void pushParentState(State state) {
        mParentStates.add(state);
    }

    @Override
    public void removeObserver(@NonNull LifecycleObserver observer) {
        enforceMainThreadIfNeeded("removeObserver");
        // we consciously decided not to send destruction events here in opposition to addObserver.
        // Our reasons for that:
        // 1. These events haven't yet happened at all. In contrast to events in addObservers, that
        // actually occurred but earlier.
        // 2. There are cases when removeObserver happens as a consequence of some kind of fatal
        // event. If removeObserver method sends destruction events, then a clean up routine becomes
        // more cumbersome. More specific example of that is: your LifecycleObserver listens for
        // a web connection, in the usual routine in OnStop method you report to a server that a
        // session has just ended and you close the connection. Now let's assume now that you
        // lost an internet and as a result you removed this observer. If you get destruction
        // events in removeObserver, you should have a special case in your onStop method that
        // checks if your web connection died and you shouldn't try to report anything to a server.
        mObserverMap.remove(observer);
    }

    /**
     * The number of observers.
     *
     * @return The number of observers.
     */
    @SuppressWarnings("WeakerAccess")
    public int getObserverCount() {
        enforceMainThreadIfNeeded("getObserverCount");
        return mObserverMap.size();
    }

    @Override
    public State getCurrentState() {
        return mState;
    }

    private void forwardPass(LifecycleOwner lifecycleOwner) {
        Iterator<Map.Entry<LifecycleObserver, ObserverWithState>> ascendingIterator =
                mObserverMap.iteratorWithAdditions();

        while (ascendingIterator.hasNext() && !mNewEventOccurred) {
            Map.Entry<LifecycleObserver, ObserverWithState> entry = ascendingIterator.next();
            ObserverWithState observer = entry.getValue();
            while (observer.mState.compareTo(mState) < 0 && !mNewEventOccurred
                    && mObserverMap.contains(entry.getKey())) {
                pushParentState(observer.mState);
                final Event event = Event.upFrom(observer.mState);
                if (event == null) {
                    throw new IllegalStateException("no event up from " + observer.mState);
                }
                observer.dispatchEvent(lifecycleOwner, event);
                popParentState();
            }
        }
    }

    private void backwardPass(LifecycleOwner lifecycleOwner) {
        Iterator<Map.Entry<LifecycleObserver, ObserverWithState>> descendingIterator =
                mObserverMap.descendingIterator();

        while (descendingIterator.hasNext() && !mNewEventOccurred) {
            Map.Entry<LifecycleObserver, ObserverWithState> entry = descendingIterator.next();
            ObserverWithState observer = entry.getValue();
            while ((observer.mState.compareTo(mState) > 0 && !mNewEventOccurred
                    && mObserverMap.contains(entry.getKey()))) {
                Event event = Event.downFrom(observer.mState);
                if (event == null) {
                    throw new IllegalStateException("no event down from " + observer.mState);
                }
                pushParentState(event.getTargetState());
                observer.dispatchEvent(lifecycleOwner, event);
                popParentState();
            }
        }
    }

    // happens only on the top of stack (never in reentrance),
    // so it doesn't have to take in account parents
    private void sync() {
        LifecycleOwner lifecycleOwner = mLifecycleOwner.get();
        if (lifecycleOwner == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is already"
                    + "garbage collected. It is too late to change lifecycle state.");
        }
        while (!isSynced()) {
            mNewEventOccurred = false;
            // no need to check eldest for nullability, because isSynced does it for us.
            if (mState.compareTo(mObserverMap.eldest().getValue().mState) < 0) {
                backwardPass(lifecycleOwner);
            }
            Map.Entry<LifecycleObserver, ObserverWithState> newest = mObserverMap.newest();
            if (!mNewEventOccurred && newest != null
                    && mState.compareTo(newest.getValue().mState) > 0) {
                forwardPass(lifecycleOwner);
            }
        }
    }

    @SuppressLint("RestrictedApi")
    private void enforceMainThreadIfNeeded(String methodName) {
        if (mEnforceMainThread) {
            if (!ArchTaskExecutor.getInstance().isMainThread()) {
                throw new IllegalStateException("Method " + methodName + " must be called on the "
                        + "main thread");
            }
        }
    }

    /**
     * Creates a new LifecycleRegistry for the given provider, that doesn't check
     * that its methods are called on the threads other than main.
     * <p>
     * LifecycleRegistry is not synchronized: if multiple threads access this {@code
     * LifecycleRegistry}, it must be synchronized externally.
     * <p>
     * Another possible use-case for this method is JVM testing, when main thread is not present.
     */
    @VisibleForTesting
    @NonNull
    public static LifecycleRegistry createUnsafe(@NonNull LifecycleOwner owner) {
        return new LifecycleRegistry(owner, false);
    }

    static State min(@NonNull State state1, @NonNull State state2) {
        return state2 != null && state2.compareTo(state1) < 0 ? state2 : state1;
    }

    static class ObserverWithState {
        State mState;
        LifecycleEventObserver mLifecycleObserver;

        ObserverWithState(LifecycleObserver observer, State initialState) {
            mLifecycleObserver = Lifecycling.lifecycleEventObserver(observer);
            mState = initialState;
        }

        void dispatchEvent(LifecycleOwner owner, Event event) {
            State newState = event.getTargetState();
            mState = min(mState, newState);
            mLifecycleObserver.onStateChanged(owner, event);
            mState = newState;
        }
    }
}
