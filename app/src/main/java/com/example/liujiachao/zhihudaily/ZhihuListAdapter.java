package com.example.liujiachao.zhihudaily;

import android.content.ClipData;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by liujiachao on 2016/7/20.
 */
public class ZhihuListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //scrolling picture banner
    public static int TYPE_BANNER = 0 ;
    // data item
    public static  int TYPE_DATE = 1;
    // view item
    public static int TYPE_ITEM = 2;


    private List<ZhihuItemInfo> zhihuItemList;
    private List<ZhihuTop> tops;
    private OnShowNewsDetail mlistener;
    private List<NewsItem> news;

    public ZhihuListAdapter(OnShowNewsDetail listener) {
        mlistener = listener;
        zhihuItemList = DB.findAll(ZhihuItemInfo.class);
        tops = DB.findAll(ZhihuTop.class);
        news = DB.findAll(NewsItem.class);
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

        if(holder instanceof DateViewHolder) {
            final DateViewHolder dateViewHolder = (DateViewHolder) holder;
                dateViewHolder.DateText.setText(getNewsLabel(news.get(position - 1).getDate()));
        } else if (holder instanceof BannerViewHolder) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder)holder;
            bannerViewHolder.banner.setPages(new CBViewHolderCreator<BannerView>() {
                @Override
                public BannerView createHolder() {
                    return new BannerView();
                }
            },tops);
                    //.setPageIndicator(new int[]{R.drawable.ic_page_indicator,R.drawable.ic_page_indicator_focused});
            bannerViewHolder.banner.notifyDataSetChanged();
        } else if (holder instanceof ItemViewHolder){
            final ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
            itemViewHolder.mTitle.setText(news.get(position -1).getItemInfo().getTitle());
            itemViewHolder.zhihuItemInfo = news.get(position -1).getItemInfo();
            Glide.with(context).load(news.get(position -1).getItemInfo().getImages().get(0).getVal()).
                    diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().
                    into(itemViewHolder.mImage);
            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(mlistener != null) {
                        mlistener.onShowNewsDetail(itemViewHolder);
                    }
                }
            });

        }

    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) {
            return TYPE_BANNER;
        } else if(news.get(position -1).getType() == TYPE_DATE){
            return TYPE_DATE;
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
            mImage =(ImageView)view.findViewById(R.id.news_img);
            mTitle = (TextView)view.findViewById(R.id.news_title);
            mItem = view.findViewById(R.id.news_item);

        }
        public ImageView mImage;
        public TextView mTitle;
        public View mItem;
        public ZhihuItemInfo zhihuItemInfo;
    }

    private String getNewsLabel(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String today = format.format(new Date());
        if (date.equals(today)) {
            return "今日热闻";
        } else {
            SimpleDateFormat format2 = new SimpleDateFormat("MM月dd日", Locale.getDefault());
            try {
                Date then = format.parse(date);
                String result = format2.format(then);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(then);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                switch (dayOfWeek) {
                    case Calendar.SUNDAY:
                        result += " 星期日";
                        break;
                    case Calendar.MONDAY:
                        result += " 星期一";
                        break;
                    case Calendar.TUESDAY:
                        result += " 星期二";
                        break;
                    case Calendar.WEDNESDAY:
                        result += " 星期三";
                        break;
                    case Calendar.THURSDAY:
                        result += " 星期四";
                        break;
                    case Calendar.FRIDAY:
                        result += " 星期五";
                        break;
                    case Calendar.SATURDAY:
                        result += " 星期六";
                        break;
                }
                return result;
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
        }
    }
}
