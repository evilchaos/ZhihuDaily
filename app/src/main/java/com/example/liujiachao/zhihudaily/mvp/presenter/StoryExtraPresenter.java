package com.example.liujiachao.zhihudaily.mvp.presenter;

import com.example.liujiachao.zhihudaily.OnLoadNewsExtraListener;
import com.example.liujiachao.zhihudaily.StoryExtra;
import com.example.liujiachao.zhihudaily.mvp.model.ZhihuNewsModel;
import com.example.liujiachao.zhihudaily.mvp.view.StoryExtraView;

/**
 * Created by evilchaos on 16/8/20.
 */
public class StoryExtraPresenter implements OnLoadNewsExtraListener
{
    private StoryExtraView storyExtraView;
    private ZhihuNewsModel zhihuNewsModel;

    public StoryExtraPresenter(StoryExtraView storyExtraView) {
        this.storyExtraView = storyExtraView;
        zhihuNewsModel = new ZhihuNewsModel();
    }


    public void loadStoryExtra(int id) {
        zhihuNewsModel.getZhihuNewsExtra(id,this);
    }

    @Override
    public void OnLoadNewsExtraSuccess(StoryExtra response) {
        storyExtraView.showExtraInfo(response);
    }

    @Override
    public void OnLoadNewsExtraFailed(String msg) {
        storyExtraView.showExtraFailed(msg);
    }
}
