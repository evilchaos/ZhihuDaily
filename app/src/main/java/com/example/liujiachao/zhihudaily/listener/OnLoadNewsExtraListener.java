package com.example.liujiachao.zhihudaily.listener;

import com.example.liujiachao.zhihudaily.entity.StoryExtra;

/**
 * Created by liujiachao on 2016/8/19.
 */
public interface OnLoadNewsExtraListener {
    void OnLoadNewsExtraSuccess(StoryExtra response);
    void OnLoadNewsExtraFailed(String msg);
}
