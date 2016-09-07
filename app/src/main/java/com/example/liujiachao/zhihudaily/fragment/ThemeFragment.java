package com.example.liujiachao.zhihudaily.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liujiachao.zhihudaily.interfaces.OnShowNewsDetail;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.adapter.ThemeListAdapter;
import com.example.liujiachao.zhihudaily.entity.ThemeContent;
import com.example.liujiachao.zhihudaily.mvp.presenter.ThemeContentPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.ThemeContentView;

/**
 * Created by liujiachao on 2016/9/6.
 */
public class ThemeFragment extends Fragment implements ThemeContentView ,OnShowNewsDetail {

    private Context context;
    private ThemeListAdapter themeListAdapter;
    private RecyclerView themeListView;
    private int theme_id;
    private ThemeContentPresenter themeContentPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();
        View themeFragmentView = inflater.inflate(R.layout.navi_fragment,null);
        themeListAdapter = new ThemeListAdapter(this);
        themeListView = (RecyclerView)themeFragmentView.findViewById(R.id.recycler_view);
        themeListView.setAdapter(themeListAdapter);

        themeContentPresenter = new ThemeContentPresenter(this);
        theme_id = getArguments().getInt("theme_id");
        themeContentPresenter.loadThemeContent(theme_id);
        return themeFragmentView;

    }

    @Override
    public void addThemeContent(ThemeContent themeContent) {
        themeListAdapter.notifyDataSetChanged();
    }


    @Override
    public void onShowNewsDetail(RecyclerView.ViewHolder holder) {

    }
}