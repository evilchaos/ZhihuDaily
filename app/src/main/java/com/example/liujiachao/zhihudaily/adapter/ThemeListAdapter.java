package com.example.liujiachao.zhihudaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.entity.ThemeContent;
import com.example.liujiachao.zhihudaily.entity.ThemeItem;
import com.example.liujiachao.zhihudaily.interfaces.OnShowNewsDetail;
import com.example.liujiachao.zhihudaily.utils.DB;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by liujiachao on 2016/9/6.
 */
public class ThemeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int HEADER_TYPE = 0;
    private final static int ITEM_TYPE = 1;
    private OnShowNewsDetail mOnShowNewsDetail;
    private List<ThemeItem> themeItemList;
    private RealmResults<ThemeContent> themeContent;



    public ThemeListAdapter(OnShowNewsDetail mOnShowNewsDetail) {
        this.mOnShowNewsDetail = mOnShowNewsDetail;
        themeItemList = DB.findAll(ThemeItem.class);
        themeContent = DB.findAll(ThemeContent.class);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == HEADER_TYPE) {
            View view = inflater.inflate(R.layout.theme_news_header,parent,false);
            return  new ThemeHeaderViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.news_item,parent,false);
            return new ThemeItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        if (holder instanceof ThemeHeaderViewHolder) {
            final ThemeHeaderViewHolder themeHeaderViewHolder = (ThemeHeaderViewHolder)holder;
            Glide.with(context).load(themeItemList.)
            themeHeaderViewHolder.headImageView
        }

    }

    @Override
    public int getItemCount() {
        return themeItemList.size() + 1 ;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_TYPE;
        } else {
            return ITEM_TYPE;
        }
    }

    public class ThemeHeaderViewHolder extends RecyclerView.ViewHolder {
        ImageView headImageView;
        TextView themeTitle;
        TextView srcImg;
        LinearLayout editContainer;

        public ThemeHeaderViewHolder(View itemView) {
            super(itemView);
            headImageView = (ImageView)itemView.findViewById(R.id.iv_theme_image);
            themeTitle = (TextView)itemView.findViewById(R.id.tv_container_theme);
            srcImg = (TextView)itemView.findViewById(R.id.tv_theme_img_src);
            editContainer = (LinearLayout)itemView.findViewById(R.id.editor_container);
        }
    }

    public class ThemeItemViewHolder extends RecyclerView.ViewHolder {
        public TextView storyHeader;
        public ImageView mImage;
        public TextView mTitle;
        public View mItem;
        public ThemeItem themeItem;

        public ThemeItemViewHolder(View view) {
            super(view);
            storyHeader = (TextView)view.findViewById(R.id.story_header);
            mImage =(ImageView)view.findViewById(R.id.news_img);
            mTitle = (TextView)view.findViewById(R.id.news_title);
            mItem = view.findViewById(R.id.news_item);
        }
    }
}
