package com.example.liujiachao.zhihudaily;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liujiachao.zhihudaily.mvp.view.NewsDetailView;
import com.example.liujiachao.zhihudaily.mvp.view.ZhihuNewsView;

/**
 * Created by liujiachao on 2016/8/18.
 */
public class ZhihuNewsDetailFragment extends Fragment implements NewsDetailView {
    @Override
    public void setInitialSavedState(SavedState state) {
        super.setInitialSavedState(state);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //Fragment的懒加载机制，在该方法里面获取newsdetail
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void showDetails(ZhihuDetail zhihuDetail) {

    }

    @Override
    public void showLoadFailed(String msg) {

    }
}
