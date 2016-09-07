package com.example.liujiachao.zhihudaily.mvp.presenter;

import com.example.liujiachao.zhihudaily.listener.OnLoadThemeContentListener;
import com.example.liujiachao.zhihudaily.entity.ThemeContent;
import com.example.liujiachao.zhihudaily.mvp.model.ZhihuNewsModel;
import com.example.liujiachao.zhihudaily.mvp.view.ThemeContentView;

/**
 * Created by liujiachao on 2016/9/6.
 */
public class ThemeContentPresenter implements OnLoadThemeContentListener {
    private ThemeContentView themeContentView;
    private ZhihuNewsModel zhihuNewsModel;

    public ThemeContentPresenter(ThemeContentView themeContentView) {
        this.themeContentView = themeContentView;
        zhihuNewsModel = new ZhihuNewsModel();
    }

    public void loadThemeContent(int theme_id) {
        zhihuNewsModel.getZhihuThemeContent(theme_id,this);
    }

    @Override
    public void onLoadThemeContentSuccess(String themeContent) {
        themeContentView.addThemeContent(themeContent);
    }
}
