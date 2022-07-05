package com.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.persistence.R;

import org.jetbrains.annotations.Nullable;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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

    private static String TAG = "MainActivity";

    private TextView tvShow;
    private Button btn;

    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        tvShow = findViewById(R.id.tv_show);
        btn = findViewById(R.id.btn_ok);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setValue("LiveData使用案例");
            }
        });

        model.getLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String s) {
                tvShow.setText(s.toString());
                Timber.d(TAG, "onChanged() called with: s = [" + s + "]");
            }
        });
        String mainHash = this.hashCode()+"";
        String modelHash = model.hashCode()+"";
        Timber.d(TAG, "mainActivity:" + mainHash);
        Timber.d(TAG, "mainActivity.viewmodel: " + modelHash);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
