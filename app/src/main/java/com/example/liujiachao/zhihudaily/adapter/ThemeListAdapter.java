package com.example.liujiachao.zhihudaily.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.entity.Edit;
import com.example.liujiachao.zhihudaily.entity.ThemeContent;
import com.example.liujiachao.zhihudaily.entity.ThemeItem;
import com.example.liujiachao.zhihudaily.interfaces.OnShowNewsDetail;
import com.example.liujiachao.zhihudaily.utils.DB;

import java.io.Serializable;
import java.util.ArrayList;
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
    private List<Edit> edits;



    public ThemeListAdapter(OnShowNewsDetail mOnShowNewsDetail) {
        this.mOnShowNewsDetail = mOnShowNewsDetail;
        themeItemList = DB.findAll(ThemeItem.class);
        themeContent = DB.findAll(ThemeContent.class);
        edits = DB.findAll(Edit.class);
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
        final Context context = holder.itemView.getContext();
        if (holder instanceof ThemeHeaderViewHolder) {
            if (themeItemList.size() == 0) {
                return;
            }
            final ThemeHeaderViewHolder themeHeaderViewHolder = (ThemeHeaderViewHolder)holder;
            Glide.with(context).load(themeContent.get(0).getBackground())
                    .diskCacheStrategy(DiskCacheStrategy.ALL).crossFade()
                    .into(themeHeaderViewHolder.headImageView);
            themeHeaderViewHolder.themeTitle.setText(themeContent.get(0).getName());
            if (themeContent.get(0).getImage_source() != null) {
                themeHeaderViewHolder.themeTitle.setText(themeContent.get(0).getImage_source());
            }

            if (edits == null) {
                //comment_avatar.png
                ImageView editImg = new ImageView(context);
                editImg.setImageResource(R.drawable.comment_avatar);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                ,ViewGroup.LayoutParams.WRAP_CONTENT);
                params.rightMargin = 10;
                params.addRule(RelativeLayout.CENTER_VERTICAL);
                editImg.setLayoutParams(params);
                themeHeaderViewHolder.editContainer.addView(editImg);
            } else {
                for (int index = 0; index < edits.size(); index++ ) {
                    Edit edit = edits.get(index);
                    ImageView editImg = new ImageView(context);
                    Glide.with(context).load(edit.getAvatar())
                            .diskCacheStrategy(DiskCacheStrategy.ALL).crossFade()
                            .into(editImg);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                            ,ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.rightMargin = 10;
                    params.addRule(RelativeLayout.CENTER_VERTICAL);
                    editImg.setLayoutParams(params);
                    themeHeaderViewHolder.editContainer.addView(editImg);
                }
            }
            themeHeaderViewHolder.editContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("edits",(Serializable)edits);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
        } else {
            ThemeItem themeItem = themeItemList.get(position - 1);
            final ThemeItemViewHolder themeItemViewHolder = (ThemeItemViewHolder)holder;
            themeItemViewHolder.storyHeader.setVisibility(View.GONE);
            themeItemViewHolder.mTitle.setText(themeItem.getTitle());

            if (themeItem.getImages() != null && themeItem.getImages().size() > 0) {
                Glide.with(context).load(themeItem.getImages().get(0).getVal())
                        .diskCacheStrategy(DiskCacheStrategy.ALL).crossFade()
                        .into(themeItemViewHolder.mImage);
            } else {
                themeItemViewHolder.mImage.setVisibility(View.GONE);
            }

            themeItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnShowNewsDetail != null) {
                        mOnShowNewsDetail.onShowNewsDetail(themeItemViewHolder);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (themeItemList.size() == 0) {
            return 0;
        } else {
            return themeItemList.size() + 1 ;
        }
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
