package com.example.liujiachao.zhihudaily;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.example.liujiachao.zhihudaily.ZhihuDailyApplication.context;

/**
 * Created by liujiachao on 2016/8/16.
 */
public class SPSave {

    private static Context context = ZhihuDailyApplication.context;
    private static SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
    private static SharedPreferences.Editor editor = sp.edit();

    public static void save(String key,String value) {
        editor.putString(key,value);
        editor.commit();
    }

    public static String get(String key,String defaultValue) {
       return sp.getString(key,defaultValue);
    }


}
