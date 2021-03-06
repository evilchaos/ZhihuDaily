package com.example.liujiachao.zhihudaily.mvp.view;

import com.example.liujiachao.zhihudaily.entity.ZhihuJson;

/**
 * Created by liujiachao on 2016/7/29.
 * mvp模式中的view，更新视图
 */
public interface ZhihuNewsView {
    void showProgress();
    void addZhihuNews(int type,ZhihuJson zhihuJson);
    void hideProgress();
    void loadFailed(String msg);
}
