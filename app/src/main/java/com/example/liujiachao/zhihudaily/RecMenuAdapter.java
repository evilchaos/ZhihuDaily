package com.example.liujiachao.zhihudaily;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by evilchaos on 16/8/28.
 */
public class RecMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MyTheme> myThemeList;
    final static int TYPE_HEADER = 0;
    final static int TYPE_ITEM = 1;

    public RecMenuAdapter(List<MyTheme> myThemeList) {
        this.myThemeList = myThemeList;
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
        return myThemeList.size() + 1 ;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0 ) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView loginView;
        public TextView collectView;
        public TextView downloadView;
        public TextView homeView;

        public HeaderViewHolder(View view) {
            super(view);
            //imageView = view.findViewById;
        }
    }
}
