package com.example.liujiachao.zhihudaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.liujiachao.zhihudaily.entity.NewsItem;
import com.example.liujiachao.zhihudaily.interfaces.OnShowNewsDetail;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.entity.ZhihuItemInfo;
import com.example.liujiachao.zhihudaily.entity.ZhihuTop;
import com.example.liujiachao.zhihudaily.mvp.view.BannerView;
import com.example.liujiachao.zhihudaily.utils.DB;
import com.example.liujiachao.zhihudaily.utils.Dater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by liujiachao on 2016/7/20.
 */
public class ZhihuListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final static int TYPE_BANNER = 0 ;
    public final static int TYPE_DATE = 1;
    public final static int TYPE_ITEM = 2;


    private List<ZhihuTop> tops = new ArrayList<>();
    private List<NewsItem> news = new ArrayList<>();
    private ArrayList<Integer> idList = new ArrayList<>();
    private OnShowNewsDetail mlistener;

    public ZhihuListAdapter(OnShowNewsDetail listener) {
        mlistener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == TYPE_BANNER) {
            View view = inflater.inflate(R.layout.zhihu_scroll_banner,parent,false);
            return new BannerViewHolder(view);

        } else if (viewType == TYPE_DATE) {
            View view = inflater.inflate(R.layout.zhihu_date,parent,false);
            return new DateViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.news_item,parent,false);
            return new  ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Context context = holder.itemView.getContext();

        if (holder instanceof BannerViewHolder) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.banner.setPages(new CBViewHolderCreator<BannerView>() {
                @Override
                public BannerView createHolder() {
                    return new BannerView(tops);
                }
            }, tops);
            //.setPageIndicator(new int[]{R.drawable.ic_page_indicator,R.drawable.ic_page_indicator_focused});
            bannerViewHolder.banner.notifyDataSetChanged();
        }  else if (holder instanceof DateViewHolder) {
            NewsItem item = news.get(position - 1);
            DateViewHolder dateViewHolder = (DateViewHolder)holder;
            dateViewHolder.mDateView.setText(Dater.getNewsLabel(item.getDate()));
        } else {
            NewsItem item = news.get(position - 1);
            final ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
            itemViewHolder.mTitle.setText(item.getTitle());
            itemViewHolder.newsItem = item;

            Glide.with(context).load(item.getImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade().into(itemViewHolder.mImage);

            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener != null) {
                        mlistener.onShowNewsDetail(itemViewHolder,idList);
                    }
                }
            });

        }
    }

    public void updateTopStory(List<ZhihuTop> data) {
        tops.clear();
        tops.addAll(data);
    }

    public void updateLatest(List<NewsItem> data) {
        news.clear();
        news.addAll(data);
        idList.clear();

        for (NewsItem newsItem : news) {
            if (newsItem.getType() == ZhihuListAdapter.TYPE_ITEM) {
                idList.add(newsItem.getId());
            }
        }
        notifyDataSetChanged();
    }

    public void loadOldData(List<NewsItem> data) {
        news.addAll(data);
        for (NewsItem newsItem :news) {
            if (newsItem.getType() == ZhihuListAdapter.TYPE_ITEM) {
                idList.add(newsItem.getId());
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return TYPE_BANNER;
        } else {
            return news.get(position -1).getType();
        }
    }

    @Override
    public int getItemCount() {
        return news.size() + 1;

    }

    public NewsItem getData(int position) {
        if (position == 0) {
            return null;
        } else {
            return  news.get(position - 1);
        }
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        public ConvenientBanner<ZhihuTop> banner;

        public BannerViewHolder(View view) {
            super(view);
            banner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        }

    }

    public class DateViewHolder extends RecyclerView.ViewHolder {
        public TextView mDateView;

        public DateViewHolder(View itemView) {
            super(itemView);
            mDateView = (TextView)itemView.findViewById(R.id.date);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImage;
        public TextView mTitle;
        public NewsItem newsItem;

        public ItemViewHolder (View view) {
            super(view);
            mImage =(ImageView)view.findViewById(R.id.news_img);
            mTitle = (TextView)view.findViewById(R.id.news_title);
        }
    }
}
