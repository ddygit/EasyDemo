package com.example.administrator.easyshopdemo;

import android.app.Application;

import com.example.administrator.easyshopdemo.model.CachePreferences;

/**
 * Created by Administrator on 2016/11/21.
 */

public class EasyShopApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CachePreferences.init(getApplicationContext());
    }
}
