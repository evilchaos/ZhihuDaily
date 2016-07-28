package com.example.liujiachao.zhihudaily.mvp.presenter;

import android.content.Context;

import com.example.liujiachao.zhihudaily.NewsView;
import com.example.liujiachao.zhihudaily.OnLoadDataListener;
import com.example.liujiachao.zhihudaily.ZhihuDetail;
import com.example.liujiachao.zhihudaily.ZhihuItemInfo;
import com.example.liujiachao.zhihudaily.ZhihuJson;

/**
 * Created by liujiachao on 2016/7/28.
 */
public class ZhihuNewsPresenter implements OnLoadDataListener {

    private NewsView<ZhihuJson> mNewsView;
    private NewsModel<ZhihuItemInfo,ZhihuDetail> mNewsDetail;

    public void ZhihuNewsPresenter(Context context,){

    }
   public void loadNews() {


    };
    public void loadBefore() {

    };

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(String msg) {

    }
}
