package com.example;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

/**
 * Copyright (C), 2019-2022, 佛生
 * FileName: MyApplication
 * Author: 佛学徒
 * Date: 2022/8/5 9:10
 * Description:
 * History:
 */
public class MyApplication
        extends MultiDexApplication {
//        extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
