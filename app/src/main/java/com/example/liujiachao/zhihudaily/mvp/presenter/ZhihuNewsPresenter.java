package com.example.liujiachao.zhihudaily.mvp.presenter;

import com.example.liujiachao.zhihudaily.utils.API;
import com.example.liujiachao.zhihudaily.OnLoadDataListener;
import com.example.liujiachao.zhihudaily.mvp.model.ZhihuNewsModel;
import com.example.liujiachao.zhihudaily.mvp.view.ZhihuNewsView;

/**
 * Created by liujiachao on 2016/7/28.
 */
public class ZhihuNewsPresenter implements OnLoadDataListener {

    private ZhihuNewsView mNewsView;
    private ZhihuNewsModel mNewsDetail;




    public  ZhihuNewsPresenter(ZhihuNewsView mNewsView){
        this.mNewsView = mNewsView;
        this.mNewsDetail = new ZhihuNewsModel();


    }
   public void loadLatest() {
        mNewsView.showProgress();
        mNewsDetail.getZhihuNews(API.TYPE_LATEST,this);

    };
    public void loadBefore() {
        mNewsDetail.getZhihuNews(API.TYPE_BEFORE,this);

    };

    @Override
    public void onSuccess() {
        mNewsView.addZhihuNews(null);
        mNewsView.hideProgress();

    }

    @Override
    public void onFailure(String msg) {

        mNewsView.hideProgress();
        mNewsView.loadFailed("load zhihu news failed");
    }
}
