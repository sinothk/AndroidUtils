package com.sinothk.android.utils.demo;

import android.app.Application;

import com.sinothk.android.utils.XUtils;

public class XUtilDemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        XUtils.init(getApplicationContext());
    }
}
