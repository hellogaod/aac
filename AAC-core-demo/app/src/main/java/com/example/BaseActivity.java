package com.example;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import androidx.lifecycle.DataClear;
import androidx.lifecycle.LiveData;

/**
 * Copyright (C), 2019-2022, 佛生
 * FileName: BaseActivity
 * Author: 佛学徒
 * Date: 2022/6/6 10:41
 * Description:
 * History:
 */
public abstract class BaseActivity<T> extends Activity {

    T liveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void setValue(T t) {
        this.liveData = t;
        updateUI(t);
    }

    protected abstract void updateUI(T t);

    @Override
    protected void onDestroy() {
        if (liveData != null) {

            if (liveData instanceof DataClear) {
                ((DataClear) liveData).clearData();
            }

            liveData = null;
        }

        super.onDestroy();
    }
}
