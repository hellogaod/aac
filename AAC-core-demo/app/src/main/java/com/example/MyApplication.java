package com.example;

import android.app.Application;

import com.example.android.persistence.BuildConfig;

import timber.log.Timber;

/**
 * Copyright (C), 2019-2022, 佛生
 * FileName: Application
 * Author: 佛学徒
 * Date: 2022/6/6 9:09
 * Description:
 * History:自定义application
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
