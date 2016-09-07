package com.example.liujiachao.zhihudaily.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.liujiachao.zhihudaily.entity.ThemeItem;
import com.example.liujiachao.zhihudaily.interfaces.OnShowNewsDetail;

import java.util.List;

/**
 * Created by liujiachao on 2016/9/6.
 */
public class ThemeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnShowNewsDetail mOnShowNewsDetail;
    private List<ThemeItem> themeItemList;



    public ThemeListAdapter(OnShowNewsDetail mOnShowNewsDetail) {
        this.mOnShowNewsDetail = mOnShowNewsDetail;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
