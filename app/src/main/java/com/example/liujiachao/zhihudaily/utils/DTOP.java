package com.example.liujiachao.zhihudaily.utils;

import android.content.Context;
import android.util.TypedValue;

import com.example.liujiachao.zhihudaily.application.ZhihuDailyApplication;

/**
 * Created by liujiachao on 2016/9/22.
 */
public class DTOP {

    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }
}
