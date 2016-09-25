package com.example.liujiachao.zhihudaily.interfaces;

import android.support.v7.widget.RecyclerView;

import com.example.liujiachao.zhihudaily.entity.ThemeItem;

import java.util.List;

/**
 * Created by liujiachao on 2016/9/25.
 */
public interface OnShowThemeDetail {
    void onShowThemeDetail(RecyclerView.ViewHolder holder,List<ThemeItem> themeItemList,int position);
}
