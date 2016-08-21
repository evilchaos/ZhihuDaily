package com.example.liujiachao.zhihudaily.mvp.view;

import com.example.liujiachao.zhihudaily.ZhihuDetail;

/**
 * Created by liujiachao on 2016/8/19.
 */
public interface NewsDetailView {
    void showDetails(ZhihuDetail zhihuDetail);
    void showLoadFailed(String msg);
}