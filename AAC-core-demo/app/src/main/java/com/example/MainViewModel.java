package com.example;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

/**
 * Copyright (C), 2019-2022, 佛生
 * FileName: MainViewModel
 * Author: 佛学徒
 * Date: 2022/6/23 9:55
 * Description:
 * History:
 */
public class MainViewModel extends AndroidViewModel {

    MutableLiveData<String> liveData = new MutableLiveData<>();

    public MainViewModel(Application application, SavedStateHandle savedStateHandle) {
        super(application);
    }

    public void setValue(String s) {
        liveData.setValue(s);
    }

    public MutableLiveData<String> getLiveData() {
        return liveData;
    }

}
