package com.example.liujiachao.zhihudaily.application;

import android.app.Application;
import android.content.Context;

import com.example.liujiachao.zhihudaily.utils.DB;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
        DB.realm = Realm.getDefaultInstance();
    }
}
