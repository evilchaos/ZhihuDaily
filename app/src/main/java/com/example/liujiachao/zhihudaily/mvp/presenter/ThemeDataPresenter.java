package com.example.liujiachao.zhihudaily.mvp.presenter;

import com.example.liujiachao.zhihudaily.OnLoadDailyThemesListener;
import com.example.liujiachao.zhihudaily.ThemeData;
import com.example.liujiachao.zhihudaily.mvp.model.ZhihuNewsModel;
import com.example.liujiachao.zhihudaily.mvp.view.ThemeDataView;

/**
 * Created by liujiachao on 2016/8/29.
 */
public class ThemeDataPresenter implements OnLoadDailyThemesListener{

    private ThemeDataView themeDataView;
    private ZhihuNewsModel zhihuNewsModel;

    public ThemeDataPresenter(ThemeDataView themeDataView) {
        this.themeDataView = themeDataView;
        zhihuNewsModel = new ZhihuNewsModel();
    }

    @Override
    public void OnLoadDailyThemesSuccess(ThemeData themeData) {
        themeDataView.PassThemeDataToActivity(themeData);
    }

    public void loadThemeData() {
        zhihuNewsModel.getZhihuDailyThemes(this);
    }

}
