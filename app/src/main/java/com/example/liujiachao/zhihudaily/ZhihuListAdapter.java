package com.example.liujiachao.zhihudaily;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;

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
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == TYPE_BANNER) {
            View view = inflater.inflate(R.layout.zhihu_scroll_banner,parent,false);
            return new BannerViewHolder(view);

        } else if(viewType == TYPE_DATE) {
            View view = inflater.inflate(R.layout.zhihu_date,parent,false);
            return new DateViewHolder(view);
        } else if(viewType == TYPE_ITEM){
            View view = inflater.inflate(R.layout.news_item,parent,false);
            return new  ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
