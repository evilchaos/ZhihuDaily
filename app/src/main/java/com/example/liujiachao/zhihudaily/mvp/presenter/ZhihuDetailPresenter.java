package com.example.liujiachao.zhihudaily.mvp.presenter;

import com.example.liujiachao.zhihudaily.OnLoadDetailListener;
import com.example.liujiachao.zhihudaily.ZhihuDetail;
import com.example.liujiachao.zhihudaily.ZhihuItemInfo;
import com.example.liujiachao.zhihudaily.mvp.model.ZhihuNewsModel;
import com.example.liujiachao.zhihudaily.mvp.view.NewsDetailView;

/**
 * Created by liujiachao on 2016/8/19.
 */
public class ZhihuDetailPresenter implements OnLoadDetailListener {
    private NewsDetailView newsDetailView;
    private ZhihuNewsModel zhihuNewsModel;

    public ZhihuDetailPresenter(NewsDetailView newsDetailView) {
        this.newsDetailView = newsDetailView;
        this.zhihuNewsModel = new ZhihuNewsModel();
    }

    public void loadNewsDetail(int news_id) {
        zhihuNewsModel.getZhihuNewsDetail(news_id,this);
    }

    @Override
    public void onGetDetailSuccess(ZhihuDetail zhihuDetail) {
        newsDetailView.showDetails(zhihuDetail);

    }

    @Override
    public void onLoadDetailFailed(String msg) {
        newsDetailView.showLoadFailed(msg);
    }
}
