package com.example.liujiachao.zhihudaily;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liujiachao on 2016/7/20.
 */
public class ZhihuListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //scrolling picture banner
    private static int TYPE_BANNER = 0 ;
    // data item
    private static  int TYPE_DATE = 1;
    // view item
    private static int TYPE_ITEM = 2;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == TYPE_BANNER) {
            View view = inflater.inflate(R.layout.zhihu_scroll_banner,null);

        } else if(viewType == TYPE_DATE) {
            View view = inflater.inflate(R.layout.zhihu_date,null);

        } else if(viewType == TYPE_ITEM){

        }
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
