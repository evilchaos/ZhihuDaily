package com.example.liujiachao.zhihudaily;

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
import com.example.liujiachao.zhihudaily.mvp.view.BannerView;
import com.example.liujiachao.zhihudaily.utils.Dater;

import java.util.List;

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
    //footer item
    private static int TYPE_FOOTER = 3;

    private List<ZhihuItemInfo> zhihuItemList;
    private List<ZhihuTop> tops;
    private OnShowNewsDetail mlistener;

    public ZhihuListAdapter(OnShowNewsDetail listener) {
        mlistener = listener;
        zhihuItemList = DB.findAll(ZhihuItemInfo.class);
        tops = DB.findAll(ZhihuTop.class);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == TYPE_BANNER) {
            View view = inflater.inflate(R.layout.zhihu_scroll_banner,parent,false);
            return new BannerViewHolder(view);

        } else if(viewType == TYPE_DATE) {
            View view = inflater.inflate(R.layout.zhihu_date,parent,false);
            return new DateViewHolder(view);
        } else  {
            View view = inflater.inflate(R.layout.news_item,parent,false);
            return new  ItemViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        if(holder instanceof ItemViewHolder) {
            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            if (position == 1) {
                itemViewHolder.header.setText("今日热闻");
                itemViewHolder.mItem.setVisibility(View.GONE);
                itemViewHolder.header.setVisibility(View.VISIBLE);
                return;
            } else {
                itemViewHolder.zhihuItemInfo = zhihuItemList.get(position - 2);
                if(itemViewHolder.zhihuItemInfo.getType() == 1) {
                    String date = Dater.getDisplayDate(itemViewHolder.zhihuItemInfo.getId() + "");
                    itemViewHolder.header.setText(date);
                    itemViewHolder.header.setVisibility(View.VISIBLE);
                    itemViewHolder.header.setClickable(false);
                    itemViewHolder.mItem.setVisibility(View.GONE);
                    return;
                } else {
                    itemViewHolder.header.setVisibility(View.GONE);
                    itemViewHolder.mItem.setVisibility(View.VISIBLE);
                }

            }

            Glide.with(context).load(itemViewHolder.zhihuItemInfo.getImages().get(0).getVal()).
                    diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().
                    into(itemViewHolder.mImage);
            itemViewHolder.mTitle.setText(itemViewHolder.zhihuItemInfo.getTitle());
            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(mlistener != null) {
                        mlistener.onShowNewsDetail(itemViewHolder);
                    }
                }
            });
        } else if (holder instanceof BannerViewHolder) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder)holder;
            bannerViewHolder.banner.setPages(new CBViewHolderCreator<BannerView>() {
                @Override
                public BannerView createHolder() {
                    return new BannerView();
                }
            },tops);
            bannerViewHolder.banner.notifyDataSetChanged();
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return TYPE_BANNER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return zhihuItemList.size() + 1;

    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        public ConvenientBanner<ZhihuTop> banner;

        public BannerViewHolder(View view) {
            super(view);
            banner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        }

    }


    public class DateViewHolder extends RecyclerView.ViewHolder {
        public TextView DateText;
        public DateViewHolder(View view) {
            super(view);
            DateText = (TextView)view.findViewById(R.id.zhihuDate);
        }


    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder (View view) {
            super(view);
            header = (TextView)view.findViewById(R.id.news_time);
            mImage =(ImageView)view.findViewById(R.id.news_img);
            mTitle = (TextView)view.findViewById(R.id.news_title);
            mItem = view.findViewById(R.id.news_item);

        }
        public TextView header;
        public ImageView mImage;
        public TextView mTitle;
        public View mItem;
        public ZhihuItemInfo zhihuItemInfo;

    }
}
