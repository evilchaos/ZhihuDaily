package com.example.liujiachao.zhihudaily.mvp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.activity.ZhihuActivity;
import com.example.liujiachao.zhihudaily.activity.ZhihuNewsDetailActivity;
import com.example.liujiachao.zhihudaily.entity.ZhihuTop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evilchaos on 16/7/23.
 */
public class BannerView implements Holder<ZhihuTop> {

    private View view;
    private List<ZhihuTop> zhihuTops;
    public BannerView(List<ZhihuTop> zhihuTops) {
        this.zhihuTops = zhihuTops;
    }

    @Override
    public View createView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
        return view;
    }

    @Override
    public void UpdateUI(final Context context, final int position, final ZhihuTop data) {

        final ImageView imageView = (ImageView)view.findViewById(R.id.banner_img);
        TextView textView = (TextView)view.findViewById(R.id.banner_title);
        textView.setText(data.getTitle());
        Glide.with(context).load(data.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH).into(imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ZhihuNewsDetailActivity.class);
                intent.putExtra("id",position);
                ArrayList<Integer> idList = new ArrayList<Integer>();
                if (zhihuTops != null && zhihuTops.size() > 0) {
                    for (ZhihuTop zhihuTop : zhihuTops) {
                        idList.add(zhihuTop.getId());
                    }
                }
                intent.putIntegerArrayListExtra("all_id",idList);
                context.startActivity(intent);
            }
        });

    }
}
