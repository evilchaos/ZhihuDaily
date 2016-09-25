package com.example.liujiachao.zhihudaily.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.liujiachao.zhihudaily.R;
import com.example.liujiachao.zhihudaily.application.ZhihuDailyApplication;
import com.example.liujiachao.zhihudaily.entity.Recommender;
import com.example.liujiachao.zhihudaily.entity.ZhihuDetail;
import com.example.liujiachao.zhihudaily.entity.RealmString;
import com.example.liujiachao.zhihudaily.mvp.presenter.ZhihuDetailPresenter;
import com.example.liujiachao.zhihudaily.mvp.view.NewsDetailView;
import com.example.liujiachao.zhihudaily.utils.DB;
import com.example.liujiachao.zhihudaily.utils.DTOP;
import com.example.liujiachao.zhihudaily.widgets.UWebView;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.RealmList;

/**
 * Created by liujiachao on 2016/8/18.
 */
public class ZhihuNewsDetailFragment extends Fragment implements NewsDetailView {
    protected View rootView;
    private ImageView storyImage;
    private TextView storyTitle;
    private TextView imageSouce;
    private UWebView detailContainer;
    private LinearLayout storyRecommenders;
    private FrameLayout detailImgHeader;

    private ZhihuDetail zhihuDetail;
    private int id;
    private ZhihuDetailPresenter presenter = new ZhihuDetailPresenter(this);

    private boolean isVisible;
    private boolean isPrepared;


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
            detailContainer = (UWebView)rootView.findViewById(R.id.detail_container);
            storyRecommenders = (LinearLayout) rootView.findViewById(R.id.story_recommonders);
            detailImgHeader = (FrameLayout)rootView.findViewById(R.id.detail_img_header);

            isPrepared = true;
            lazyLoad();
        }
        return rootView;

    }

    //Fragment的懒加载机制，在该方法里面获取newsdetail
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            isVisible = true;
            lazyLoad();
        }
    }

    private void lazyLoad() {
        if(!isPrepared || !isVisible){
            return;
        }
        id = getArguments().getInt("id");
        zhihuDetail = DB.getById(id, ZhihuDetail.class);
        if(zhihuDetail == null ) {
            presenter.loadNewsDetail(id);
        } else {
            showDetails(zhihuDetail);
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
            storyTitle.setText(zhihuDetail.getTitle());
            if (!TextUtils.isEmpty(zhihuDetail.getImage_source())) {
                imageSouce.setText(zhihuDetail.getImage_source());
            }
        } else {
            detailImgHeader.setVisibility(View.GONE);
        }

        RealmList<Recommender>  recommenders = zhihuDetail.getRecommenders();
        if (recommenders != null && recommenders.size() > 0) {
            storyRecommenders.setVisibility(View.VISIBLE);
            storyRecommenders.removeViews(1, storyRecommenders.getChildCount() - 1);

            for (Recommender rec : recommenders) {
                CircleImageView circleImageView = new CircleImageView(getActivity());

                Glide.with(getActivity()).load(rec.getAvatar())
                .diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(circleImageView);

                int px = DTOP.dip2px(getActivity(),30);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(px,px);
                lp.rightMargin = DTOP.dip2px(getActivity(), 10);
                circleImageView.setLayoutParams(lp);

                storyRecommenders.addView(circleImageView);
            }
        }

        String css = "";
        for (RealmString css_url : zhihuDetail.getCss()) {
            css += "<link rel=\"stylesheet\" href=" + css_url.getVal() + ">\n";
        }

        String js = "";
        for (RealmString js_url : zhihuDetail.getJs()) {
            js += "<script src=" + js_url.getVal() + "/>\n";
        }

        //这个地方有bug,出现bug的原因是zhiDetail中某些字段，如body为空，此时要加载share_url内容
        String mBody = zhihuDetail.getBody();
        String body = mBody.replaceAll("<div class=\"img-place-holder\"></div>", "");

        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n").append("<head>\n").append(css).append(js).append("</head>\n")
                .append("<body>").append(body).append("</body>\n")
                .append("</html>");

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
