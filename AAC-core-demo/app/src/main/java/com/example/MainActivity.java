package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.persistence.R;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import timber.log.Timber;

/**
 * Copyright (C), 2019-2022, 佛生
 * FileName: MainActivity
 * Author: 佛学徒
 * Date: 2022/6/2 11:11
 * Description:
 * History:
 */
public class MainActivity extends Activity {

    private TextView tvShow;
    private LiveData<String> liveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        tvShow = findViewById(R.id.tv_show);

        liveData = new LiveData<>();
        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChange(String s) {
                tvShow.setText(s != null ? s : "数据错误！");
            }
        });

        liveData.setValue("我是小可爱！");

        findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liveData.setValue("一板脚踢死你个萌币！");
            }
        });

        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChange(String s) {
                Timber.i(s + "可爱你个头！！！");
            }
        });

        Timber.e("error world！");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
