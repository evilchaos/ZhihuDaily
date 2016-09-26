package com.example.liujiachao.zhihudaily.listener;

import com.example.liujiachao.zhihudaily.entity.ZhihuJson;

/**
 * Created by evilchaos on 16/7/29.
 */
public interface OnLoadDataListener {
    void onSuccess(int type,ZhihuJson zhihuJson);
    void onFailure(String msg);
}
