package com.example.liujiachao.zhihudaily.mvp.presenter;

import com.example.liujiachao.zhihudaily.entity.Comments;
import com.example.liujiachao.zhihudaily.listener.OnLoadShortCommentsListener;
import com.example.liujiachao.zhihudaily.mvp.model.ZhihuNewsModel;
import com.example.liujiachao.zhihudaily.mvp.view.ShortCommentsView;

/**
 * Created by liujiachao on 2016/9/12.
 */
public class ShortCommentsPresenter implements OnLoadShortCommentsListener {
    ShortCommentsView shortCommentsView;
    ZhihuNewsModel zhihuNewsModel;

    public ShortCommentsPresenter(ShortCommentsView shortCommentsView) {
        this.shortCommentsView = shortCommentsView;
        zhihuNewsModel = new ZhihuNewsModel();
    }

    public void loadShortComments(int story_id) {
        zhihuNewsModel.getShortComments(story_id,this);
    }

    @Override
    public void onLoadShortCommentsSuccess(Comments response) {
        shortCommentsView.showShortComments(response);
    }
}
