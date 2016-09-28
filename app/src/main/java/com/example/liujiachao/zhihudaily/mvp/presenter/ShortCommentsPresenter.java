package com.example.liujiachao.zhihudaily.mvp.presenter;

import com.example.liujiachao.zhihudaily.entity.Comments;
import com.example.liujiachao.zhihudaily.listener.OnLoadCommentsListener;
import com.example.liujiachao.zhihudaily.listener.OnLoadShortCommentsListener;
import com.example.liujiachao.zhihudaily.mvp.model.ZhihuNewsModel;
import com.example.liujiachao.zhihudaily.mvp.view.CommentsView;
import com.example.liujiachao.zhihudaily.mvp.view.ShortCommentsView;

/**
 * Created by liujiachao on 2016/9/12.
 */
public class ShortCommentsPresenter implements OnLoadCommentsListener {
    CommentsView commentsView;
    ZhihuNewsModel zhihuNewsModel;

    public ShortCommentsPresenter(CommentsView commentsView) {
        this.commentsView = commentsView;
        zhihuNewsModel = new ZhihuNewsModel();
    }

    public void loadShortComments(int story_id) {
        zhihuNewsModel.getShortComments(story_id,this);
    }

    @Override
    public void onLoadCommentsSuccess(Comments response,int comment_type) {
        commentsView.showComments(response,comment_type);
    }
}
