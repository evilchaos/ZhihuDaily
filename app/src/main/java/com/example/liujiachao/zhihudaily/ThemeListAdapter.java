package com.example.liujiachao.zhihudaily;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by liujiachao on 2016/9/6.
 */
public class ThemeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    OnShowNewsDetail mOnShowNewsDetail;

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
