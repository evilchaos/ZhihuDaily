package com.example.liujiachao.zhihudaily;

import android.app.Application;
import android.content.Context;

/**
 * Created by liujiachao on 2016/8/16.
 */
public class ZhihuDailyApplication extends Application {
    //封装SharedPreferences接口时有用
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}