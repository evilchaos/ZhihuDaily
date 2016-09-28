package com.example.liujiachao.zhihudaily.mvp.presenter;

import com.example.liujiachao.zhihudaily.entity.Comments;
import com.example.liujiachao.zhihudaily.listener.OnLoadCommentsListener;
import com.example.liujiachao.zhihudaily.listener.OnLoadLongCommentsListener;
import com.example.liujiachao.zhihudaily.mvp.model.ZhihuNewsModel;
import com.example.liujiachao.zhihudaily.mvp.view.CommentsView;
import com.example.liujiachao.zhihudaily.mvp.view.LongCommentsView;

/**
 * Created by liujiachao on 2016/9/12.
 */
public class LongCommentsPresenter implements OnLoadCommentsListener {
    private CommentsView commentsView;
    private ZhihuNewsModel zhihuNewsModel;

    public LongCommentsPresenter(CommentsView commentsView) {
        this.commentsView = commentsView;
        zhihuNewsModel = new ZhihuNewsModel();
    }

    public void loadLongComments(int story_id) {
        zhihuNewsModel.getLongComments(story_id,this);
    }

    @Override
    public void onLoadCommentsSuccess(Comments response,int comment_type) {
        commentsView.showComments(response,comment_type);
    }
}
