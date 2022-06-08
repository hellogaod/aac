package androidx.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import java.util.function.Function;

/**
 * Copyright (C), 2019-2022, 佛生
 * FileName: ReportFragment
 * Author: 佛学徒
 * Date: 2022/6/8 8:57
 * Description:使用一个Fragment来监听activity的声明周期
 * History:
 */
public class ReportFragment extends Fragment {

    public static final String REPORT_FRAGMENT_TAG = "androidx.lifecycle"
            + ".LifecycleDispatcher.report_fragment_tag";
    private LiveData liveData;

    public void setLiveData(LiveData liveData) {
        this.liveData = liveData;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dispatch(Event.ON_CREATE);
    }

    @Override
    public void onStart() {
        super.onStart();

        dispatch(Event.ON_START);
    }

    @Override
    public void onResume() {
        super.onResume();

        dispatch(Event.ON_RESUME);
    }

    @Override
    public void onPause() {
        super.onPause();
        dispatch(Event.ON_PAUSE);
    }

    @Override
    public void onStop() {
        super.onStop();
        dispatch(Event.ON_STOP);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dispatch(Event.ON_DESTROY);
        // just want to be sure that we won't leak reference to an activity

    }

    private void dispatch(Event event) {
        liveData.dispatch(event);
    }
}
