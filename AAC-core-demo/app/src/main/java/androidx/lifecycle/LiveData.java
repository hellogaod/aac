package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * 一个数据源可以有多个观察者，但是只允许有一个生命周期拥有者实例
 *
 * @param <T>
 */
public class LiveData<T> {

    private T data;
    private List<ObserverWrapper<T>> observers = new ArrayList<>();
    private WeakReference<Activity> weakReference;

    private int START_VERSION = -1;

    private int mVersion = START_VERSION;

    //防止重复操作
    private boolean dispatchValue;

    public LiveData(T data) {
        checkValue(data, "data数据必须赋值！");
        this.data = data;
    }

    public LiveData() {
    }

    public void setValue(T t) {
        checkValue(t, "data数据必须赋值！");
        data = t;
        mVersion++;
        dispatch(Event.ON_CREATE);

    }

    //    @TargetApi(Build.VERSION_CODES.Q)
    public void observe(Activity activity, final Observer<T> observer) {
        checkValue(activity, "activity不允许为null");
        checkValue(observer, "observer观察者不允许为null");
        this.observers.add(new ObserverWrapper<>(observer));

        WeakReference wrActivity = new WeakReference<>(activity);

        if (weakReference != null) {
            //比较两个对象的应用地址判断是否同一个实例化对象：一个生命周期拥有者可以监听多个LiveData，一个LiveData可以被一个生命周期拥有者监听多次；但是多个生命周期拥有者不能监听同一个LiveData；
            if (!wrActivity.get().toString().equals(weakReference.get().toString())) {
                throw new IllegalArgumentException("一个生命周期拥有者可以监听多个LiveData，一个LiveData可以被一个生命周期拥有者监听多次；但是多个生命周期拥有者不能监听同一个LiveData；");
            }

            return;
        }

        this.weakReference = wrActivity;

        lifecycleCallback();

    }

    private void lifecycleCallback() {

        if (weakReference.get() != null) {
            //允许设备api >=29才可以使用当前方法
            if (Build.VERSION.SDK_INT >= 29) {
                weakReference.get().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                        dispatch(Event.ON_CREATE);
                    }

                    @Override
                    public void onActivityStarted(Activity activity) {
                        dispatch(Event.ON_START);
                    }

                    @Override
                    public void onActivityResumed(Activity activity) {
                        dispatch(Event.ON_RESUME);
                    }

                    @Override
                    public void onActivityPaused(Activity activity) {
                        dispatch(Event.ON_PAUSE);
                    }

                    @Override
                    public void onActivityStopped(Activity activity) {
                        dispatch(Event.ON_STOP);
                    }

                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                    }

                    @Override
                    public void onActivityDestroyed(Activity activity) {
                        dispatch(Event.ON_DESTROY);
                    }
                });
            } else {
                injectIfNeededIn(weakReference.get());
            }

        }
    }

    private void injectIfNeededIn(Activity activity) {
        // Prior to API 29 and to maintain compatibility with older versions of
        // ProcessLifecycleOwner (which may not be updated when lifecycle-runtime is updated and
        // need to support activities that don't extend from FragmentActivity from support lib),
        // use a framework fragment to get the correct timing of Lifecycle events
        android.app.FragmentManager manager = activity.getFragmentManager();

        ReportFragment reportFragment = new ReportFragment();
        reportFragment.setLiveData(this);
        if (manager.findFragmentByTag(ReportFragment.REPORT_FRAGMENT_TAG) == null) {
            manager.beginTransaction().add(reportFragment, ReportFragment.REPORT_FRAGMENT_TAG).commit();
            // Hopefully, we are the first to make a transaction.
            manager.executePendingTransactions();
        }
    }

    public void dispatch(Event event) {

        if (event.equals(Event.ON_DESTROY)) {
            clear();
            return;
        }

        //表示数据没有更新，则不需要做观察者回调
        if (mVersion == START_VERSION) {
            return;
        }

        if (dispatchValue) {
            return;
        }

        dispatchValue = true;
        for (ObserverWrapper<T> observerWrapper : observers) {
            if (mVersion > observerWrapper.mVersion) {
                observerWrapper.mVersion = mVersion;
                observerWrapper.observer.onChange(data);
            }

        }

        dispatchValue = false;
    }

    public T getValue() {

        checkValue(data, "data数据必须赋值！");
        return data;
    }

    public void clear() {
        checkValue(data, "data数据必须赋值！");

        observers.clear();

        if (data instanceof DataClear)
            ((DataClear) data).clearData();
        data = null;

        weakReference = null;
    }

    public void checkValue(Object obj, String msg) {
        if (obj == null) {
            if (msg == null)
                msg = obj.getClass() + "对象必须赋值！";
            throw new NullPointerException(msg);
        }
    }

    private class ObserverWrapper<T> {
        public Observer<T> observer;
        public int mVersion = START_VERSION;

        public ObserverWrapper(Observer observer) {
            this.observer = observer;
        }
    }
}
