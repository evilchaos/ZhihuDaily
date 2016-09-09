package com.example.liujiachao.zhihudaily.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.liujiachao.zhihudaily.entity.Edit;

import java.util.List;

/**
 * Created by liujiachao on 2016/9/8.
 */
public class EditorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Edit> edits;

    public EditorAdapter(List<Edit> edits) {
        this.edits = edits;
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
        return edits.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {


        public ItemViewHolder(View itemView) {
            super(itemView);

        }
    }
}
