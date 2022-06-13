package com.example;

import android.os.Bundle;
import android.widget.TextView;

import com.example.android.persistence.R;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import timber.log.Timber;

/**
 * Copyright (C), 2019-2022, 佛生
 * FileName: MainActivity
 * Author: 佛学徒
 * Date: 2022/6/2 11:11
 * Description:
 * History:
 */
public class MainActivity extends ComponentActivity {

    private TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        tvShow = findViewById(R.id.tv_show);


        getLifecycle().addObserver(new DefaultLifecycleObserver() {
            @Override
            public void onCreate(LifecycleOwner owner) {
                Timber.d("onCreate");
            }

            @Override
            public void onStart(LifecycleOwner owner) {
                Timber.d("onStart");
            }

            @Override
            public void onResume(LifecycleOwner owner) {
                Timber.d("onResume");
            }

            @Override
            public void onPause(LifecycleOwner owner) {
                Timber.d("onPause");
            }

            @Override
            public void onStop(LifecycleOwner owner) {
                Timber.d("onStop");
            }

            @Override
            public void onDestroy(LifecycleOwner owner) {
                Timber.d("onDestroy");
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
