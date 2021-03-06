package com.example.liujiachao.zhihudaily.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
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
import com.example.liujiachao.zhihudaily.activity.EditorDetailActivity;
import com.example.liujiachao.zhihudaily.entity.Edit;
import com.example.liujiachao.zhihudaily.entity.MyTheme;
import com.example.liujiachao.zhihudaily.entity.ThemeContent;
import com.example.liujiachao.zhihudaily.entity.ThemeItem;
import com.example.liujiachao.zhihudaily.interfaces.OnShowNewsDetail;
import com.example.liujiachao.zhihudaily.interfaces.OnShowThemeDetail;
import com.example.liujiachao.zhihudaily.utils.DB;
import com.example.liujiachao.zhihudaily.utils.DTOP;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.RealmResults;

/**
 * Created by liujiachao on 2016/9/6.
 */
public class ThemeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int HEADER_TYPE = 0;
    private final static int ITEM_TYPE = 1;
    private OnShowThemeDetail mOnShowThemeDetail;
    private boolean isEditorLoaded = false;
    private ArrayList<ThemeContent> themeContent = new ArrayList<>();


    public ThemeListAdapter(OnShowThemeDetail mOnShowThemeDetail,String name) {
        this.mOnShowThemeDetail = mOnShowThemeDetail;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == HEADER_TYPE) {
            View view = inflater.inflate(R.layout.theme_news_header,parent,false);
            return  new ThemeHeaderViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.news_item, parent, false);
            return new ThemeItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Context context = holder.itemView.getContext();
        final ThemeContent tm_content = themeContent.get(0);
        if (holder instanceof ThemeHeaderViewHolder) {
            final ThemeHeaderViewHolder themeHeaderViewHolder = (ThemeHeaderViewHolder)holder;

            Glide.with(context).load(tm_content.getBackground())
                    .diskCacheStrategy(DiskCacheStrategy.ALL).crossFade()
                    .into(themeHeaderViewHolder.headImageView);

            String description = tm_content.getDescription();
            themeHeaderViewHolder.themeDes.setText(description);

            if (tm_content.getImage_source() != null) {
                themeHeaderViewHolder.srcImg.setText(tm_content.getImage_source());
            }

            final List<Edit> edits = tm_content.getEditors();

            if (!isEditorLoaded) {
                addEditor(context,themeHeaderViewHolder,edits);
            }

            themeHeaderViewHolder.editContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,EditorDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("edits",(Serializable)edits);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
        } else {
            ThemeItem themeItem = tm_content.getStories().get(position - 1);
            final ThemeItemViewHolder themeItemViewHolder = (ThemeItemViewHolder)holder;

            themeItemViewHolder.mTitle.setText(themeItem.getTitle());
            themeItemViewHolder.themeItem = themeItem;

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
                    if (mOnShowThemeDetail != null) {
                        List<ThemeItem> themeItems = tm_content.getStories();
                        mOnShowThemeDetail.onShowThemeDetail(themeItemViewHolder,themeItems,position - 1);
                    }
                }
            });
        }

    }

    private void addEditor(Context context,ThemeHeaderViewHolder themeHeaderViewHolder,List<Edit> edits) {
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
                CircleImageView circleImageView = new CircleImageView(context);
                Glide.with(context).load(edit.getAvatar())
                        .diskCacheStrategy(DiskCacheStrategy.ALL).crossFade()
                        .into(circleImageView);
                int px = DTOP.dip2px(context,30);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(px,px);
                lp.rightMargin = DTOP.dip2px(context, 10);
                circleImageView.setLayoutParams(lp);
                themeHeaderViewHolder.editContainer.addView(circleImageView);
            }
        }
        isEditorLoaded = true;
    }

    public void updateData(ArrayList<ThemeContent> data) {
        //更改数据源，不能直接赋值
        //this.myThemeList = myThemeList;
        themeContent.clear();
        themeContent.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (themeContent.size() == 0) {
            return 0;
        } else {
            return themeContent.get(0).getStories().size() + 1 ;
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
        TextView themeDes;
        TextView srcImg;
        LinearLayout editContainer;

        public ThemeHeaderViewHolder(View itemView) {
            super(itemView);
            headImageView = (ImageView)itemView.findViewById(R.id.iv_theme_image);
            themeDes = (TextView)itemView.findViewById(R.id.tv_theme_des);
            srcImg = (TextView)itemView.findViewById(R.id.tv_theme_img_src);
            editContainer = (LinearLayout)itemView.findViewById(R.id.editor_container);
        }
    }

    public class ThemeItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImage;
        public TextView mTitle;
        public ThemeItem themeItem;

        public ThemeItemViewHolder(View view) {
            super(view);
            mImage =(ImageView)view.findViewById(R.id.news_img);
            mTitle = (TextView)view.findViewById(R.id.news_title);
        }
    }
}
