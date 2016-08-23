package com.example.liujiachao.zhihudaily;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.liujiachao.zhihudaily.mvp.model.RealmString;
import com.example.liujiachao.zhihudaily.mvp.presenter.StoryExtraPresenter;
import com.example.liujiachao.zhihudaily.mvp.presenter.ZhihuDetailPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.NewsDetailView;
import com.example.liujiachao.zhihudaily.mvp.view.ZhihuNewsView;

/**
 * Created by liujiachao on 2016/8/18.
 */
public class ZhihuNewsDetailFragment extends Fragment implements NewsDetailView {
    protected View rootView;
    private ImageView storyImage;
    private TextView storyTitle;
    private TextView imageSouce;
    private WebView detailContainer;
    private LinearLayout storyRecommenders;

    private ZhihuDetail zhihuDetail;
    private int id;



    public static ZhihuNewsDetailFragment newInstance(int id) {
        ZhihuNewsDetailFragment fragment = new ZhihuNewsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setInitialSavedState(SavedState state) {
        super.setInitialSavedState(state);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.zhihu_detail_fragment,container,false);
            storyImage = (ImageView) rootView.findViewById(R.id.story_image);
            storyTitle = (TextView) rootView.findViewById(R.id.story_title);
            imageSouce = (TextView) rootView.findViewById(R.id.image_source);
            detailContainer = (WebView)rootView.findViewById(R.id.detail_container);
            storyRecommenders = (LinearLayout) rootView.findViewById(R.id.story_recommonders);



        }
        return rootView;

    }

    //Fragment的懒加载机制，在该方法里面获取newsdetail
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        ZhihuDetailPresenter presenter = new ZhihuDetailPresenter(this);
        if(isVisibleToUser) {
            id = getArguments().getInt("id");
            zhihuDetail = DB.getById(id,ZhihuDetail.class);
            if(zhihuDetail == null ) {
                presenter.loadNewsDetail(id);
            } else {
                showDetails(zhihuDetail);
            }
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void showDetails(ZhihuDetail zhihuDetail) {
        if (!TextUtils.isEmpty(zhihuDetail.getImage())) {
            Glide.with(ZhihuDailyApplication.context).load(zhihuDetail.getImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade().into(storyImage);
        } else {
            storyImage.setVisibility(View.INVISIBLE);
        }

        storyTitle.setText(zhihuDetail.getTitle());
        if (zhihuDetail.getRecommenders() == null) {
            storyRecommenders.setVisibility(View.GONE);
        } else {
            storyRecommenders.removeViews(1,storyRecommenders.getChildCount() - 1);
            for (Recommender rec : zhihuDetail.getRecommenders()) {
                ImageView image = (ImageView) View.inflate(getActivity(),R.layout.recommender_item,null);
                Glide.with(ZhihuDailyApplication.context).load(rec.getAvatar())
                        .diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(image);
                storyRecommenders.addView(image);
            }
        }

        String css = "";
        for (RealmString css_url : zhihuDetail.getCss()) {
            css += "<link rec=\"stylesheet\" href=" + css_url.getVal() + ">\n";
        }

        String js = "";
        for (RealmString js_url : zhihuDetail.getJs()) {
            js += "<script src=" + js_url.getVal() + ">\n";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n").append("<head>\n").append(css).append(js).append("</head>\n")
                .append("<body>").append(zhihuDetail.getBody()).append("</body>\n")
                .append("</html>\n");

        detailContainer.loadData(sb.toString(),"text/html;charset=UTF-8", "UTF-8");

    }

    @Override
    public void onResume() {
        super.onResume();
        detailContainer.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        detailContainer.onPause();
    }

    @Override
    public void showLoadFailed(String msg) {
        if(rootView != null) {
            Snackbar.make(rootView,"网络不给力，请稍后再试",Snackbar.LENGTH_LONG).show();
        }




    }
}
