package com.example.liujiachao.zhihudaily.listener;

import com.example.liujiachao.zhihudaily.entity.ZhihuDetail;

/**
 * Created by evilchaos on 16/7/30.
 * 监听从网络上加载知乎文章成功与否
 */
public interface OnLoadDetailListener {
    void onGetDetailSuccess(ZhihuDetail zhihuDetail);
    void onLoadDetailFailed(String msg);


}
