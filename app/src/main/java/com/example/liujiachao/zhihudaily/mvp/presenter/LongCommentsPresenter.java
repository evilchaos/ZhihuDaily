package com.example.liujiachao.zhihudaily.mvp.presenter;

import com.example.liujiachao.zhihudaily.entity.Comments;
import com.example.liujiachao.zhihudaily.listener.OnLoadLongCommentsListener;
import com.example.liujiachao.zhihudaily.mvp.model.ZhihuNewsModel;
import com.example.liujiachao.zhihudaily.mvp.view.LongCommentsView;

/**
 * Created by liujiachao on 2016/9/12.
 */
public class LongCommentsPresenter implements OnLoadLongCommentsListener {
    private LongCommentsView longCommentsView;
    private ZhihuNewsModel zhihuNewsModel;

    public LongCommentsPresenter(LongCommentsView longCommentsView) {
        this.longCommentsView = longCommentsView;
        zhihuNewsModel = new ZhihuNewsModel();
    }

    public void loadLongComments(int story_id) {
        zhihuNewsModel.getLongComments(story_id,this);
    }

    @Override
    public void onLoadLongCommentsSuccess(Comments response) {
        longCommentsView.showLongComments(response);
    }
}
